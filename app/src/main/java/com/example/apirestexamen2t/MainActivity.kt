package com.example.apirestexamen2t

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)


        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(emptyList())
        recyclerView.adapter = productAdapter
        fetchData()

    }

    private fun fetchData() {
        lifecycleScope.launch {
            try {
                progressBar.visibility = View.VISIBLE  // Muestra el ProgressBar

                val productResponse = withContext(Dispatchers.IO) {
                    val response = RetrofitClient.apiService.getProducts()
                    Log.d("API_RESPONSE", response.toString())
                    response
                }


                val products = productResponse.results
                withContext(Dispatchers.Main) {
                    productAdapter.updateData(products)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error al cargar los productos: ${e.message}", Toast.LENGTH_LONG).show()
                }
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }
}
