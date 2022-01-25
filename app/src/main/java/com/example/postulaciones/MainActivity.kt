package com.example.postulaciones

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.postulaciones.Api.userApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONObject





class MainActivity : AppCompatActivity(), View.OnClickListener{
    var tvpostulacion: TextView?=null
    var edtEmail:EditText?=null
    var edtPassword:EditText?=null
    var btnLogin:Button?=null
    var preference:PreferenceConfiguration?=null
    var tvPostulaciones:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvpostulacion=findViewById(R.id.tvPostulacion)
        edtEmail=findViewById(R.id.edtEmail)
        edtPassword=findViewById(R.id.edtPassword)
        btnLogin=findViewById(R.id.btnEntrar)
        preference=PreferenceConfiguration(applicationContext)
        tvPostulaciones=findViewById(R.id.tvPostulaciones)
        tvpostulacion?.setOnClickListener(this)
        btnLogin?.setOnClickListener(this)
        tvPostulaciones?.setOnClickListener(this)
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.MANAGE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.MANAGE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.MANAGE_EXTERNAL_STORAGE),
                    102)

                // REQUEST_CODE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        //if(preference?.get_value_string("token")!=""){
        //    val initial: Intent = Intent(applicationContext, Home::class.java)
        //    startActivity(initial)
        //}
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvPostulacion->{
                val intent:Intent= Intent(this,register::class.java);
                startActivity(intent);
            }
            R.id.tvPostulaciones->{
                val intent:Intent= Intent(this,postulantes::class.java);
                startActivity(intent);
            }
            R.id.btnEntrar-> {
                val r: ResponseApi = ResponseApi();
                val call1: Call<userApiInterface> =
                    r.getClient()!!.login(edtEmail!!.text.toString(), edtPassword!!.text.toString())
                call1.enqueue(object : Callback<userApiInterface?> {
                    override fun onResponse(
                        call: Call<userApiInterface?>,
                        response: Response<userApiInterface?>
                    ) {
                        if (response.isSuccessful) {
                            preference!!.set_value("id", response.body()!!.id)
                            preference!!.set_value("Email", response.body()!!.email)
                            preference!!.set_value("name", response.body()!!.userName)
                            preference!!.set_value("token", response.body()!!.token)
                            val initial: Intent = Intent(applicationContext, Home::class.java)
                            startActivity(initial)
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "No se ha podido identificar su usuario.",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }

                    override fun onFailure(call: Call<userApiInterface?>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "Error en el servicio",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })
            }
            else->{
                Toast.makeText(this, "Unknown action", Toast.LENGTH_LONG).show();
            }
        }
    }
}