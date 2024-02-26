package com.example.akhuwatdemo.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
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
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.callback.ApiResponseBack
import com.example.akhuwatdemo.model.ApiLivestockAppraisalRequest
import com.example.akhuwatdemo.model.LiveStockDetail
import com.example.akhuwatdemo.model.LiveStockLoanUtilization
import com.example.akhuwatdemo.model.ResultXXXX
import com.example.akhuwatdemo.model.ResultXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXX
import com.example.akhuwatdemo.utils.GetDataFromApi
import com.example.akhuwatdemo.utils.SendDataToApi
import com.example.akhuwatdemo.utils.SharedPref

/**
 created by Abdullah
 */
class LivestockFragment : Fragment(R.layout.fragment_livestock) {
    private lateinit var iv_add1 : ImageView
    private lateinit var iv_add2 : ImageView
    private lateinit var linearLayout1: LinearLayout
    private lateinit var linearLayout2: LinearLayout
    private lateinit var btnsaveAppraisal : Button
    private lateinit var btnfetchAppraisal : Button
    private var getDataFromApi = GetDataFromApi()
    private lateinit var spinnerBusinessPlace: Spinner
    private var sendDataToApi =  SendDataToApi()
    private lateinit var etCurrentMonthlyProfit : EditText
    private lateinit var etExpectedIncrease : EditText
    private var count1 : Int = 0
    private var countStock : Int = 0
    private var countLoan : Int = 0
     var applicationID : String = "kV6fgfAoKYMjrPfMN_ZMIg"
    private var businessPlacePosition : Int = 0
    private var spStockPosition : Int = 0
    private var businessPlaceNames = ArrayList<String>()
    private lateinit var spinnerLoan3 : Spinner
    private lateinit var spinnerLoan4 : Spinner

    var listLiveStockDetail : ArrayList<LiveStockDetail> = ArrayList()
    var listLiveStockLoan : ArrayList<LiveStockLoanUtilization> = ArrayList()
    var bodyy : ApiLivestockAppraisalRequest = ApiLivestockAppraisalRequest("",0,0,0,0,"",listLiveStockDetail,listLiveStockLoan)
    private lateinit var stockTypeList : List<ResultXXXXX>
    private lateinit var businessplaceList : List<ResultXXXX>
    private lateinit var livestockAppraisal : ResultXXXXXX

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("InflateParams", "MissingInflatedId", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_livestock, container, false)


        initialize(view)

        iv_add1.setOnClickListener {
            generateStockData(null, null, null)
        }

        iv_add2.setOnClickListener {
            generateLoanData(null,null,null)
        }

        assignValues()


        btnsaveAppraisal.setOnClickListener {
            saveDataToApi()

        }


        getBusinessPlaceData()
        getLoanUtilizationData()






