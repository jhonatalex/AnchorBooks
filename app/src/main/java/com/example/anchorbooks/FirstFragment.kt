package com.example.anchorbooks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anchorbooks.Model.Local.Book
import com.example.anchorbooks.ViewModel.AppViewModel
import kotlinx.android.synthetic.main.fragment_first.view.*


class FirstFragment : Fragment(), AdapterBooks.DataBook {


    private val myViewModel: AppViewModel by activityViewModels()
    lateinit var myAdapter: AdapterBooks


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        myAdapter= AdapterBooks(this)
        val recyclerView=view.recyclerViewBook
        recyclerView.layoutManager=  LinearLayoutManager(context)
        recyclerView.adapter=myAdapter



        myViewModel.viewListBooks.observe(viewLifecycleOwner, Observer {

            myAdapter.updateAdapter(it)
        })








    }

    override fun passItem(book: Book) {

        myViewModel.bookDetailSelect(book.id)

        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}