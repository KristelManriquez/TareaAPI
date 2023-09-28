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
            .baseUrl("https://dummyjson.com/products/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // Hacer la llamada a la API
        val call = apiService.getProducts()

        call.enqueue(object : Callback<List<ProductClass>> {
            override fun onResponse(call: Call<List<ProductClass>>, response: Response<List<ProductClass>>) {
                if (response.isSuccessful) {
                    val products = response.body()
                    if (products != null) {
                        for (product in products) {
                            // Aquí puedes acceder a cada producto individualmente
                            // Por ejemplo, puedes imprimir el nombre y el precio de cada producto
                            Log.i("Información", "Id: ${product.id}")
                            Log.i("Información", "Título: ${product.title}")
                            Log.i("Información", "Marca: ${product.brand}")
                        }
                    } else {
                        Log.e("ErrorDelPrograma", "La respuesta es null")
                    }

                } else {
                    // Manejar errores en la respuesta
                    Log.e("Error de respuesta", "Error en la respuesta de la API")
                    Log.w("Advertencia", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ProductClass>>, t: Throwable) {
                //Manejar errores de conexión
                Log.e("Error de conexión", "Error en la llamada")
            }
        })
    }
}