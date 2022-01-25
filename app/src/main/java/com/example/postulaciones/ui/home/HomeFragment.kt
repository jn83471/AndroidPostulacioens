package com.example.postulaciones.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postulaciones.Api.prospectsApiInterface
import com.example.postulaciones.Api.userApiInterface
import com.example.postulaciones.Home
import com.example.postulaciones.PreferenceConfiguration
import com.example.postulaciones.R
import com.example.postulaciones.ResponseApi
import com.example.postulaciones.custom.reciclerview.ProspectsAdapter
import com.example.postulaciones.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.DividerItemDecoration




class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    var preference: PreferenceConfiguration?=null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        preference=PreferenceConfiguration(root.context)
        val recicler:RecyclerView=root.findViewById(R.id.rvProspects)
        val r: ResponseApi = ResponseApi();
        val con:Context=root.context
        //Toast.makeText(root.context,"Bearer "+preference!!.get_value_string("token").toString(),Toast.LENGTH_LONG).show()
        val call1: Call<ArrayList<prospectsApiInterface>> =
            r.getClient()!!.prospects("Bearer "+preference!!.get_value_string("token").toString())
        call1.enqueue(object : Callback<ArrayList<prospectsApiInterface>> {
            override fun onResponse(
                call: Call<ArrayList<prospectsApiInterface>>,
                response: Response<ArrayList<prospectsApiInterface>>
            ) {
                if(response.isSuccessful()){
                    val p: ArrayList<prospectsApiInterface>? =response.body()

                    val pa:ProspectsAdapter=ProspectsAdapter(p!!,root.context)
                    recicler.setHasFixedSize(true)
                    val li:LinearLayoutManager=LinearLayoutManager(root.context)
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
                    //Toast.makeText(root.context,p.toString(),Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(root.context,response.toString(),Toast.LENGTH_LONG).show()
                }


            }

            override fun onFailure(call: Call<ArrayList<prospectsApiInterface>>, t: Throwable) {
                Toast.makeText(root.context,"Error de busqueda",Toast.LENGTH_LONG).show()
            }


        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}