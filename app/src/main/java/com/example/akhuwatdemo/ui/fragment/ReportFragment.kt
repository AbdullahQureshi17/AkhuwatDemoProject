package com.example.akhuwatdemo.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.ui.adapter.ExpenseAdapter
import com.example.akhuwatdemo.ui.adapter.IncomeAdapter
import com.example.akhuwatdemo.database.AppDatabase
import com.example.akhuwatdemo.model.ExpenseSource
import com.example.akhuwatdemo.model.IncomeSource


/**
 Created by Abdullah
 */
class ReportFragment : Fragment(R.layout.fragment_report) {

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var incomeAdapter: IncomeAdapter
    private lateinit var expenseAdapter: ExpenseAdapter
    private lateinit var db3: AppDatabase


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_report, container, false)
        db3 = AppDatabase.getInstance(requireContext())
       val totalIncome:List<IncomeSource> = db3.sourceDaoIncome().getAllIncomeValues2()
        val totalExpense: List<ExpenseSource> = db3.sourceDaoExpense().getAllExpenseValues()

        recyclerView1 = view.findViewById(R.id.rv_Income)
        recyclerView2 = view.findViewById(R.id.rv_Expense)

        val layoutManager = LinearLayoutManager(requireContext())
        val layoutManager2 = LinearLayoutManager(requireContext())
        recyclerView1.layoutManager = layoutManager
        recyclerView2.layoutManager = layoutManager2


       incomeAdapter = IncomeAdapter(totalIncome)
        recyclerView1.adapter = incomeAdapter

        expenseAdapter = ExpenseAdapter(totalExpense)
        recyclerView2.adapter = expenseAdapter



        return view
    }


    }



