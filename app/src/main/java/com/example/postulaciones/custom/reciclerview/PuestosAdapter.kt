package com.example.postulaciones.custom.reciclerview

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postulaciones.Api.Has_puesto
import com.example.postulaciones.Api.prospectsApiInterface
import com.example.postulaciones.R

class PuestosAdapter(var elementos: ArrayList<Has_puesto>, c: Context):RecyclerView.Adapter<PuestosAdapter.ContactHolder>() {

    var contexto:Context?=null
    var elements:ArrayList<Has_puesto>?=null
    var mInflater: LayoutInflater? = null
    init {
        contexto=c
        elements=elementos
        mInflater= LayoutInflater.from(contexto)
    }
    class ContactHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        val id: TextView? = v.findViewById(R.id.tvIdPuesto)
        val Nom: TextView?=v.findViewById(R.id.tvDisplayNamePuesto)
        val Estatus: TextView?=v.findViewById(R.id.tvEstatusPuesto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view:View=mInflater!!.inflate(R.layout.activity_custom_puestos,parent,false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        when(elements!!.get(position).estatus){
            0->{
                holder.Estatus?.text="Estatus: Activo"
            }
            1->{
                holder.itemView.setBackgroundColor(Color.RED)
                holder.Estatus?.text="Estatus: Desactivado"
            }
        }
        holder.id!!.text="Id: "+elements!!.get(position).id.toString();
        holder.Nom!!.text="Nombre: "+elements!!.get(position).display_name;
    }

    override fun getItemCount(): Int {
        return elements!!.size
    }
}