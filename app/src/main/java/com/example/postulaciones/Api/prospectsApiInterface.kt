package com.example.postulaciones.Api

import com.google.gson.annotations.SerializedName

data class prospectsApiInterface (@SerializedName("id"              ) var id              : Int?                = null,
                                  @SerializedName("nombre"          ) var nombre          : String?             = null,
                                  @SerializedName("apellidoPaterno" ) var apellidoPaterno : String?             = null,
                                  @SerializedName("apellidoMaterno" ) var apellidoMaterno : String?             = null,
                                  @SerializedName("calle"           ) var calle           : String?             = null,
                                  @SerializedName("numero"          ) var numero          : String?             = null,
                                  @SerializedName("colonia"         ) var colonia         : String?             = null,
                                  @SerializedName("cp"              ) var cp              : String?             = null,
                                  @SerializedName("email"           ) var email           : String?             = null,
                                  @SerializedName("phone"           ) var phone           : String?             = null,
                                  @SerializedName("puesto"          ) var puesto          : Int?                = null,
                                  @SerializedName("rfc"             ) var rfc             : String?             = null,
                                  @SerializedName("Estatus"         ) var estatus         : Int?                = null,
                                  @SerializedName("Motive"          ) var Motive          : String?             = null,
                                  @SerializedName("created_at"      ) var createdAt       : String?             = null,
                                  @SerializedName("updated_at"      ) var updatedAt       : String?             = null,
                                  @SerializedName("has_puesto"      ) var has_puesto       : Has_puesto?,
                                  @SerializedName("hasfiles"        ) var hasfiles        : ArrayList<Hasfiles> = arrayListOf() )