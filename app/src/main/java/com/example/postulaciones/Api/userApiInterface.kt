package com.example.postulaciones.Api

import com.google.gson.annotations.SerializedName

data class userApiInterface (@SerializedName("name") val userName:String,
                             @SerializedName("email") val email:String,
                             @SerializedName("id") val id:Int,
                            @SerializedName("access_token") val token:String)