@file:OptIn(ExperimentalWasmJsInterop::class, ExperimentalJsExport::class)

package com.dv.apps.komic

import androidx.compose.runtime.Composable
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.js.*

private suspend fun <T: JsAny> Promise<T>.await(): T? = suspendCancellableCoroutine { cont ->
    then(
        onFulfilled = {
            cont.resume(it)
            null
        },
        onRejected = {
            cont.resume(null)
            null
        }
    )
}

external class FileSystemDirectoryHandle : JsAny {
    val name: String
}

private fun createDirectoryPickerOptions(
    startIn: String? = null,
): JsAny = js(
    "({ startIn })"
)

external fun showDirectoryPicker(options: JsAny = definedExternally): Promise<FileSystemDirectoryHandle>

actual typealias Window = Unit

@Composable
actual fun Window.registerFolderPicker(): suspend () -> String? = {
    val options = createDirectoryPickerOptions(
        startIn = "downloads"
    )

    val handle = showDirectoryPicker(
        options
    ).await()

    handle?.name
}