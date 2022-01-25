package com.example.postulaciones

import android.text.Editable
import com.example.postulaciones.Api.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("/api/user")
    fun login(@Field("user") user: String, @Field("pass") pass:String): Call<userApiInterface>
    @Headers("Accept: application/vnd.yourapi.v1.full+json",
        "User-Agent: Your-App-Name")
    @POST("/api/prospects")
    fun prospects(@Header("Authorization") token:String): Call<ArrayList<prospectsApiInterface>>
    @Headers("Accept: application/vnd.yourapi.v1.full+json",
        "User-Agent: Your-App-Name")
    @POST("/api/puestos")
    fun puestos(@Header("Authorization") token:String): Call<ArrayList<Has_puesto>>
    @Headers("Accept: application/vnd.yourapi.v1.full+json",
        "User-Agent: Your-App-Name")
    @POST("/api/usuarios")
    fun usuarios(@Header("Authorization") token:String): Call<ArrayList<usuarioApiInterface>>

    @Multipart
    @Headers("Accept: application/vnd.yourapi.v1.full+json",
        "User-Agent: Your-App-Name")
    @POST("/api/register")
    fun register(@Part("nombre") nombre:String,
                 @Part("app") app:String,
                 @Part("apm") apm:String,
                 @Part("calle") calle:String,
                 @Part("numero") numero:String,
                 @Part("col") col:String,
                 @Part("cp") cp:String,
                 @Part("email") email:String,
                 @Part("phone") phone:String,
                 @Part("puesto") puesto:Int,
                 @Part("rfc") rfc:String,
                 @Part nameFile:List<MultipartBody.Part>): Call<ResponseBody>

}