package com.example.postulaciones

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.loader.content.CursorLoader
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postulaciones.Api.Has_puesto
import com.example.postulaciones.Api.NomalResponse
import com.example.postulaciones.Api.Registerclasss
import com.example.postulaciones.custom.reciclerview.PuestosAdapter
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.File

import retrofit2.http.Part
import java.io.IOException

import javax.sql.StatementEvent

import retrofit2.http.Multipart
import okhttp3.MultipartBody

import okhttp3.RequestBody









class register : AppCompatActivity(),View.OnClickListener {
    var images= arrayListOf<Uri>()
    var imgbFiles:ImageView?=null
    var nombre:EditText?=null
    var app:EditText?=null
    var apm:EditText?=null
    var email:EditText?=null
    var calle:EditText?=null
    var Numero:EditText?=null
    var colonia:EditText?=null
    var cp:EditText?=null
    var phone:EditText?=null
    var rfc:EditText?=null
    var puesto:Spinner?=null
    var btnregistrar:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nombre=findViewById(R.id.edtNombre)
        app=findViewById(R.id.edtApellidop)
        apm=findViewById(R.id.edtApellidom)
        email=findViewById(R.id.edtEmailp)
        calle=findViewById(R.id.edtCalle)
        Numero=findViewById(R.id.edtNumero)
        colonia=findViewById(R.id.edtColonia)
        cp=findViewById(R.id.edtCp)
        phone=findViewById(R.id.edtPhone)
        rfc=findViewById(R.id.edtRfc)
        puesto=findViewById(R.id.spPuesto)
        btnregistrar=findViewById(R.id.btnSendRegister)

        imgbFiles=findViewById(R.id.imbFiles)

