package com.example.postulaciones.Api

import okhttp3.MultipartBody
import retrofit2.http.Multipart

class Registerclasss {
    private var nombre:String?=null
    private var app:String?=null
    private var apm:String?=null
    private var calle:String?=null
    private var numero:String?=null
    private var col:String?=null
    private var cp:String?=null
    private var email:String?=null
    private var phone:String?=null
    private var puesto:Int?=null
    private var rfc:String?=null
    private var grant_type="password"
    private var client_id="2"
    private var client_secret="LYmFJusiC5doqlM5dHGN3Efb0eSGmcPWJZw0Fkqk"
    private var scope="'*'"

    constructor(
        nombre: String?,
        app: String?,
        apm: String?,
        calle: String?,
        numero: String?,
        col: String?,
        cp: String?,
        email: String?,
        phone: String?,
        puesto: Int?,
        rfc: String?
    ) {
        this.nombre = nombre
        this.app = app
        this.apm = apm
        this.calle = calle
        this.numero = numero
        this.col = col
        this.cp = cp
        this.email = email
        this.phone = phone
        this.puesto = puesto
        this.rfc = rfc
    }
}