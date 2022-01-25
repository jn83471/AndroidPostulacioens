package com.example.postulaciones.Api

import com.google.gson.annotations.SerializedName

data class Has_puesto (

    @SerializedName("id") val id : Int,
    @SerializedName("display_name") val display_name : String,
    @SerializedName("Estatus") val estatus : Int,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String
){
    override fun toString(): String {
        return display_name;
    }
}