package com.lesa.aot.ui.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.lesa.aot.models.Item
import com.lesa.aot.ui.theme.AotAccentGreen
import com.lesa.aot.ui.theme.AotAccentYellow
import com.lesa.aot.ui.theme.aotFontFamily

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemView(
    item: Item,
    onItemClicked: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked(item.name)
            }
            .padding(16.dp)

    ) {
        GlideImage(
            model = item.img,
            contentDescription = item.name,
            loading = placeholder(ColorPainter(AotAccentGreen)),
            modifier = Modifier
                //.fillMaxSize()
                .size(200.dp)
                .border(
                    2.dp,
                    AotAccentYellow
                )

        )
        Column(
            modifier = Modifier
                .weight(1f)
                .requiredHeight(200.dp)
                //.background(Color.Green)
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = AotAccentYellow,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = aotFontFamily
            )
        }
    }
}