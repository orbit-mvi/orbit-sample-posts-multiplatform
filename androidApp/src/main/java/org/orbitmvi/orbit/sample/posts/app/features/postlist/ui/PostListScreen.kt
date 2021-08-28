/*
 * Copyright 2021 Mikołaj Leszczyński & Appmattus Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.orbitmvi.orbit.sample.posts.app.features.postlist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.sample.posts.android.R
import org.orbitmvi.orbit.sample.posts.app.common.AppBar
import org.orbitmvi.orbit.sample.posts.app.common.elevation
import org.orbitmvi.orbit.sample.posts.app.common.toRouteString
import org.orbitmvi.orbit.sample.posts.domain.viewmodel.NavigationEvent
import org.orbitmvi.orbit.sample.posts.domain.viewmodel.list.OpenPostNavigationEvent
import org.orbitmvi.orbit.sample.posts.domain.viewmodel.list.PostListViewModel

@Composable
fun PostListScreen(navController: NavController, viewModel: PostListViewModel) {

    val state = viewModel.container.stateFlow.collectAsState().value

    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collect { handleSideEffect(navController, it) }
        }
    }

    val lazyListState = rememberLazyListState()

    Column {
        AppBar(stringResource(id = R.string.app_name), elevation = lazyListState.elevation)

        LazyColumn(state = lazyListState) {
            itemsIndexed(state.overviews) { index, post ->
                if (index != 0) Divider(color = colorResource(id = R.color.separator), modifier = Modifier.padding(horizontal = 16.dp))

                PostListItem(post) {
                    viewModel.onPostClicked(it)
                }
            }
        }
    }
}

private fun handleSideEffect(navController: NavController, sideEffect: NavigationEvent) {
    when (sideEffect) {
        is OpenPostNavigationEvent -> {
            val data = sideEffect.post.toRouteString()
            navController.navigate("detail/$data")
        }
    }
}
