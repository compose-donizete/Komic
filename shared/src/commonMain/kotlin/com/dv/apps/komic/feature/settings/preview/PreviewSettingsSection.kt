package com.dv.apps.komic.feature.settings.preview

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dv.apps.komic.KomicTheme
import com.dv.apps.komic.feature.settings.SettingsSection
import komic.shared.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PreviewSettingsSection() {
    if (LocalInspectionMode.current) {
        PreviewSettingsSection(State())
    } else {
        val vm = koinViewModel<PreviewSettingsSectionViewModel>()
        val state by vm.state.collectAsStateWithLifecycle()
        PreviewSettingsSection(state, vm::handleIntent)
    }
}

@Composable
fun PreviewSettingsSection(
    state: State,
    dispatchIntent: (Intent) -> Unit = {}
) {
    Column {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_preview_options_title),
                style = MaterialTheme.typography.titleMedium
            )

            IconButton(
                onClick = { }
            ) {
                Icon(
                    painterResource(Res.drawable.ic_preview),
                    contentDescription = ""
                )
            }
        }

        HorizontalDivider(Modifier.padding(horizontal = 8.dp))

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_preview_options_vertical_span)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    dispatchIntent(
                        Intent.OnVerticalPreviewSpanSizeChanged(
                            state.verticalPreviewSpanSize - 1
                        )
                    )
                }) {
                    Icon(
                        painterResource(Res.drawable.ic_arrow_left),
                        contentDescription = ""
                    )
                }
                Text("${state.verticalPreviewSpanSize}")
                IconButton(onClick = {
                    dispatchIntent(
                        Intent.OnVerticalPreviewSpanSizeChanged(
                            state.verticalPreviewSpanSize + 1
                        )
                    )
                }) {
                    Icon(
                        painterResource(Res.drawable.ic_arrow_right),
                        contentDescription = ""
                    )
                }
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_preview_options_horizontal_span)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    dispatchIntent(
                        Intent.OnHorizontalPreviewSpanSizeChanged(
                            state.horizontalPreviewSpanSize - 1
                        )
                    )
                }) {
                    Icon(
                        painterResource(Res.drawable.ic_arrow_left),
                        contentDescription = ""
                    )
                }
                Text("${state.horizontalPreviewSpanSize}")
                IconButton(onClick = {
                    dispatchIntent(
                        Intent.OnHorizontalPreviewSpanSizeChanged(
                            state.horizontalPreviewSpanSize + 1
                        )
                    )
                }) {
                    Icon(
                        painterResource(Res.drawable.ic_arrow_right),
                        contentDescription = ""
                    )
                }
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_preview_quality)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                data class QualityView(
                    val quality: Settings.Quality,
                    val icon: DrawableResource,
                    val iconFilled: DrawableResource
                )
                listOf(
                    QualityView(
                        Settings.Quality.HD,
                        Res.drawable.ic_quality_hd,
                        Res.drawable.ic_quality_hd_filled
                    ),
                    QualityView(
                        Settings.Quality.FULL_HD,
                        Res.drawable.ic_quality_full_hd,
                        Res.drawable.ic_quality_full_hd_filled
                    ),
                    QualityView(
                        Settings.Quality.TWO_K,
                        Res.drawable.ic_quality_2k,
                        Res.drawable.ic_quality_2k_filled
                    ),
                    QualityView(
                        Settings.Quality.FOUR_K,
                        Res.drawable.ic_quality_4k,
                        Res.drawable.ic_quality_4k_filled
                    )
                ).forEach {
                    IconButton(onClick = {
                        if (it.quality == state.quality) return@IconButton
                        dispatchIntent(Intent.OnQualityChanged(it.quality))
                    }) {
                        Icon(
                            painterResource(
                                if (it.quality == state.quality) {
                                    it.iconFilled
                                } else {
                                    it.icon
                                }
                            ),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSettingsSectionPreview() {
    KomicTheme {
        SettingsSection {
            PreviewSettingsSection()
        }
    }
}

