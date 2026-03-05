package com.example.calculator.data.remote.ResponseData

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("country") val country: String?,
    @SerializedName("status") val status: String?
)