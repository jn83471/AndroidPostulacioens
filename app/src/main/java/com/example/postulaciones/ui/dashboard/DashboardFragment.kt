package com.example.postulaciones.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postulaciones.Api.Has_puesto
import com.example.postulaciones.Api.prospectsApiInterface
import com.example.postulaciones.PreferenceConfiguration
import com.example.postulaciones.R
import com.example.postulaciones.ResponseApi
import com.example.postulaciones.custom.reciclerview.ProspectsAdapter
import com.example.postulaciones.custom.reciclerview.PuestosAdapter
import com.example.postulaciones.databinding.FragmentDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    var preference: PreferenceConfiguration?=null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        preference=PreferenceConfiguration(root.context)
        val recicler: RecyclerView =root.findViewById(R.id.rvPuestos)
        val r: ResponseApi = ResponseApi();
        val con: Context =root.context
        //Toast.makeText(root.context,"Bearer "+preference!!.get_value_string("token").toString(),Toast.LENGTH_LONG).show()
        val call1: Call<ArrayList<Has_puesto>> =
            r.getClient()!!.puestos("Bearer "+preference!!.get_value_string("token").toString())
        call1.enqueue(object : Callback<ArrayList<Has_puesto>> {
            override fun onResponse(
                call: Call<ArrayList<Has_puesto>>,
                response: Response<ArrayList<Has_puesto>>
            ) {
                val p: ArrayList<Has_puesto>? =response.body()
                val pa: PuestosAdapter = PuestosAdapter(p!!,root.context)
                recicler.setHasFixedSize(true)
                val li: LinearLayoutManager = LinearLayoutManager(root.context)
                val dividerItemDecoration = DividerItemDecoration(
                    recicler.getContext(),
                    li.getOrientation()
                )
                recicler.isClickable=true
                recicler.addItemDecoration(dividerItemDecoration)
                recicler.layoutManager=li

                recicler.adapter=pa
                if(recicler==null){
                    Log.d("lllll","es nulo")
                }
            }

            override fun onFailure(call: Call<ArrayList<Has_puesto>>, t: Throwable) {
                Toast.makeText(root.context,"Erro al buscar los puestos",Toast.LENGTH_LONG).show()
            }

        });
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}