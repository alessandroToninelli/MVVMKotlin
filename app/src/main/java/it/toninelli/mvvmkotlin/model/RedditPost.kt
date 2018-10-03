/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.toninelli.mvvmkotlin.model


import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.ThreadLocalRandom


data class RedditPost(

        @SerializedName("name")
        val name: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("score")
        val score: Int,
        @SerializedName("author")
        val author: String,
        @SerializedName("subreddit") // this seems mutable but fine for a demo

        val subreddit: String,
        @SerializedName("num_comments")
        val num_comments: Int,
        @SerializedName("created_utc")
        val created: Long,
        val thumbnail: String?,
        val url: String?


)



{


    // to be consistent w/ changing backend order, we need to keep a data like this
    var indexInResponse: Int = -1
}

fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) +  start

class ListingResponse(val data: ListingData)


class ListingData(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)

data class RedditChildrenResponse(val data: RedditPost)