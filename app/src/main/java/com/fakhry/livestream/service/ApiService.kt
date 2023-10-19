package com.fakhry.livestream.service

import com.fakhry.livestream.factory.HttpFactory
import com.fakhry.livestream.service.response.CreateActivityResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST(".")
    suspend fun createActivityApiV2(
        @Query("Action") action: String = "CreateActivityAPIV2",
        @Query("Version") limit: String = "2020-06-01",
        @Body params: RequestBody,
    ): CreateActivityResponse

    companion object {
        fun createApiService(/*context: Context*/): ApiService {
            return HttpFactory.createRetrofit(
                HttpFactory.createOkhttpClient(
                    HttpFactory.createLoggingInterceptor(),
                    /*HttpFactory.crateChuckerInterceptor(context)*/
                )
            ).create(ApiService::class.java)
        }
    }
}