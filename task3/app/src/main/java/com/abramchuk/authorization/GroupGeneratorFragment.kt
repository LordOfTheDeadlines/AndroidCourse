package com.abramchuk.authorization

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.abramchuk.authorization.db.AppDatabase
import com.abramchuk.authorization.db.DinosaurDAO
import com.abramchuk.authorization.models.Dinosaur
import com.abramchuk.authorization.models.DinosaurGroupGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupGeneratorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_group_generator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val generate_btn = view.findViewById<Button>(R.id.button_generate)
        val view_btn = view.findViewById<Button>(R.id.button_view)
        val clean_btn = view.findViewById<Button>(R.id.button_clean)
        val dinosaursFromDB = view.findViewById<TextView>(R.id.textView_dbData)
        val dao = AppDatabase.createDb(requireContext()).getDinosaurDAO()

        generate_btn.setOnClickListener {
            val generator = DinosaurGroupGenerator()
            val dinosaurs = generator.createGroup(1,11)
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    for (dino in dinosaurs) {
                        dao.insert(dino)
                    }
                }
            }
        }
        view_btn.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    val dinosaurs = dao.getDinosaurs()
                    dinosaursFromDB.text = dinosaurs.toString()
                    for (dino in dinosaurs) {
                        Log.d("TEST_DB", dino.toString())
                    }
                }
            }
        }
        clean_btn.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    dao.deleteAll()
                }
            }
            dinosaursFromDB.text = ""
        }
    }
}