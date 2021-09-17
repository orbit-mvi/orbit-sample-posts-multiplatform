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

package org.orbitmvi.orbit.sample.posts.domain.viewmodel.list

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.sample.posts.domain.repositories.PostOverview
import org.orbitmvi.orbit.sample.posts.domain.repositories.PostRepository
import org.orbitmvi.orbit.sample.posts.domain.viewmodel.NavigationEvent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class PostListViewModel(
    private val postRepository: PostRepository,
) : ViewModel(), ContainerHost<PostListState, NavigationEvent> {

    override val container = viewModelScope.container<PostListState, NavigationEvent>(
        initialState = PostListState()
    ) {
        loadOverviews()
    }

    private fun loadOverviews() = intent {
        val overviews = postRepository.getOverviews()

        reduce {
            state.copy(overviews = overviews)
        }
    }

    fun onPostClicked(post: PostOverview) = intent {
        postSideEffect(OpenPostNavigationEvent(post))
    }
}
