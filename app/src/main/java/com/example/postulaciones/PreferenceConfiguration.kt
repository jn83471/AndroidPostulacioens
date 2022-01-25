package com.example.postulaciones

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferenceConfiguration {
    var preference:SharedPreferences?=null;
    constructor(context:Context){
        preference=PreferenceManager.getDefaultSharedPreferences(context,);
    }
    fun set_value(key:String,value:Int){
        val edit: SharedPreferences.Editor? = preference?.edit();
        edit?.putInt(key,value);
        edit?.commit()
    }
    fun set_value(key:String,value:String){
        val edit: SharedPreferences.Editor? = preference?.edit();
        edit?.putString(key,value);
        edit?.commit()
    }
    fun get_value_int(key:String): Int? {
        return preference?.getInt(key,-1)
    }
    fun get_value_string(key:String): String? {
        return preference?.getString(key,"")
    }
    fun clear(){
        preference?.edit()?.clear()?.commit();
    }
}