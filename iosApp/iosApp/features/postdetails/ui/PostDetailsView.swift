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
        
        ScrollView {
            VStack(alignment: .leading) {
                
                Text(state.postOverview.title).font(.title).padding(.bottom)
                
                if let state = state as? PostDetailState.Details {
                    
                    Text(state.post.body)
                        .font(.body)
                        .fixedSize(horizontal: false, vertical: true)
                    
                    Divider().padding(.bottom).padding(.top)
                    
                    Text(String(format:"%d comments", state.post.comments.count))
                        .font(.body)
                        .padding(.bottom)
                    
                    ForEach(state.post.comments.indices, id: \.self) { idx in
                        if idx != 0 {
                            Divider()
                                .padding(.bottom)
                                .padding(.top)
                        }
                        
                        let comment = state.post.comments[idx]
                        Text(comment.name)
                            .lineLimit(1)
                            .font(.caption2)
                        Text(comment.email)
                            .lineLimit(1)
                            .font(.caption)
                        Text(comment.body)
                            .font(.body)
                            .fixedSize(horizontal: false, vertical: true)
                    }
                }
            }
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
            .padding()
        }
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarLeading) {
                AsyncImage(
                    url: URL(string:state.postOverview.avatarUrl)!,
                    placeholder: { Circle().foregroundColor(Color.tertiarySystemGroupedBackground) },
                    image: { Image(uiImage: $0).resizable() }
                ).frame(width: 24, height: 24)
            }
            ToolbarItem(placement: .navigationBarLeading) {
                Text(state.postOverview.username)
            }
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
