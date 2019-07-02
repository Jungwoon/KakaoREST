package com.byjw.jungwoon.retrofit.base

import com.byjw.jungwoon.retrofit.gson.Image
import com.byjw.jungwoon.retrofit.gson.Video
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {

    @Headers("Authorization: KakaoAK e2978f4fa4734c953c064731d5f8975f")
    @GET("/v2/search/image")
    fun requestSearchImage(
        @Query("query") keyword: String,
        @Query("sort") sort: String = "recency",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80
    ): Call<Image>

    @Headers("Authorization: KakaoAK e2978f4fa4734c953c064731d5f8975f")
    @GET("/v2/search/vclip")
    fun requestSearchVideo(
        @Query("query") keyword: String,
        @Query("sort") sort: String = "recency",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 15
    ): Call<Video>

}