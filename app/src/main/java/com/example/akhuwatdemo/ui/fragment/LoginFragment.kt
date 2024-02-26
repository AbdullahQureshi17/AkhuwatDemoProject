package com.example.akhuwatdemo.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.callback.ApiResponseBack

import com.example.akhuwatdemo.database.AppDatabase
import com.example.akhuwatdemo.model.ApiLoginRequest
import com.example.akhuwatdemo.model.Data
import com.example.akhuwatdemo.model.Result
import com.example.akhuwatdemo.ui.activity.GoogleMapCurrentLocationActivity
import com.example.akhuwatdemo.ui.activity.UserActivity
import com.example.akhuwatdemo.utils.GetDataFromApi
import retrofit2.Callback
import retrofit2.Response


/**
 Created by Abdullah
 */
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var btnlogin: Button
    private lateinit var db: AppDatabase
    private lateinit var etusername: EditText
    private lateinit var etpassword: EditText
    private lateinit var tvloginMain: TextView
     var body: ApiLoginRequest = ApiLoginRequest(UserName = "", Password = "")




    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        btnlogin = view.findViewById(R.id.loginBtn)
        etusername = view.findViewById(R.id.et_usernamee)
        etpassword = view.findViewById(R.id.et_passwordd)
        tvloginMain = view.findViewById(R.id.tv_loginMain)




        db = AppDatabase.getInstance(requireContext())



        btnlogin.setOnClickListener {
            login()
        }

        tvloginMain.setOnClickListener {
            loadFragment(RegisterFragment())
        }
        return view
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun login() {

        body.UserName = etusername.text.toString().trim()
        body.Password = etpassword.text.toString().trim()

        if ((etusername.text.isEmpty() || etpassword.text.isEmpty())) {
            Toast.makeText(requireContext(),"Please Enter Username and Password", Toast.LENGTH_SHORT).show();
        }

        else {
            val getDataFromApi = GetDataFromApi()
            getDataFromApi.loginUser(body,requireContext(),object :ApiResponseBack<Data>{

                override fun onSuccess(data: Data) {
                    Toast.makeText(requireContext(),"Login Successful",Toast.LENGTH_SHORT).show()
                    val i = Intent(activity, UserActivity::class.java)
                    startActivity(i)

                }

                override fun onFailure(message: String) {
                    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()

                }

            })



//            if(db.userDao().login(body.UserName,body.Password)){
//                val i = Intent(activity, UserActivity::class.java)
//                startActivity(i)
//                var id = db.userDao().getUser(UserName,Password)
//                sharedPref.saveUserToSharedPreferences(requireContext(),id)
//            }
//            else{
//                Toast.makeText(requireContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show()
//            }


//            RetrofitClient.instance(requireContext()).login(body)
//                .enqueue(object : Callback<ApiLoginResponse> {
//                    override fun onResponse(
//                        call: Call<ApiLoginResponse>, response: Response<ApiLoginResponse>
//                    ) {
//                        val code = response.code()
//                        if (response.body()?.StatusCode?.equals(code) == true) {
//                            val token = response.body()!!.Data.Token.Token
//                           // TOKEN_P = token
//                            val n = SharedPref.getInstance(requireContext())
//                            n.saveToken(token).toString()
//                          //  n.getToken()
//
//                            val userrr = response.body()!!.Data
//                            val sharedPref = SharedPref.getInstance(requireContext())
//                            sharedPref.saveUserToSharedPreferences(requireContext(),userrr)
//
//                            val i = Intent(activity, UserActivity::class.java)
//                            startActivity(i)
//
//
//
//
//
//
//                        } else {
//                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
//                        }

//                    }
//
//                    override fun onFailure(call: Call<ApiLoginResponse>, t: Throwable) {
//                        t.message
//                    }
//
//                })


        }

    }
}