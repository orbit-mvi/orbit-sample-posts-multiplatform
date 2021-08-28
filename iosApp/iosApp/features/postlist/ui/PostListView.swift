//
// Copyright 2021 Mikołaj Leszczyński & Appmattus Limited
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

import SwiftUI
import Combine
import shared
import sharedOrbitSwift

struct PostListView: View {

    @StateObject private var postListViewModel = ViewModels().postListViewModel().asStateObject()

    @State private var showingNavigation: ContentViewNavigation?

    var body: some View {
        NavigationView {
            ScrollView {
                VStack(alignment: .leading, spacing: 0) {
                    ForEach(postListViewModel.state.overviews, id: \.self) { overview in
                        Button(action: {
                            postListViewModel.onPostClicked(post: overview)
                        }) {
                            PostListItemView(postOverview: overview)
                                .padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
                        }
                    }
                }
            }
            .navigationBarTitle("Orbit Posts Sample")
            .navigationBarHidden(true)
            .navigation(item: $showingNavigation, destination: presentNavigation)
            .onReceive(postListViewModel.sideEffect, perform: { navigationEvent in
                if (navigationEvent is OpenPostNavigationEvent) {
                    showingNavigation = .postDetails(post: (navigationEvent as! OpenPostNavigationEvent).post)
                }

            })
        }
    }

    @ViewBuilder
    func presentNavigation(_ navigation: ContentViewNavigation) -> some View {
      switch navigation {
      case .postDetails(let post):
        PostDetailsView.create(postOverview: post)
      }
    }

    enum ContentViewNavigation {
      case postDetails(post: PostOverview)
    }
}

/*struct PostListView_Previews: PreviewProvider {
    static var previews: some View {
        TextView("")
        //PostListView()
    }
}*/
