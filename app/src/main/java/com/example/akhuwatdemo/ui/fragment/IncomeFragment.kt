package com.example.akhuwatdemo.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.database.AppDatabase
import com.example.akhuwatdemo.model.ExpenseSource
import com.example.akhuwatdemo.model.IncomeSource
import com.example.akhuwatdemo.utils.SharedPref
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 Created by Abdullah
 */
class IncomeFragment : Fragment(R.layout.fragment_income) {
    private lateinit var parentLinearLayout2: LinearLayout
    private lateinit var parentLinearLayout: LinearLayout





    private lateinit var btadd: ImageView
    private lateinit var btadd2: ImageView
    var hashMapIncome: HashMap<Int, DataIncome> = HashMap<Int, DataIncome>()
    var hashMapExpense: HashMap<Int, DataExpense> = HashMap<Int, DataExpense>()
    private lateinit var tvtotalincome: TextView
    private lateinit var tvtotalexpense : TextView
    private lateinit var btnAddDatabase: Button
    private var count: Int = 1
    private var count2: Int = 1
    private lateinit var db2: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_income, container, false)
        val income = resources.getStringArray(R.array.Income)
        val expend = resources.getStringArray(R.array.Expend)
        tvtotalincome = view.findViewById(R.id.tv_totalincome)
        tvtotalexpense = view.findViewById(R.id.total_expend_textview)
        btnAddDatabase = view.findViewById(R.id.btn_addDatabase)




        btadd = view.findViewById(R.id.bt_add)
        btadd2 = view.findViewById(R.id.bt_add2)

        parentLinearLayout2 = view.findViewById(R.id.parent_linear_layout2)

        parentLinearLayout = view.findViewById(R.id.parent_linear_layout)



        btadd.setOnClickListener {
            val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView: View = inflater.inflate(R.layout.spinner_sample, null)
            val arrayAdapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                income
            )
            val spinnerSample: Spinner = rowView.findViewById(R.id.sp_income)
            spinnerSample.adapter = arrayAdapter
            val etincome: EditText = rowView.findViewById(R.id.et_income)
            spinnerSample.id = count
            parentLinearLayout.addView(rowView)
            hashMapIncome.put(spinnerSample.id, DataIncome())

            spinnerSample.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    v: View,
                    position: Int,
                    id: Long
                ) {
                    if (parent != null) {
                        if (parent.getItemAtPosition(position).equals("Choose Income")) {
                        }
                        else {
                            hashMapIncome[spinnerSample.id]?.incomeSource = income[position].toString()


                            etincome.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(
                                    p0: CharSequence?, p1: Int, p2: Int, p3: Int
                                ) {

                                }

                                override fun onTextChanged(
                                    p0: CharSequence?, p1: Int, p2: Int, p3: Int
                                ) {
                                    if ((etincome.text).isNotEmpty()) { hashMapIncome[spinnerSample.id]?.incomeValue = (etincome.text).toString().toInt()
                                    }
                                    if ((etincome.text).isEmpty()) { Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                                    }

                                }

                                override fun afterTextChanged(p0: Editable?) {

                                }

                            })

                        }


                    }
                }


                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
            count += 1
        }



        btadd2.setOnClickListener {
            val inflater2 =
                requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView2: View = inflater2.inflate(R.layout.spinner_sample, null)
            val spinnerSample: Spinner = rowView2.findViewById(R.id.sp_income)
            val arrayAdapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                expend
            )
            val etincome: EditText = rowView2.findViewById(R.id.et_income)
            spinnerSample.adapter = arrayAdapter
            spinnerSample.id = count2
            parentLinearLayout2.addView(rowView2)
            hashMapExpense.put(spinnerSample.id, DataExpense())


            spinnerSample.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p0 != null) {
                        if (p0.getItemAtPosition(p2).equals("Choose Expense")) {
                        } else {
                            hashMapExpense[spinnerSample.id]?.expenseSource = expend[p2].toString()

                            etincome.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(
                                    p0: CharSequence?, p1: Int, p2: Int, p3: Int
                                ) {
                                }

                                override fun onTextChanged(
                                    p0: CharSequence?, p1: Int, p2: Int, p3: Int
                                ) {
                                    if ((etincome.text).isNotEmpty()) {
                                        hashMapExpense[spinnerSample.id]?.expenseValue =
                                            (etincome.text).toString().toInt()

                                    }
                                    if ((etincome.text).isEmpty()) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Empty",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }

                                override fun afterTextChanged(p0: Editable?) {
                                }
                            })
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            count2 += 1
        }
        db2 = AppDatabase.getInstance(requireContext())


        btnAddDatabase.setOnClickListener {


                val w = SharedPref.getInstance(requireContext())
                val v = w.getUserFromSharedPreferences(requireContext())
                val itr = hashMapIncome.keys.iterator()
                while (itr.hasNext()) {
                    val key = itr.next()
                    val value = hashMapIncome[key]!!



//                    GlobalScope.launch {
//
//                        if (value == null) {
//                        }
//                        if (value != null) {
//                            db2.sourceDaoIncome().insertSource(
//                                IncomeSource(
//                                    0,
//                                    v?.userId,
//                                    value.incomeSource,
//                                    value.incomeValue
//                                )
//                            )
//
//
//                        }
//                    }
                }
                val itr2 = hashMapExpense.keys.iterator()
                while (itr2.hasNext()) {
                    val key2 = itr2.next()
                    val value2 = hashMapExpense[key2]!!

//                    GlobalScope.launch {
//
//                        if (value2 == null) {
//                        }
//                        if (value2 != null) {
//                            db2.sourceDaoExpense().insertSource(
//                                ExpenseSource(
//                                    0,
//                                    v?.userId,
//                                    value2.expenseSource,
//                                    value2.expenseValue
//                                )
//                            )
//
//
//                        }
//                    }
                }


                loadFragment(IncomeFragment())





        }

//        btnAddValues.setOnClickListener {
//            GlobalScope.launch {
//
//                val totalIncome: List<IncomeSource> = db2.sourceDaoIncome().getAllIncomeValues()
//                for (element in totalIncome) {
//                    if(element.incomeValue != null) {
//                        total1 += element.incomeValue!!
//                    }
//                }
//                tvtotalincome.text = total1.toString()
//            }
//            GlobalScope.launch {
//
//                val totalExpense: List<ExpenseSource> = db2.sourceDaoExpense().getAllExpenseValues()
//                for (element in totalExpense) {
//                    if(element.expenseValue != null ){
//                        total2 += element.expenseValue!!
//                    }
//                }
//                tvtotalexpense.text = total2.toString()
//
//            }
//
//        }

        return view
    }
    private fun loadFragment(fragment : Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()

    }
}
class DataIncome(){
    var incomeSource : String? = null
    var incomeValue : Int ? = null

}
class DataExpense(){
    var expenseSource : String? = null
    var expenseValue : Int ? = null

}


