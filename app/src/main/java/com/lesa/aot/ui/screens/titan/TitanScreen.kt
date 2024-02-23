package com.lesa.aot.ui.screens.titan

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.lesa.aot.R
import com.lesa.aot.ui.theme.AotAccentGreen
import com.lesa.aot.ui.theme.AotAccentYellow
import com.lesa.aot.ui.utils.ItemCharacteristic

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TitanScreen(
    //titan: Titan?
    viewModel: TitanViewModel
) {
    val titan = viewModel.titan.collectAsState().value
    LazyColumn(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (titan != null) {
            item {
                BoxWithConstraints(
                    Modifier.fillMaxSize()
                ) {
                    GlideImage(
                        model = titan.img,
                        contentDescription = titan.name,
                        loading = placeholder(ColorPainter(AotAccentGreen)),
                        modifier = Modifier
                            .size(this.maxWidth)
                            .border(
                                2.dp, AotAccentYellow
                            )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                ItemCharacteristic(titleStringRes = R.string.view_name, description = titan.name)
                Spacer(modifier = Modifier.height(16.dp))
                ItemCharacteristic(titleStringRes = R.string.view_height, description = titan.height)
                Spacer(modifier = Modifier.height(16.dp))
                ItemCharacteristic(titleStringRes = R.string.view_abilities, description = titan.abilities.joinToString())
                Spacer(modifier = Modifier.height(16.dp))
                ItemCharacteristic(titleStringRes = R.string.view_allegiance, description = titan.allegiance)

            }
        } else {
            item {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red))
            }
        }
    }
}
