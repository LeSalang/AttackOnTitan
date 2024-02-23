package com.lesa.aot.ui.screens.titans

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.lesa.aot.ui.utils.ItemsList

@Composable
fun TitansScreen(
    viewModel: TitansViewModel,
    onItemClicked: (String) -> Unit
) {
    when (val titansUiState = viewModel.titansUiState) {
        is TitansUiState.Error -> Text(text = titansUiState.message)
        is TitansUiState.Loading -> Text(text = titansUiState.message)
        is TitansUiState.Success -> {
            ItemsList(
                listOfItems = titansUiState.listOfTitanItems,
                onItemClicked = onItemClicked
            )
        }
        is TitansUiState.Local -> {
            ItemsList(
                listOfItems = titansUiState.listOfTitanItems.collectAsState().value,
                onItemClicked = onItemClicked
            )
        }
    }
}

