package com.abramchuk.task4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DepartmentsFragment : Fragment(), DepartmentClickListener{
    var navController: NavController?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        GlobalScope.launch(Dispatchers.IO) {
            val response = ApiService.instance().getArtObject(1)
            Log.d("TEST_RESP", response.body().toString())

            val listResponse = ApiService.instance().getDepartments()

            withContext(Dispatchers.Main) {
                val departments = listResponse.body()!!.departments
                Log.d("TEST_RESP", departments.toString())
                val rv = view.findViewById<RecyclerView>(R.id.listRv)
                rv.layoutManager = LinearLayoutManager(context)
                val adapter = DepartmentAdapter(this@DepartmentsFragment)
                rv.adapter = adapter
                adapter.update(departments)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_departments, container, false)
    }

    override fun onCellClickListener(item: Department, position: Int) {
        Toast.makeText(this.context,item.id.toString(), Toast.LENGTH_SHORT).show()
        val bundle = bundleOf("dep_id" to item.id.toString())
        navController!!.navigate(
            R.id.action_departmentsFragment_to_artObjectsFragment,
            bundle
        )
    }

}
