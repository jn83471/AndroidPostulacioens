package com.example.postulaciones

import android.text.Editable
import com.example.postulaciones.Api.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    fun register(@Part("nombre") nombre:RequestBody,
                 @Part("app") app:RequestBody,
                 @Part("apm") apm:RequestBody,
                 @Part("calle") calle:RequestBody,
                 @Part("numero") numero:RequestBody,
                 @Part("col") col:RequestBody,
                 @Part("cp") cp:RequestBody,
                 @Part("email") email:RequestBody,
                 @Part("phone") phone:RequestBody,
                 @Part("puesto") puesto:Int,
                 @Part("rfc") rfc:RequestBody,
                 @Part nameFile:List<MultipartBody.Part>): Call<ResponseBody>

}