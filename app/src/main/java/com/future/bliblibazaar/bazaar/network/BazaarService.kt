package com.future.bliblibazaar.bazaar.network

import com.future.bliblibazaar.BBResponse
import com.future.bliblibazaar.bazaar.model.BazaarDto
import com.future.bliblibazaar.bazaar.model.BazaarItemDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BazaarService {

    @GET("bazaars/active")
    suspend fun getActiveBazaars(): BBResponse<List<BazaarDto>>

    @GET("bazaars/{bazaarId}/items")
    suspend fun getBazaarItems(@Path("bazaarId") bazaarId: Long): BBResponse<List<BazaarItemDto>>

}