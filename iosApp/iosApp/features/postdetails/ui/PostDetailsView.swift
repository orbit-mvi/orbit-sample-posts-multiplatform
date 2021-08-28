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
import shared
import sharedOrbitSwift

struct PostDetailsView: View {
    
    @StateObject private var postDetailsViewModel: PostDetailsViewModelStateObject

    var body: some View {
        let state = postDetailsViewModel.state
        Text(state.postOverview.title)
        
        if let state = state as? PostDetailState.Details {
            Text(state.post.body)
        }
    }
}

/*struct PostDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        PostDetailsView()
    }
}*/

extension PostDetailsView {
    static func create(postOverview: PostOverview) -> some View {
        let postDetailsViewModel = ViewModels().postDetailsViewModel(postOverview: postOverview).asStateObject()

        return PostDetailsView(postDetailsViewModel: postDetailsViewModel)
    }
}
