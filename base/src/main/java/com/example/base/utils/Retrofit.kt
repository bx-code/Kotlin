package com.example.base.utils

import android.telecom.Call
import retrofit2.Retrofit
import retrofit2.http.GET

interface API{
     @GET("lessons")
     fun lessons():retrofit2.Call<Any>
}

val  RETROFIT: Retrofit = Retrofit.Builder()
     .baseUrl("https://api.hencoder.com/")
     .build()

/**
 * inline 内联函数 使用函数类型作为参数 如： listener : (view :View)-> Unit
 */
inline fun <reified T> create():T{
     return RETROFIT.create(T ::class.java)
}

fun main(){
      println(create<API>())
}