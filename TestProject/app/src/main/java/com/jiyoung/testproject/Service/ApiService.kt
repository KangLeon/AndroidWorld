package com.jiyoung.testproject.Service

import com.jiyoung.testproject.model.FundAllModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("fund/search")
    fun getFundData(@Query("key_word") key_word: String): Call<FundAllModel>
}