package com.example.apirestexamen2t

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size


    fun updateData(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)
        private val productTitle: TextView = itemView.findViewById(R.id.productName)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)

        fun bind(product: Product) {
            productTitle.text = product.name
            productPrice.text = "${product.price} €"
            Picasso.get()
                .load(product.image)
                .into(productImage, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                    }
                    // si no se carga la foto, pone un placeholder en su lugar.
                    override fun onError(e: Exception?) {
                        e?.printStackTrace()
                        productImage.setImageResource(R.drawable.placeholder)
                    }
                })
        }
    }
}

