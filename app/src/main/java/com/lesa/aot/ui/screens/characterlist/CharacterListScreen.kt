package com.lesa.aot.ui.screens.characterlist

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.lesa.aot.models.Item
import com.lesa.aot.ui.utils.ItemsList2

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel
) {
    val characterListItem: LazyPagingItems<Item> = viewModel.characterState.collectAsLazyPagingItems()

    ItemsList2(
        listOfItems = characterListItem,
        onItemClicked = {TODO()}
    )
}