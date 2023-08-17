package com.example.presentation.ui.screens.search.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.defaultBackground
import com.example.presentation.ui.screens.common.composable.SearchBar
import com.example.presentation.ui.screens.common.composable.RestaurantInfoList
import com.example.presentation.ui.screens.common.dummyRestaurant
import com.example.presentation.ui.screens.search.SearchContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchScreen(
    state: SearchContract.State,
    effectFlow: Flow<SearchContract.Effect>,
    onEventSent: (event: SearchContract.Event) -> Unit,
    onNavigationEffect: (effect: SearchContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(true) {
        effectFlow.onEach { effect ->
            when (effect) {
                is SearchContract.Effect.Navigation -> {
                    onNavigationEffect(effect)
                }
            }
        }.collect()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(defaultBackground)
            .padding(horizontal = 24.dp)
    ) {
        TopAppBar(
            modifier = Modifier
                .height(45.dp),
            title = { },
            navigationIcon = {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable { onEventSent(SearchContract.Event.ClickBackButton) },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back Icon"
                    )
                }
            }
        )
        Box(
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            SearchBar(
                showBorder = true,
                readOnly = false,
                onValueChanged = { searchText -> onEventSent(SearchContract.Event.EnterSearchText(searchText = searchText)) },
                hint = "음식 메뉴, 지역을 검색 해 보세요"
            )
        }
        RestaurantInfoList(restaurants = state.searchResult) {
            onEventSent(SearchContract.Event.ClickRestaurant(it))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
    MukMapTheme {
        SearchScreen(
            state = SearchContract.State(
                searchResult = listOf(dummyRestaurant, dummyRestaurant, dummyRestaurant),
                hasError = false
            ),
            effectFlow = flowOf(),
            onEventSent = { },
            onNavigationEffect = { }
        )
    }
}