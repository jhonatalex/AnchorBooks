package com.example.anchorbooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.anchorbooks.Model.Local.Book
import kotlinx.android.synthetic.main.item_books.view.*

class AdapterBooks (val callback: DataBook):RecyclerView.Adapter<AdapterBooks.BookViewHolder>() {

    private var listBooks=  emptyList<Book>()

    fun updateAdapter(list:List<Book>){
        listBooks=list;
        notifyDataSetChanged()
    }



    inner class BookViewHolder(itemVista: View): RecyclerView.ViewHolder (itemVista){

        val itemImage: ImageView =itemVista.portada_img
        val itemTitle: TextView =itemVista.text_title
        val itemSubtitle: TextView =itemVista.text_subtitle

        val click= itemVista.setOnClickListener {
            callback.passItem(listBooks[adapterPosition])

        }
    }


    interface  DataBook{
        fun passItem(books: Book)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_books, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        holder.itemTitle.text=listBooks[position].title
        holder.itemSubtitle.text=listBooks[position].author

        val image=listBooks[position].imageLink
        Glide.with(holder.itemView.context).load(image)
            .fitCenter()
            .transform(RoundedCorners(10))
            .into(holder.itemImage)


    }

    override fun getItemCount()=listBooks.size


}