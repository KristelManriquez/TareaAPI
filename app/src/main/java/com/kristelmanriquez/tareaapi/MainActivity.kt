package com.kristelmanriquez.tareaapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // Hacer la llamada a la API
        val call = apiService.getProducts()

        call.enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful) {
                    val productData = response.body()
                    if (productData != null) {
                        Log.i("Éxito en la consulta de la API", "Id: ${productData.products}")
                    }
                } else {
                    // Manejar errores en la respuesta
                    Log.e("Error de respuesta", "Error en la respuesta de la API")
                    Log.w("Advertencia", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                //Manejar errores de conexión
                Log.e("Error de conexión", "Error en la llamada")
            }

        })
    }
}