        imgbFiles?.setOnClickListener(this)
        btnregistrar?.setOnClickListener(this)
        loadSpinner()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imbFiles->{
                openImageChooser()
            }
            R.id.btnSendRegister->{
                val r: ResponseApi = ResponseApi();
                val p:Has_puesto= puesto?.selectedItem as Has_puesto
                val multipartBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("nombre", nombre?.text.toString())
                    .addFormDataPart("app", app?.text.toString())
                    .addFormDataPart("apm",apm?.text.toString())
                    .addFormDataPart("calle", calle?.text.toString())
                    .addFormDataPart("numero",Numero?.text.toString())
                    .addFormDataPart("col", colonia?.text.toString())
                    .addFormDataPart("cp",cp?.text.toString())
                    .addFormDataPart("email", email?.text.toString())
                    .addFormDataPart("phone",phone?.text.toString())
                    .addFormDataPart("puesto", p!!.id.toString())
                    .addFormDataPart("rfc",rfc?.text.toString())
                var parts: MutableList<MultipartBody.Part> = ArrayList()
                for (i in 0 until images.size) {
                    parts!!.add(prepairFiles("nameFile[]", images!!.get(i)))
                    //val st: String? =getContentResolver().getType(images!!.get(i))
                    //Toast.makeText(applicationContext,st,Toast.LENGTH_LONG).show()
                    //multipartBody.addFormDataPart("nameFile[]",file.name, RequestBody.create(
                    //    st!!.toMediaTypeOrNull(), file))
                }
                val requestBody: MultipartBody = multipartBody.build()
                Log.d("count",(parts!!.size).toString());
                //val nombrerl:RequestBody=RequestBody.create("text/plain".toMediaTypeOrNull(), nombre?.text.toString())
                //val puestol:RequestBody=RequestBody.create("text/plain".toMediaTypeOrNull(), "1")
                val call1: Call<ResponseBody> =r.getClient()!!.register(nombre?.text.toString(),
                        app?.text.toString(),apm?.text.toString(),calle?.text.toString(),
                        Numero?.text.toString(),colonia?.text.toString(),cp?.text.toString(),email?.text.toString(),
                        phone?.text.toString(),p?.id,rfc?.text.toString(),parts)
                call1.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        Toast.makeText(applicationContext,"Se ha enviado su postulación",Toast.LENGTH_LONG).show()
                        val intt:Intent= Intent(applicationContext,MainActivity::class.java)
                        startActivity(intt)
                        finish()
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(applicationContext,call.toString(),Toast.LENGTH_LONG).show()
                        Toast.makeText(applicationContext,t.stackTrace.toString(),Toast.LENGTH_LONG).show()
                    }

                })

            }
        }
    }

    fun getRealPath(context: Context, fileUri: Uri): String? {
        // SDK >= 11 && SDK < 19
        return if (Build.VERSION.SDK_INT < 19) {
            getRealPathFromURIAPI11to18(context, fileUri)
        } else {
            getRealPathFromURIAPI19(context, fileUri)
        }// SDK > 19 (Android 4.4) and up
    }

    @SuppressLint("NewApi")
    fun getRealPathFromURIAPI11to18(context: Context, contentUri: Uri): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        var result: String? = null

        val cursorLoader = CursorLoader(context, contentUri, proj, null, null, null)
        val cursor = cursorLoader.loadInBackground()

        if (cursor != null) {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            result = cursor.getString(columnIndex)
            cursor.close()
        }
        return result
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author Niks
     */
    @SuppressLint("NewApi")
    fun getRealPathFromURIAPI19(context: Context, uri: Uri): String? {

        val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]

                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                var cursor: Cursor? = null
                try {
                    cursor = context.contentResolver.query(uri, arrayOf(MediaStore.MediaColumns.DISPLAY_NAME), null, null, null)
                    cursor!!.moveToNext()
                    val fileName = cursor.getString(0)
                    val path = Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName
                    if (!TextUtils.isEmpty(path)) {
                        return path
                    }
                } finally {
                    cursor?.close()
                }
                val id = DocumentsContract.getDocumentId(uri)
                if (id.startsWith("raw:")) {
                    return id.replaceFirst("raw:".toRegex(), "")
                }
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads"), java.lang.Long.valueOf(id))

                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]

                var contentUri: Uri? = null
                when (type) {
                    "image" -> contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    "video" -> contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    "audio" -> contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }

                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])

                return getDataColumn(context, contentUri, selection, selectionArgs)
            }// MediaProvider
            // DownloadsProvider
        } else if ("content".equals(uri.scheme!!, ignoreCase = true)) {

            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme!!, ignoreCase = true)) {
            return uri.path
        }// File
        // MediaStore (and general)

        return null
    }
    private fun getDataColumn(context: Context, uri: Uri?, selection: String?,
                              selectionArgs: Array<String>?): String? {

        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)

        try {
            cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }
    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }
    private fun prepairFiles(partName: String, fileUri: Uri): MultipartBody.Part {
        val file = File(getRealPathFromURIAPI19(applicationContext,fileUri))
        val st: String? =getContentResolver().getType(fileUri)
        val requestBody: RequestBody = RequestBody.create(st!!.toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData(partName, file.name, requestBody)
    }


    private fun loadSpinner(){
        val r: ResponseApi = ResponseApi();
        //Toast.makeText(root.context,"Bearer "+preference!!.get_value_string("token").toString(),Toast.LENGTH_LONG).show()
        val call1: Call<ArrayList<Has_puesto>> =
            r.getClient()!!.puestos("Bearer ")
        call1.enqueue(object : Callback<ArrayList<Has_puesto>> {
            override fun onResponse(
                call: Call<ArrayList<Has_puesto>>,
                response: Response<ArrayList<Has_puesto>>
            ) {
                val adapter=ArrayAdapter(applicationContext,R.layout.support_simple_spinner_dropdown_item,response.body()!!.toArray())
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                puesto?.adapter=adapter
            }

            override fun onFailure(call: Call<ArrayList<Has_puesto>>, t: Throwable) {
                Toast.makeText(applicationContext,"Error al buscar los puestos",Toast.LENGTH_LONG).show()
            }

        });
    }
    private fun openImageChooser() {
        val gallery = Intent(Intent.ACTION_PICK).apply {
            type = "*/*"
        }
        startActivityForResult(gallery,101)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 101) {
            data?.data?.let { images.add(it) }

        }
    }


}