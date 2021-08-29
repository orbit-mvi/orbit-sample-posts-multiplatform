# Orbit Sample - Posts Multiplatform

This sample implements a simple master-detail application using
[Orbit MVI](https://github.com/orbit-mvi/orbit-mvi).

## Shared code

- The application uses Koin for dependency injection with dependencies defined in
  [DependencyInjection](shared/src/commonMain/kotlin/org/orbitmvi/orbit/sample/posts/domain/DependencyInjection.kt)
  . iOS exposes the dependencies
  in [ViewModels](shared/src/iosMain/kotlin/org/orbitmvi/orbit/sample/posts/domain/ViewModels.kt).

- [PostListViewModel](shared/src/commonMain/kotlin/org/orbitmvi/orbit/sample/posts/domain/viewmodel/list/PostListViewModel.kt)
  loads a list of posts
  and [PostDetailsViewModel](shared/src/commonMain/kotlin/org/orbitmvi/orbit/sample/posts/domain/viewmodel/detail/PostDetailsViewModel.kt)
  the details of a post.

## Android app code

- Navigation between the list and the detail view uses
  Jetpack's [Navigation](https://developer.android.com/jetpack/compose/navigation).
  [PostListViewModel](shared/src/commonMain/kotlin/org/orbitmvi/orbit/sample/posts/domain/viewmodel/list/PostListViewModel.kt)
  posts a side effect which
  [PostListScreen](androidApp/src/main/java/org/orbitmvi/orbit/sample/posts/app/features/postlist/ui/PostListScreen.kt)
  observes and sends to the `NavController`.

- The state is accessed in the screens through a `Flow`.

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
  is used to render layouts throughout.

## iOS app code

- Navigation between the list and the detail view uses programmatic navigation based on
  [The future of SwiftUI navigation (?)](https://www.fivestars.blog/articles/programmatic-navigation/)
  .
  [PostListViewModel](shared/src/commonMain/kotlin/org/orbitmvi/orbit/sample/posts/domain/viewmodel/list/PostListViewModel.kt)
  posts a side effect which
  [PostListView](iosApp/iosApp/features/postlist/ui/PostListView.swift)
  observes and sends to the `NavigationView`.

- The state is accessed in the screens through a Combine `@Published` observable object.

- [SwiftUI](https://developer.apple.com/xcode/swiftui/)
  is used to render layouts throughout.

## To Do

- Saved state is not currently supported
