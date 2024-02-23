package com.lesa.aot.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.lesa.aot.models.Item
import com.lesa.aot.ui.theme.AotAccentYellow

@Composable
fun ItemsList(
    listOfItems: List<Item>,
    onItemClicked: (String) -> Unit
) {
    LazyColumn(
        Modifier
            //.border(1.dp, AotAccentYellow)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(
            items = listOfItems,
            itemContent = {
                ItemView(
                    item = it,
                    onItemClicked = onItemClicked
                )
                Divider(
                    thickness = 2.dp,
                    color = AotAccentYellow
                )
            }
        )
    }
}

@Composable
fun ItemsList2(
    listOfItems: LazyPagingItems<Item>,
    onItemClicked: (String) -> Unit
) {
    LazyColumn(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(listOfItems.itemCount) {
            ItemView(
                item = listOfItems[it]!!,
                onItemClicked = onItemClicked
            )
            Divider(
                thickness = 2.dp,
                color = AotAccentYellow
            )
        }
    }
}