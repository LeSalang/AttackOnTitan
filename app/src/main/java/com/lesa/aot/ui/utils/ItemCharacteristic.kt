package com.lesa.aot.ui.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun ItemCharacteristic(
    @StringRes
    titleStringRes: Int,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append(stringResource(id = titleStringRes))
                }
                append(" $description")
            },
            fontSize = 20.sp
        )
    }
}