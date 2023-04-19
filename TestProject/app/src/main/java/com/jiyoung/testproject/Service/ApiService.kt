package com.jiyoung.testproject.Service

import com.jiyoung.testproject.model.FundAllModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("fund/search?id=320007")
    fun getFundData(): Call<List<FundAllModel>>
}