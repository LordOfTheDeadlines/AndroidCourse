package com.abramchuk.authorization

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.abramchuk.authorization.databinding.FragmentGreetingBinding

private val SHARED_PREF_KEY = "mypref"
private var KEY_LOGIN: String? = "login"

class GreetingFragment : Fragment() {
    private var binding: FragmentGreetingBinding? = null
    var navController: NavController?=null
    private lateinit var username: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = activity?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        username = sharedPref?.getString(KEY_LOGIN, "").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val message = "Hello, $username"
        binding?.usernameTextView?.text = message
        val nav_btn = binding?.buttonNavToGroupGeneratorFragment
        val logout_btn = binding?.buttonLogout
        nav_btn?.setOnClickListener {
            navController!!.navigate(
                R.id.action_greetingFragment_to_groupGeneratorFragment
            )
        }
        logout_btn?.setOnClickListener {
            with (activity?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)?.edit()) {
                this?.clear()
                this?.apply()
            }
            navController!!.navigate(
                R.id.action_greetingFragment_to_authorizationFragment
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding!!.root
    }
}