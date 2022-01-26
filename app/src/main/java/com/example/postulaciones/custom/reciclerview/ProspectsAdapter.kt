package com.example.postulaciones.custom.reciclerview

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.example.postulaciones.Api.prospectsApiInterface
import com.example.postulaciones.R


class ProspectsAdapter(var elementos: ArrayList<prospectsApiInterface>,c:Context):RecyclerView.Adapter<ProspectsAdapter.ContactHolder>() {

    var contexto:Context?=null
    var elements:ArrayList<prospectsApiInterface>?=null
    var mInflater: LayoutInflater? = null
    init {
        contexto=c
        elements=elementos
        mInflater= LayoutInflater.from(contexto)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view:View=mInflater!!.inflate(R.layout.activity_postulaciones,parent,false)
        return ContactHolder(view)
    }



    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        when(elements!!.get(position).estatus){
            1->{
                holder.itemView.setBackgroundColor(Color.GREEN)
                holder.Estatus?.text="Estatus: Aceptado"
            }
            2->{
                holder.itemView.setBackgroundColor(Color.RED)
                holder.Estatus?.text="Estatus: Rechazado"
            }
            else->{
                holder.itemView.setBackgroundColor(Color.TRANSPARENT)
                holder.Estatus?.text="Estatus: Enviado"
            }
        }
        holder.id?.text="Id: "+elements!!.get(position).id.toString();
        holder.Nom?.text="Nombre: "+elements!!.get(position).apellidoPaterno.toString()+" "+elements!!.get(position).apellidoMaterno.toString()+" "+elements!!.get(position).nombre.toString();
        holder.Calle?.text="Calle: "+elements!!.get(position).calle.toString();
        holder.Num?.text="Número: "+elements!!.get(position).numero.toString();
        holder.Colonia?.text="Colonia: "+elements!!.get(position).colonia.toString();
        holder.Cp?.text="Código postal: "+elements!!.get(position).cp.toString();
        holder.Email?.text="Email: "+elements!!.get(position).email.toString();
        holder.Telefono?.text="Teléfono: "+elements!!.get(position).phone.toString();
        holder.Rfc?.text="RFC: "+elements!!.get(position).rfc.toString();
        holder.Puesto?.text="Puesto: "+elements!!.get(position).has_puesto?.display_name.toString();
    }

    override fun getItemCount(): Int {
        return elements!!.size
    }
    class ContactHolder(v:View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        val id: TextView? = v.findViewById(R.id.tvIdPostulacion)
        val Nom:TextView?=v.findViewById(R.id.tvNombrePostulacion)
        val Calle:TextView?=v?.findViewById(R.id.tvCallePostulacion)
        val Num:TextView?=v.findViewById(R.id.tvNumeroPostulacion)
        val Colonia:TextView?=v.findViewById(R.id.tvColoniaPostulacion)
        val Cp:TextView?=v.findViewById(R.id.tvCpPostulacion)
        val Email:TextView?=v.findViewById(R.id.tvEmailPostulacion)
        val Telefono:TextView?=v.findViewById(R.id.tvTelefonoPostulacion)
        val Rfc:TextView?=v.findViewById(R.id.tvRfcPostulacion)
        val Puesto:TextView?=v.findViewById(R.id.tvPuestoPostulacion)
        val Estatus:TextView?=v.findViewById(R.id.tvEstatusPostulacion)
    }


}