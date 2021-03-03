package com.abramchuk.authorization

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
    private var login: String? = "Admin"
    private var password: String? = "admin@Huawei"

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
        loginButton.setOnClickListener{
            val user = inputUser.text.toString()
            val pass = inputPass.text.toString()
            authorize(user, pass)
        }
    }

     private fun authorize(user: String, pass: String){
        if(user==login && pass==password){
            val bundle = bundleOf("username" to user)
            navController!!.navigate(
                    R.id.action_authorizationFragment_to_greetingFragment,
                    bundle
            )
        }
    }
}