        return view
    }

    private fun initialize(v : View){
        iv_add1 = v.findViewById(R.id.iv_add)
        iv_add2 = v.findViewById(R.id.iv_add2)
        linearLayout1 = v.findViewById(R.id.linear_layout_quantity_amount)
        linearLayout2 = v.findViewById(R.id.linear_layout_quantity_amount2)
        spinnerBusinessPlace = v.findViewById(R.id.sp_businessplace)
        btnsaveAppraisal = v.findViewById(R.id.saveAppraisal)
        btnfetchAppraisal = v.findViewById(R.id.fetchAppraisal)
        etCurrentMonthlyProfit = v.findViewById(R.id.et_currentMonthlyProfit)
        etExpectedIncrease = v.findViewById(R.id.et_expectedIncrease)
    }

    private fun assignValues(){

        bodyy.ApLsLatitude = 0
        bodyy.ApLsLongitude = 0
        etCurrentMonthlyProfit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(etCurrentMonthlyProfit.text.isNotEmpty()){
                    bodyy.ApLsMonthlyProfit = etCurrentMonthlyProfit.text.toString().toInt()

                }
                if(etCurrentMonthlyProfit.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Current Monthly Profit", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        etExpectedIncrease.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(etExpectedIncrease.text.isNotEmpty()){
                    bodyy.ApLsExpectedIncomIncrease = etExpectedIncrease.text.toString().toInt()

                }
                if(etCurrentMonthlyProfit.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Expected Increase in Income", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        bodyy.ApplicationIDHash = applicationID
        bodyy.LiveStockDetail = listLiveStockDetail
        bodyy.LiveStockLoanUtilization = listLiveStockLoan

    }

    //Here we are checking if the apis have run or not
private fun checkData(){
    if(count1 == 2){
        setBusinessPlaceSpinner()
//       generateStockData(null,null,null,)
//        generateLoanData(null,null,null)
        getLiveStockAppraisalData()


    }
}

    //Here we are setting business place spinner
    private fun setBusinessPlaceSpinner() {
        for (i in businessplaceList) {
            businessPlaceNames.add(i.BusinessPlaceTypeName)

        }
        val arrayAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, businessPlaceNames
        )

        spinnerBusinessPlace.adapter = arrayAdapter

        spinnerBusinessPlace.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

             bodyy.ApLsBusinessPlaceTypeIDHash = businessplaceList[p2].BusinessPlaceTypeIDHash

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }




    }


    //Here we are generating loan block dynamically
    @SuppressLint("MissingInflatedId", "InflateParams")
    private fun generateLoanData(hashID:String?, quantity:String?, amount:String?){
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.quantity_amount_item, null)
        spinnerLoan4 = rowView.findViewById(R.id.sp_stocktypee)
        val etquantity: EditText = rowView.findViewById(R.id.et_quantity)
        val etamount: EditText = rowView.findViewById(R.id.et_amount)
        setStockAndLoanSpinner(spinnerLoan4)
       listLiveStockLoan.add(LiveStockLoanUtilization("", "", "", ""))


        linearLayout2.addView(rowView)
        loanItemsPopulate(etquantity,etamount,spinnerLoan4)

        if(hashID != null){
            stockTypeList.forEachIndexed { index, resultXXXXX ->
                if(hashID ==  resultXXXXX.LoanUtilizationTypeIDHash){
                    spinnerLoan4.setSelection(index)
                }
            }
        }

        if(quantity != null){
            etquantity.setText(quantity)
        }

        if(amount != null){
            etamount.setText(amount)
        }





    }
    //Here we are generating stock block dynamically
    @SuppressLint("MissingInflatedId", "InflateParams")
    private fun generateStockData(hashID:String?, quantity:String?, amount:String?){
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.quantity_amount_item, null)
        spinnerLoan3 = rowView.findViewById(R.id.sp_stocktypee)
        spinnerLoan3.id = countStock

        val etquantity: EditText = rowView.findViewById(R.id.et_quantity)
        val etamount: EditText = rowView.findViewById(R.id.et_amount)


        linearLayout1.addView(rowView)
        listLiveStockDetail.add(LiveStockDetail("", "", "", ""))
        setStockAndLoanSpinner(spinnerLoan3)
        liveStockItemsPopulate(spinnerLoan3,etquantity,etamount)


        if(hashID != null){
            stockTypeList.forEachIndexed { index, resultXXXXX ->
                if(hashID ==  resultXXXXX.LoanUtilizationTypeIDHash){
                    spinnerLoan3.setSelection(index)

                }
            }
        }
        if(quantity != null){
            etquantity.setText(quantity)
        }

        if(amount != null){
            etamount.setText(amount)
        }

    }

    private fun setStockAndLoanSpinner(spinner: Spinner) {
        val stockType2 = ArrayList<String>()
        for(i in stockTypeList){
            stockType2.add(i.LoanUtilizationTypeName)
        }
        val arrayAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, stockType2
        )
        spinner.adapter = arrayAdapter
    }

    private fun liveStockItemsPopulate(spinner: Spinner, quantity : EditText, amount : EditText){


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listLiveStockDetail[spinner.id].LoanUtilizationTypeIDHash = stockTypeList[p2].LoanUtilizationTypeIDHash
                listLiveStockDetail[spinner.id].ApplicationIDHash = applicationID


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        quantity.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(quantity.text.isNotEmpty())
                {
                    listLiveStockDetail[spinner.id].Quantity = p0.toString()

                }
                if(quantity.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Stock Quantity", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        amount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(amount.text.isNotEmpty()){
                    listLiveStockDetail[spinner.id].Amount = p0.toString()

                }
                if(amount.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Stock Amount", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        countStock +=1
    }

    private fun loanItemsPopulate(quantity : EditText, amount: EditText, spinner: Spinner){
        spinner.id = countLoan

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listLiveStockLoan[spinner.id].LoanUtilizationTypeIDHash = stockTypeList[p2].LoanUtilizationTypeIDHash
                listLiveStockLoan[spinner.id].ApplicationIDHash = applicationID


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        quantity.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(quantity.text.isNotEmpty()){
                    listLiveStockLoan[spinner.id].Quantity = p0.toString()

                }
                if(quantity.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Loan Quantity", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        amount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(amount.text.isNotEmpty()){
                    listLiveStockLoan[spinner.id].Amount = p0.toString()

                }
                if(amount.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Loan Amount", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        countLoan += 1


    }

    private fun getBusinessPlaceData(){
        val n = SharedPref.getInstance(requireContext())
        val bi = n.getBusinessID()
        getDataFromApi.getBusinessPlace(bi,requireContext(),object : ApiResponseBack<List<ResultXXXX>>{
            override fun onSuccess(data: List<ResultXXXX>) {
                businessplaceList = data
                count1 += 1;
               checkData()
            }
            override fun onFailure(message: String) {
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
            }

        })

    }


    private fun getLoanUtilizationData(){
        val n = SharedPref.getInstance(requireContext())
        val bi = n.getBusinessID()

        getDataFromApi.getLoanUtilization(bi,requireContext(), object : ApiResponseBack<List<ResultXXXXX>> {
            override fun onSuccess(data: List<ResultXXXXX>) {
                stockTypeList = data

                count1 += 1
                checkData()



            }

            override fun onFailure(message: String) {
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun getLiveStockAppraisalData(){
        val n = SharedPref.getInstance(requireContext())
        val bi = n.getBusinessID()
        getDataFromApi.getLiveStockAppraisal(bi,applicationID,requireContext(), object : ApiResponseBack<ResultXXXXXX>{
            override fun onSuccess(data: ResultXXXXXX) {
                if(data != null){
                    Toast.makeText(context,"Data successfully fetched from Api",Toast.LENGTH_SHORT).show()
                    livestockAppraisal = data
                    setForm()

                }


            }

            override fun onFailure(message: String) {
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
            }

        })
    }


    private fun saveDataToApi(){
        sendDataToApi.sendData(bodyy,requireContext(),object : ApiResponseBack<ApiLivestockAppraisalRequest>{
            override fun onSuccess(data: ApiLivestockAppraisalRequest) {
                Toast.makeText(requireContext(), "Data Successfully sent to Api", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(message: String) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setForm(){
        etCurrentMonthlyProfit.setText(livestockAppraisal.ApLsMonthlyProfit.toString())
        etExpectedIncrease.setText(livestockAppraisal.ApLsExpectedIncomIncrease.toString())

        businessplaceList.forEachIndexed { index, resultXXXX ->
            if(resultXXXX.BusinessPlaceTypeIDHash == livestockAppraisal.ApLsBusinessPlaceTypeIDHash){
                businessPlacePosition = index

            }
        }
        spinnerBusinessPlace.setSelection(businessPlacePosition)

        livestockAppraisal.LiveStockDetail.forEachIndexed { index, liveStockDetail ->
            generateStockData(livestockAppraisal.LiveStockDetail[index].LoanUtilizationTypeIDHash, livestockAppraisal.LiveStockDetail[index].Quantity.toString(), livestockAppraisal.LiveStockDetail[index].Amount.toString())
            //generateStockData(liveStockDetail.LoanUtilizationTypeIDHash,liveStockDetail.Quantity.toString(),liveStockDetail.Amount.toString())
        }

        livestockAppraisal.LiveStockLoanUtilization.forEachIndexed { index, liveStockLoanUtilizationX ->
            generateLoanData(livestockAppraisal.LiveStockLoanUtilization[index].LoanUtilizationTypeIDHash, livestockAppraisal.LiveStockLoanUtilization[index].Quantity.toString(),livestockAppraisal.LiveStockLoanUtilization[index].Amount.toString())

        }







    }


}



