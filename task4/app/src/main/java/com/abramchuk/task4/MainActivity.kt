package com.abramchuk.task4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.IO) {
            val response = ApiService.instance().getArtObject(1)
            Log.d("TEST_RESP", response.body().toString())
            val listResponse = ApiService.instance().getDepartments()
            withContext(Dispatchers.Main) {
                val departments = listResponse.body()!!.departments
                Log.d("TEST_RESP", departments.toString())
                val rv = findViewById<RecyclerView>(R.id.listRv)
                rv.layoutManager = LinearLayoutManager(baseContext)
                val adapter = DepartmentAdapter()
                rv.adapter = adapter
                adapter.update(departments)
            }
        }
    }
}