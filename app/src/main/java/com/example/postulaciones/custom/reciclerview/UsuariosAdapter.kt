package com.example.postulaciones.custom.reciclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postulaciones.Api.Has_puesto
import com.example.postulaciones.Api.usuarioApiInterface
import com.example.postulaciones.R

class UsuariosAdapter(var elementos: ArrayList<usuarioApiInterface>, c: Context):RecyclerView.Adapter<UsuariosAdapter.ContactHolder>() {

    var contexto:Context?=null
    var elements:ArrayList<usuarioApiInterface>?=null
    var mInflater: LayoutInflater? = null
    init {
        contexto=c
        elements=elementos
        mInflater= LayoutInflater.from(contexto)
    }
    class ContactHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        val id: TextView? = v.findViewById(R.id.tvIdUsuario)
        val Nom: TextView?=v.findViewById(R.id.tvNombreUsuario)
        val email: TextView?=v.findViewById(R.id.tvEmailUsuario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view:View=mInflater!!.inflate(R.layout.activity_custom_usuarios,parent,false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.id!!.text="Id: "+elements!!.get(position).id.toString();
        holder.Nom!!.text="Nombre: "+elements!!.get(position).name;
        holder.email!!.text="Email: "+elements!!.get(position).email;
    }

    override fun getItemCount(): Int {
        return elements!!.size
    }
}