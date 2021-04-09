package com.abramchuk.it_bookstore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.abramchuk.it_bookstore.R

class MainPageFragment : Fragment() {
    var navController: NavController?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val inputStr = view.findViewById<EditText>(R.id.inputStr)
        val searchButton =  view.findViewById<Button>(R.id.search_btn)
        searchButton.setOnClickListener{
            val text = inputStr.text.toString()
            searchBooks(text)
        }
    }
    private fun searchBooks(text: String){
        if(text.isNotEmpty()){
            val bundle = bundleOf("title_fragment" to text)
            navController!!.navigate(
                R.id.action_mainPageFragment_to_booksFragment,
                bundle
            )
        }
    }
}