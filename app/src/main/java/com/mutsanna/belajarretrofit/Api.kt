package com.mutsanna.belajarretrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("posts")
    fun getPost(): Call<ArrayList<PostResponse>>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") text: String
    ): Call<CreatePostResponse>
}