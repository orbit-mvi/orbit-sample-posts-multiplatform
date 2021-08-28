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

package org.orbitmvi.orbit.sample.posts.app.features

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.getStateViewModel
import org.orbitmvi.orbit.sample.posts.app.common.toRouteParcelable
import org.orbitmvi.orbit.sample.posts.app.features.postdetails.ui.PostDetailsScreen
import org.orbitmvi.orbit.sample.posts.app.features.postlist.ui.PostListScreen
import org.orbitmvi.orbit.sample.posts.domain.repositories.PostOverview
import org.orbitmvi.orbit.sample.posts.domain.viewmodel.list.PostListViewModel
import org.orbitmvi.orbit.sample.posts.domain.viewmodel.detail.PostDetailsViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "list") {
                composable("list") {
                    val viewModel = getStateViewModel<PostListViewModel>()
                    PostListScreen(navController, viewModel)
                }
                composable("detail/{item}") {
                    val itemStr = it.arguments?.getString("item")!!

                    val result = itemStr.toRouteParcelable<PostOverview>()

                    // Work around a bug in Koin
                    val viewModel = viewModel<PostDetailsViewModel>(factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T =
                            // it.savedStateHandle
                            PostDetailsViewModel(get(), result) as T
                    })

                    PostDetailsScreen(navController, viewModel)
                }
            }
        }
    }
}
