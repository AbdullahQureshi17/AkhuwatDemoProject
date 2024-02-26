package com.example.akhuwatdemo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.database.AppDatabase
import com.example.akhuwatdemo.model.ExpenseSource
import com.example.akhuwatdemo.model.IncomeSource
import com.example.akhuwatdemo.utils.SharedPref


/**
 Created by Abdullah
 */
class BudgetFragment : Fragment(R.layout.fragment_budget) {

    private lateinit var finalIncome : TextView
    private lateinit var finalExpense : TextView
    private lateinit var finalBudget : TextView
    private lateinit var report : ImageView
    private lateinit var db2: AppDatabase
    private var total1: Int = 0
    private var total2: Int = 0
    private var total3: Int = 0
    private var total4: Int = 0
    private var total5: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_budget, container, false)

        finalIncome = view.findViewById(R.id.income_value)
        finalExpense = view.findViewById(R.id.expenditure_value)
        finalBudget = view.findViewById(R.id.budget_value)
        report = view.findViewById(R.id.report)
        db2 = AppDatabase.getInstance(requireContext())



//        val w = SharedPref.getInstance(requireContext())
//        val v = w.getUserFromSharedPreferences(requireContext())
//            val totalIncome:List<IncomeSource> = db2.sourceDaoIncome().getAllIncomeValues(v?.userId)
//            for (element in totalIncome) {
//                if (element.incomeValue != null) {
//                    total1 += element.incomeValue!!
//
//                }
//            }

            total4 = total1

            finalIncome.text = total1.toString()
            total1 = 0


//        val totalExpense: List<ExpenseSource> = db2.sourceDaoExpense().getAllExpenseValues(v?.userId)
//
//        for (element in totalExpense) {
//                    if (element.expenseValue != null) {
//                        total2 += element.expenseValue!!
//
//                    }
//
//
//                }

            total5 = total2
            finalExpense.text = total2.toString()
        total2 = 0


        total3 = total4 - total5
        finalBudget.text = total3.toString()

        report.setOnClickListener {
            loadFragment(ReportFragment())

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
