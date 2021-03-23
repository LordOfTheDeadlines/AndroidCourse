package com.abramchuk.task4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtObjectsFragment : Fragment(), ArtObjectClickListener {
    var navController: NavController?=null
    private lateinit var dep_id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dep_id = requireArguments().getString("dep_id").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("TEST_RESP", dep_id)
            val listResponse = ApiService.instance().getArtObjectsByDepartmentId(dep_id.toInt())

            withContext(Dispatchers.Main) {
                val art_objects = listResponse.body()!!.art_objects_ids
                Log.d("TEST_RESP", art_objects.toString())
                val rv = view.findViewById<RecyclerView>(R.id.listRv_art_objects)
                rv.layoutManager = LinearLayoutManager(context)
                val adapter = ArtObjectAdapter(this@ArtObjectsFragment)
                rv.adapter = adapter
                adapter.update(art_objects)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_art_objects, container, false)
    }

    override fun onCellClickListener(item: ArtObject, position: Int) {
        Toast.makeText(this.context,item.id.toString(), Toast.LENGTH_SHORT).show()
    }
}