package com.example.postulaciones.Api

import com.google.gson.annotations.SerializedName

data class prospectsApiInterface (@SerializedName("id") val id : Int,
                                  @SerializedName("nombre") val nombre : String,
                                  @SerializedName("apellidoPaterno") val apellidoPaterno : String,
                                  @SerializedName("apellidoMaterno") val apellidoMaterno : String,
                                  @SerializedName("calle") val calle : String,
                                  @SerializedName("numero") val numero : Int,
                                  @SerializedName("colonia") val colonia : String,
                                  @SerializedName("cp") val cp : Int,
                                  @SerializedName("email") val email : String,
                                  @SerializedName("phone") val phone : String,
                                  @SerializedName("puesto") val puesto : Int,
                                  @SerializedName("rfc") val rfc : String,
                                  @SerializedName("Estatus") val estatus : Int,
                                  @SerializedName("created_at") val created_at : String,
                                  @SerializedName("updated_at") val updated_at : String,
                                  @SerializedName("has_puesto") val has_puesto : Has_puesto,
                                  @SerializedName("hasfiles") val hasfiles : List<Hasfiles> )