package com.example.anchorbooks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.anchorbooks.ViewModel.AppViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_second.*
import java.lang.Exception

class SecondFragment : Fragment() {

    private val myViewModel: AppViewModel by activityViewModels()
    lateinit var bookSend: String
    private var  idBooks=0





    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        myViewModel.election.observe(viewLifecycleOwner, Observer {
            idBooks=it


            myViewModel.getBookDetail (idBooks).observe(viewLifecycleOwner, Observer {



                    Glide.with(this).load( if( it.imageLink!= null) {it.imageLink } else{ "https://www.timandorra.com/wp-content/uploads/2016/11/Imagen-no-disponible.png" }).fitCenter().into(imagePortada)

                    title_details.text = it.title
                    author_details.text=it.author
                    pages_details.text=it.pages.toString()
                    text_pais.text=it.country
                    text_lenguaje.text=it.language
                    text_year.text=it.year.toString()

                    if(it.delivery){
                        text_deli.text=getString(R.string.des)
                    }else{
                        text_deli.text= getString(R.string.Sin)
                    }

                    bookSend=it.title
                    text_precio.text=it.price.toString()


                    webView.webChromeClient = object : WebChromeClient() {

                    }

                    webView.webViewClient=object : WebViewClient(){

                    }


                    val settings=webView.settings
                    settings.javaScriptEnabled=true

                    var url = if( it.link!= null) { it.link } else{ "https://www.google.com" }
                    webView.loadUrl(url)








            })
        })


        view.findViewById<Button>(R.id.email).setOnClickListener { view ->
            val addresses="ventas@anchorBooks.cl"
            val subject=getString(R.string.Consulta)+ bookSend+ " ID " +idBooks
            val text=getString(R.string.text_mail)+bookSend+" de codigo "+ idBooks + getString(R.string.text_mail_parte2)
            bodyMail(addresses, subject, text)

        }





    }



    fun bodyMail(addresses: String, subject: String, text: String) {
        var intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(addresses))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }

    }









}