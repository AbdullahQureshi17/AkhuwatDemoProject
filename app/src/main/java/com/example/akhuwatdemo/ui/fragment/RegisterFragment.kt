package com.example.akhuwatdemo.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.api.RetrofitClient
import com.example.akhuwatdemo.database.AppDatabase
import com.example.akhuwatdemo.model.DefaultResponse
import com.example.akhuwatdemo.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 Created by Abdullah
 */
class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var btnregister : Button
    private lateinit var etusername : EditText
    private lateinit var etpassword : EditText
    private lateinit var etfirstname : EditText
    private lateinit var etlastname : EditText
    var isAllowed : Boolean = false
    private lateinit var tvLogin : TextView



    private lateinit var db : AppDatabase



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        btnregister = view.findViewById(R.id.registerBtn)
        etusername = view.findViewById(R.id.et_username)
        etpassword = view.findViewById(R.id.et_password)
        etfirstname = view.findViewById(R.id.et_firstname)
        etlastname = view.findViewById(R.id.et_lastname)
        tvLogin = view.findViewById(R.id.tv_login)


        db = AppDatabase.getInstance(requireContext())

        etusername.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                var username : String = p0.toString()
                if(db.userDao().isTaken(username)){
                    isAllowed = false
                    Toast.makeText(requireContext(),"Username already taken",Toast.LENGTH_SHORT).show()

                }
                else{
                    isAllowed = true

                }
            }

        })

        btnregister.setOnClickListener {
            var UserName = etusername.text.toString().trim()
            var Password = etpassword.text.toString().trim()
            var firstName = etfirstname.text.toString().trim()
            var lastName = etlastname.text.toString().trim()

            if(UserName.isEmpty()){
                etusername.error = "Username Required"
                etusername.requestFocus()
                return@setOnClickListener
            }

            if(Password.isEmpty()){
                etpassword.error = "Password Required"
                etpassword.requestFocus()
                return@setOnClickListener
            }

            if(firstName.isEmpty()){
                etfirstname.error = "First Name Required"
                etfirstname.requestFocus()
                return@setOnClickListener
            }

            if(lastName.isEmpty()){
                etlastname.error = "Last Name Required"
                etlastname.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance(requireContext()).createUser(firstName,lastName, UserName, Password).enqueue(object : Callback<DefaultResponse>{
                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>
                ) {
                    Toast.makeText(requireContext(), response.body()?.message,Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(requireContext(),t.message,Toast.LENGTH_SHORT).show()
                }

            })


//            if(isAllowed){
//                GlobalScope.launch {
//
//                    db.userDao().insertUser(User(0,firstName,lastName,userName,password))
//                }
//                Toast.makeText(requireContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show()
//                loadFragment(LoginFragment())
//            }
//            else{
//                Toast.makeText(requireContext(),"Username already taken",Toast.LENGTH_SHORT).show()
//            }

        }
        tvLogin.setOnClickListener {
            loadFragment(LoginFragment())

        }
       return view
    }
    private fun loadFragment(fragment : Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()

    }



}