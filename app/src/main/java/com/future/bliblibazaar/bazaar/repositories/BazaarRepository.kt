package com.future.bliblibazaar.bazaar.repositories

import com.future.bliblibazaar.BBResponse
import com.future.bliblibazaar.bazaar.model.BazaarDto
import com.future.bliblibazaar.bazaar.model.BazaarItemDto
import com.future.bliblibazaar.network.RetrofitClient

class BazaarRepository {
    private val bazaarService = RetrofitClient.createBazaarService()

    suspend fun getBazaars(): BBResponse<List<BazaarDto>> {
        return bazaarService.getActiveBazaars()
    }

    suspend fun getBazaarItems(bazaarId: Long): BBResponse<List<BazaarItemDto>> {
        return bazaarService.getBazaarItems(bazaarId)
    }

}