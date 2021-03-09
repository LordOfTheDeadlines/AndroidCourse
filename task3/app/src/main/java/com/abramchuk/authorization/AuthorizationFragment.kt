package com.abramchuk.authorization

import android.content.Context
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


class AuthorizationFragment : Fragment() {
    var navController: NavController?=null
    private var LOGIN: String? = "Admin"
    private var PASSWORD: String? = "admin@Huawei"
    private val SHARED_PREF_KEY = "mypref"
    private var KEY_LOGIN: String = "login"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val inputUser = view.findViewById<EditText>(R.id.login_editText)
        val inputPass = view.findViewById<EditText>(R.id.password_editText)
        val loginButton =  view.findViewById<Button>(R.id.button)
        val sharedPref = activity?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val user = sharedPref?.getString(KEY_LOGIN, null)
        if(user!=null){
            navigateUserToGreetingFragment(user)
        }
        loginButton.setOnClickListener{
            val login = inputUser.text.toString()
            val password = inputPass.text.toString()
            authorize(login, password)
        }
    }

    private fun authorize(user: String, pass: String){
        if(user==LOGIN && pass==PASSWORD){
            setUserInfo(user)
            navigateUserToGreetingFragment(user)
        }
    }

    private fun setUserInfo(user: String){
        val sharedPref = activity?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        with (sharedPref?.edit()) {
            this?.putString(KEY_LOGIN, user)
            this?.apply()
        }
    }

    private fun navigateUserToGreetingFragment(user: String){
        val bundle = bundleOf("username" to user)
        navController!!.navigate(
                R.id.action_authorizationFragment_to_greetingFragment,
                bundle
        )
    }
}