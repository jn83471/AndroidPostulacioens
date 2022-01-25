package com.example.postulaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postulaciones.Api.prospectsApiInterface
import com.example.postulaciones.custom.reciclerview.ProspectsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class postulantes : AppCompatActivity() {
    var rvPostulaciones:RecyclerView?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postulantes)
        rvPostulaciones = findViewById(R.id.rvPostulaciones)

        val r: ResponseApi = ResponseApi();
        val call1: Call<ArrayList<prospectsApiInterface>> =
            r.getClient()!!.prospects("Bearer " + "")
        call1.enqueue(object : Callback<ArrayList<prospectsApiInterface>> {
            override fun onResponse(
                call: Call<ArrayList<prospectsApiInterface>>,
                response: Response<ArrayList<prospectsApiInterface>>
            ) {
                if (response.isSuccessful()) {
                    val p: ArrayList<prospectsApiInterface>? = response.body()

                    val pa: ProspectsAdapter = ProspectsAdapter(p!!, applicationContext)
                    rvPostulaciones?.setHasFixedSize(true)
                    val li: LinearLayoutManager = LinearLayoutManager(applicationContext)
                    val dividerItemDecoration = DividerItemDecoration(
                        rvPostulaciones?.getContext(),
                        li.getOrientation()
                    )
                    rvPostulaciones?.isClickable = true
                    rvPostulaciones?.addItemDecoration(dividerItemDecoration)
                    rvPostulaciones?.layoutManager = li
                    rvPostulaciones?.adapter = pa

                    //Toast.makeText(root.context,p.toString(),Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_LONG)
                        .show()
                }


            }

            override fun onFailure(call: Call<ArrayList<prospectsApiInterface>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error de busqueda", Toast.LENGTH_LONG).show()
            }


        })
    }
}