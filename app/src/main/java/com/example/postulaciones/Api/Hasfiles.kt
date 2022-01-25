package com.example.postulaciones.Api

import com.google.gson.annotations.SerializedName

data class Hasfiles (

    @SerializedName("id") val id : Int,
    @SerializedName("id_prospect") val id_prospect : Int,
    @SerializedName("src") val src : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String
)