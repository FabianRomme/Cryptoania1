package com.example.crypto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace


class LoginFragment : Fragment() {

    lateinit var signInButton: Button
    lateinit var userName: EditText
    lateinit var passWord: EditText
    //lateinit var fragmentChatrooms: ChatroomsFragment
    var userNameCode = "Crypto"
    var passwordCode = "admin"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButton = requireView().findViewById(R.id.buttonSignIn)
        userName = view?.findViewById(R.id.editTextTextPersonName)!!
        passWord = view?.findViewById(R.id.editTextTextPassword)!!


        signInButton.setOnClickListener(){
            val userNameText = userName.text.toString()
            val passWordText = passWord.text.toString()

            if (userNameText == userNameCode && passWordText == passwordCode){
                //change fragment to after log in screen to chatroomsFragment


                    activity?.supportFragmentManager?.commit{
                    setReorderingAllowed(true)
                    replace<OverviewFragment>(R.id.Main_frame)
                }


            } else if (userNameText != userNameCode && passWordText != passwordCode){
                Toast.makeText(this.requireContext(), "Wrong username or password", Toast.LENGTH_SHORT).show()
            }   else if (userNameText.isEmpty()  || passWordText.isEmpty() ){
                Toast.makeText(this.requireContext(), "You need to put in information", Toast.LENGTH_SHORT).show()

            }
        }



    }

}