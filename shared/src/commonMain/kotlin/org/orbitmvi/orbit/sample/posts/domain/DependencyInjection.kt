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

package org.orbitmvi.orbit.sample.posts.domain

import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.orbitmvi.orbit.sample.posts.data.posts.PostDataRepository
import org.orbitmvi.orbit.sample.posts.data.posts.network.AvatarUrlGenerator
import org.orbitmvi.orbit.sample.posts.data.posts.network.PostNetworkDataSource
import org.orbitmvi.orbit.sample.posts.data.posts.network.httpClientFactory
import org.orbitmvi.orbit.sample.posts.domain.repositories.PostRepository

fun commonModule() = module {
    single {
        httpClientFactory()
    }

    single { PostNetworkDataSource(get()) }

    single { AvatarUrlGenerator() }

    single<PostRepository> { PostDataRepository(get(), get()) }
}

expect class DependencyInjection {
    fun initialiseDependencyInjection(block: KoinAppDeclaration = {})
}
