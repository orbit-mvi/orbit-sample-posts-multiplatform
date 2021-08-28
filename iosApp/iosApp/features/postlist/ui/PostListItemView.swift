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

struct PostListItemView: View {
    var postOverview: PostOverview

    var body: some View {
        HStack(alignment: .center) {
            AsyncImage(
                url: URL(string:postOverview.avatarUrl)!,
                placeholder: { Circle().foregroundColor(Color.tertiarySystemGroupedBackground) },
                image: { Image(uiImage: $0).resizable() }
            ).frame(width: 32, height: 32)
            
            VStack(alignment: .leading) {
                Text(postOverview.username).font(.system(.caption))
                Text(postOverview.title).font(.system(.body))
            }.padding(8)
            
            Spacer()
        }
    }
}

struct PostListItemView_Previews: PreviewProvider {
    static var previews: some View {
        PostListItemView(postOverview: PostOverview(id: 0, avatarUrl: "https://upload.wikimedia.org/wikipedia/commons/a/ab/Apple-logo.png", title: "title", username: "username"))
    }
}
