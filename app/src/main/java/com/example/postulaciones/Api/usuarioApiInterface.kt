package com.example.postulaciones.Api

import com.google.gson.annotations.SerializedName

data class usuarioApiInterface (@SerializedName("id") val id : Int,
                               @SerializedName("name") val name : String,
                               @SerializedName("email") val email : String,
                               @SerializedName("email_verified_at") val email_verified_at : String,
                               @SerializedName("created_at") val created_at : String,
                               @SerializedName("updated_at") val updated_at : String)