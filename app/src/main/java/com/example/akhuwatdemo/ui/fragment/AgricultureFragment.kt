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
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.callback.ApiResponseBack
import com.example.akhuwatdemo.callback.DataResponseBack
import com.example.akhuwatdemo.model.AgriLoanProductRequest
import com.example.akhuwatdemo.model.AgriLoanUtilizationRequest
import com.example.akhuwatdemo.model.ApiAgriAppraisalRequest
import com.example.akhuwatdemo.model.FarmerType
import com.example.akhuwatdemo.model.ResultXXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXXXXXXX
import com.example.akhuwatdemo.utils.GetDataFromApi
import com.example.akhuwatdemo.utils.SendDataToApi
import com.example.akhuwatdemo.utils.SharedPref

//import com.sayantan.advancedspinner.MultiSpinner


/**
 Created By Abdullah
 */
class AgricultureFragment : Fragment(R.layout.fragment_agriculture), DataResponseBack<ArrayList<String>> {

    private lateinit var ivAdd : ImageView
    private lateinit var ivAdd2 : ImageView
    private lateinit var linearLayout3: LinearLayout
    private lateinit var linearLayout4: LinearLayout
    private lateinit var tvFarmerType : TextView
    private var getDataFromApi = GetDataFromApi()
    private lateinit var agriAppraisalList : List<ResultXXXXXXX>
    private lateinit var farmSeasonTypeList : List<ResultXXXXXXXXXXX>
    private var farmerTypeList : ArrayList<FarmerType> = ArrayList()

    private lateinit var AreaTypeList : List<ResultXXXXXXXX>
    private lateinit var IrrigationSourceTypeList : List<ResultXXXXXXXXX>
    private lateinit var LoanPurposeTypeList : List<ResultXXXXXXXXXX>
    private var agriAppraisalTypeNames = ArrayList<String>()

    private var irrigationTypeNames = ArrayList<String>()
    private var loanPurposeTypeNames = ArrayList<String>()
    private var AreaTypeNames = ArrayList<String>()
    private lateinit var spinnerAppraisalType: Spinner
    private lateinit var spinnerAgriLandType: Spinner
    private lateinit var spinnerOwnedAreaType: Spinner
    private lateinit var spinnerTenancyAreaType: Spinner
    private lateinit var spinnerIrrigationSourceType: Spinner
    private lateinit var spinnerFarmSeasonType: Spinner
    private lateinit var spinnerCropNameType: Spinner
    private lateinit var etLoanPurposeType: EditText
    private lateinit var btnsaveAppraisal : Button
    private var count1 : Int = 0
    private var countLoanProduct : Int = 0
    private var countLoanUtilization : Int = 0
    private var sendDataToApi =  SendDataToApi()
    var listAgriLoanProduct : ArrayList<AgriLoanProductRequest> = ArrayList()
    var listAgriLoanUtilization : ArrayList<AgriLoanUtilizationRequest> = ArrayList()
    var agriAppraisalData : ApiAgriAppraisalRequest = ApiAgriAppraisalRequest(listAgriLoanProduct,listAgriLoanUtilization,"",0,0,0,"",0,"",false,false,false,false,"",0,
        0,"",0,0,0,0,"",0,0,0,"",0,"")

    private lateinit var agriAppraisalDataResponse : ResultXXXXXXXXXXXX
    private lateinit var etAgriAreaSize : EditText
    private lateinit var etOwnedAreaSize : EditText
    private lateinit var etTenancyAreaSize : EditText
    private lateinit var etLandAddress : EditText
    private lateinit var etExpectedProduction : EditText
    private lateinit var etExpectedSale : EditText
    private lateinit var etExpectedExpenses : EditText
    private lateinit var etExpectedIncome : EditText
    private lateinit var etAvailableAmount : EditText
    private lateinit var etRequiredAmount : EditText
    private lateinit var etCropPreviousSeason : EditText
    private lateinit var etAmount : EditText
    private lateinit var etAvailableAmountt : EditText
    private lateinit var etRequiredAmountt : EditText
    var applicationID : String = "-CtaBRkPYtxvFvgl0YySsA"

    private lateinit var rbSoilYes : RadioButton
    private lateinit var rbSoilNo : RadioButton
    private lateinit var rbLaserYes : RadioButton
    private lateinit var rbLaserNo : RadioButton
    private lateinit var rgSoil : RadioGroup
    private lateinit var rgLaser : RadioGroup
    private var appraisalTypePosition : Int = 0
    private var areaTypePosition : Int = 0
    private var ownedAreaTypePosition : Int = 0
    private var farmerTypePosition : Int = 0
    private var tenancyAreaTypePosition : Int = 0
    private var irrigationSourceTypePosition : Int = 0
    private var selectedPosition : ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_agriculture, container, false)

        initialize(view)



        ivAdd.setOnClickListener {
            generateLoanPurposeData(null,null,null,null)
        }

        ivAdd2.setOnClickListener {
            generateCropInfo(null,null)
        }

        btnsaveAppraisal.setOnClickListener {
            saveAgriAppraisalDataToApi()
        }

        tvFarmerType.setOnClickListener {

            farmerType()

        }


        //farmerType()
        getAgriAppraisalTypeData()
        getAreaTypeData()
        getIrrigationSourceData()
        getFarmSeasonTypeData()


        assignValues()

        return view
    }



    private fun initialize(v : View){
        ivAdd = v.findViewById(R.id.iv_addd)
        ivAdd2 = v.findViewById(R.id.iv_addd2)

        linearLayout3 = v.findViewById(R.id.linear_layout_loanPurpose)
        linearLayout4 = v.findViewById(R.id.linear_layout_cropInfo)
        tvFarmerType = v.findViewById(R.id.sp_farmerType)
        spinnerAppraisalType = v.findViewById(R.id.sp_appraisalType)
        spinnerAgriLandType = v.findViewById(R.id.sp_agriLandType)
        spinnerOwnedAreaType = v.findViewById(R.id.sp_ownedAreaType)
        spinnerTenancyAreaType = v.findViewById(R.id.sp_tenancyAreaType)
        spinnerIrrigationSourceType = v.findViewById(R.id.sp_irrigationSource)


        btnsaveAppraisal = v.findViewById(R.id.btn_saveAppraisal)
        etAgriAreaSize = v.findViewById(R.id.et_agriAreaSize)
        etOwnedAreaSize = v.findViewById(R.id.et_ownedAreaSize)
        etTenancyAreaSize = v.findViewById(R.id.et_tenancyAreaSize)
        etLandAddress = v.findViewById(R.id.et_agriculturelandCompleteAddress)

        etExpectedProduction = v.findViewById(R.id.et_expectedProduction)
        etExpectedSale = v.findViewById(R.id.et_expectedSale)
        etExpectedExpenses = v.findViewById(R.id.et_expectedExpenses)
        etExpectedIncome = v.findViewById(R.id.et_expectedIncome)
        etAvailableAmount = v.findViewById(R.id.et_availableAmount)
        etRequiredAmount = v.findViewById(R.id.et_requiredAmount)

        rbSoilYes = v.findViewById(R.id.rb_soilYes)
        rbSoilNo = v.findViewById(R.id.rb_soilNo)
        rbLaserYes = v.findViewById(R.id.rb_laserYes)
        rbLaserNo = v.findViewById(R.id.rb_laserNo)
        rgSoil = v.findViewById(R.id.rg_soilAnalysis)
        rgLaser = v.findViewById(R.id.rg_laserLevel)


    }

    private fun assignValues(){
        agriAppraisalData.AgriLoanUtilizationRequest = listAgriLoanUtilization
        agriAppraisalData.AgriLoanProductRequest = listAgriLoanProduct

        agriAppraisalData.ApplicationIDHash = applicationID


        etAgriAreaSize.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etAgriAreaSize.text.isNotEmpty()){
                    agriAppraisalData.ApAgriLandArea = etAgriAreaSize.text.toString().toInt()
                    agriAppraisalData.ApAgriLandAreaAcre = etAgriAreaSize.text.toString().toInt()
                }
                if (etAgriAreaSize.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Agri Area Size", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etOwnedAreaSize.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etOwnedAreaSize.text.isNotEmpty()){
                    agriAppraisalData.ApAgriOwnedArea = etOwnedAreaSize.text.toString().toInt()
                    agriAppraisalData.ApAgriOwnedAreaAcre = etOwnedAreaSize.text.toString().toInt()
                }
                if(etOwnedAreaSize.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Owned Area Size", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etTenancyAreaSize.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etTenancyAreaSize.text.isNotEmpty()){
                    agriAppraisalData.ApAgriTenancyArea = etTenancyAreaSize.text.toString().toInt()
                    agriAppraisalData.ApAgriTenancyAreaAcre = etTenancyAreaSize.text.toString().toInt()
                }
                if(etTenancyAreaSize.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Tenancy Area Size", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etLandAddress.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etLandAddress.text.isNotEmpty()){
                    agriAppraisalData.ApAgriLandAddress = etLandAddress.text.toString()
                }
                if(etLandAddress.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Land Complete Address", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etExpectedProduction.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etExpectedProduction.text.isNotEmpty()){
                    agriAppraisalData.ApAgriExpectedProduction = etExpectedProduction.text.toString()
                }
                if(etExpectedProduction.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Expected Production", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etExpectedSale.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etExpectedSale.text.isNotEmpty()){
                    agriAppraisalData.ApAgriExpectedSale = etExpectedSale.text.toString().toInt()
                }
                if (etExpectedSale.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Expected Sale", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etExpectedExpenses.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etExpectedExpenses.text.isNotEmpty()){
                    agriAppraisalData.ApAgriExpectedExpenses = etExpectedExpenses.text.toString().toInt()
                }
                if(etExpectedExpenses.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Expected Expenses", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etExpectedIncome.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etExpectedIncome.text.isNotEmpty()){
                    agriAppraisalData.ApAgriExpectedIncome = etExpectedIncome.text.toString().toInt()
                }
                if (etExpectedIncome.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Expected Income", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etAvailableAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etAvailableAmount.text.isNotEmpty()){
                    agriAppraisalData.ApAgriAvailableAmount = etAvailableAmount.text.toString().toInt()
                }
                if(etAvailableAmount.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Available Amount", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etRequiredAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etRequiredAmount.text.isNotEmpty()){
                    agriAppraisalData.ApAgriRequiredAmount = etRequiredAmount.text.toString().toInt()
                }
                if(etRequiredAmount.text.isEmpty()){
                    Toast.makeText(requireContext(), "Enter Required Amount", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })




    }


    private fun farmerType(){
               selectedPosition.add(2)
        //        selectedPosition.add(1)

        val farmerDialog = CustomDialogFragment(this, selectedPosition)
        farmerDialog.show(requireActivity().supportFragmentManager, null)
    }





    @SuppressLint("MissingInflatedId")
    private fun generateLoanPurposeData(loanPurpose : String?, amount : String?, availableAmount : String?, requiredAmount : String?){
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.loan_purpose_item, null)
        etLoanPurposeType = rowView.findViewById(R.id.et_loanPurpose)
        etAmount = rowView.findViewById(R.id.et_amount)
        etAvailableAmountt = rowView.findViewById(R.id.et_availableAmountt)
        etRequiredAmountt = rowView.findViewById(R.id.et_requiredAmountt)
        listAgriLoanUtilization.add(AgriLoanUtilizationRequest(0,"",0,"",0))
        linearLayout3.addView(rowView)

        //getLoanPurposeData(spinnerLoanPurposeType)
        populateloanUtilizationList(etLoanPurposeType,etAmount,etAvailableAmountt,etRequiredAmountt)

        if(loanPurpose != null){
            etLoanPurposeType.setText(loanPurpose)
        }

        if(amount != null){
            etAmount.setText(amount)
        }

        if(availableAmount != null){
            etAvailableAmountt.setText(availableAmount)
        }

        if(requiredAmount != null){
            etRequiredAmountt.setText(requiredAmount)
        }




    }

    private fun generateCropInfo(hashID:String?, previousSeason:String?){
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView2: View = inflater.inflate(R.layout.crop_info_item, null)
        spinnerFarmSeasonType = rowView2.findViewById(R.id.sp_cropSeason)
        spinnerCropNameType = rowView2.findViewById(R.id.sp_cropName)
        etCropPreviousSeason = rowView2.findViewById(R.id.et_cropProduction)
        setFarmSeasonTypeSpinner(spinnerFarmSeasonType)
        listAgriLoanProduct.add(AgriLoanProductRequest("","",0))
        linearLayout4.addView(rowView2)


        setCropNameSpinner(spinnerCropNameType)


        populateLoanProductList(spinnerFarmSeasonType,etCropPreviousSeason)

        if(hashID != null){
            farmSeasonTypeList.forEachIndexed { index, resultXXXXX ->
                if(hashID ==  resultXXXXX.FarmSeasonTypeIDHash){
                    spinnerFarmSeasonType.setSelection(index)
                }
            }
        }

        if(previousSeason != null){
            etCropPreviousSeason.setText(previousSeason)
        }

    }


    private fun checkSoilAndLaser(){

        if(rbSoilYes.isChecked){
            agriAppraisalData.ApAgriIsSoilAnalysis = true

        }
        if(rbSoilNo.isChecked){
            agriAppraisalData.ApAgriIsSoilAnalysis = false
        }

        if(rbLaserYes.isChecked){
            agriAppraisalData.ApAgriIsLaserLevel = true
        }

        if(rbLaserNo.isChecked){
            agriAppraisalData.ApAgriIsLaserLevel = false
        }

    }

    private fun getAgriAppraisalTypeData(){
        val n = SharedPref.getInstance(requireContext())
        val bi = n.getBusinessID()

        getDataFromApi.getAgriAppraisalType(bi ,requireContext(),object : ApiResponseBack<List<ResultXXXXXXX>>{
            override fun onSuccess(data: List<ResultXXXXXXX>) {
                agriAppraisalList = data
                count1+=1
                checkData()


            }

            override fun onFailure(message: String) {
                Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
            }

        })
    }




    private fun getFarmSeasonTypeData(){
        val n = SharedPref.getInstance(requireContext())
        val bi = n.getBusinessID()

        getDataFromApi.getFarmSeasonType(bi, requireContext(), object : ApiResponseBack<List<ResultXXXXXXXXXXX>>{
            override fun onSuccess(data: List<ResultXXXXXXXXXXX>) {
                farmSeasonTypeList = data

                count1+=1
                checkData()


            }

            override fun onFailure(message: String) {

            }

        })
    }

    private fun setFarmSeasonTypeSpinner(spinner: Spinner){
        var farmSeasonTypeNames = ArrayList<String>()
        for(i in farmSeasonTypeList){
            farmSeasonTypeNames.add(i.FarmSeasonTypeName)
        }
        val arrayAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, farmSeasonTypeNames
        )
        spinner.adapter = arrayAdapter

    }

    private fun populateLoanProductList(spinner: Spinner, previousSeason : EditText){

        spinner.id = countLoanProduct
        listAgriLoanProduct[spinner.id].ApplicationIDHash = applicationID

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                listAgriLoanProduct[spinner.id].FarmSeasonTypeIDHash = farmSeasonTypeList[position].FarmSeasonTypeIDHash
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        previousSeason.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(previousSeason.text.isNotEmpty()){
                    listAgriLoanProduct[spinner.id].PreviousProduction = s.toString().toInt()
                }
                if(previousSeason.text.isEmpty()){
                    Toast.makeText(requireContext(),"Enter Crop Production of Previous Season",Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        countLoanProduct += 1

    }



    private fun setAgriAppraisalTypeSpinner(){
        for(i in agriAppraisalList){
            agriAppraisalTypeNames.add(i.AgriAppraisalTypeName)
        }
        val arrayAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, agriAppraisalTypeNames
        )
        spinnerAppraisalType.adapter = arrayAdapter

        spinnerAppraisalType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                agriAppraisalData.ApAgriAppraisalTypeIDHash = agriAppraisalList[position].AgriAppraisalTypeIDHash

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }



    private fun getAreaTypeData(){
        val n = SharedPref.getInstance(requireContext())
        val bi = n.getBusinessID()

        getDataFromApi.getAreaTypeData(bi,requireContext(), object : ApiResponseBack<List<ResultXXXXXXXX>>{
            override fun onSuccess(data: List<ResultXXXXXXXX>) {
                AreaTypeList = data
                count1+=1
                checkData()

            }

            override fun onFailure(message: String) {
                Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setAreaTypeSpinner(){
        for(i in AreaTypeList){
            AreaTypeNames.add(i.AreaTypeName)
        }
        val arrayAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, AreaTypeNames
        )
        spinnerAgriLandType.adapter = arrayAdapter

        spinnerAgriLandType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                agriAppraisalData.ApAgriLandAreaTypeIDHash = AreaTypeList[position].AreaTypeIDHash
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        spinnerOwnedAreaType.adapter = arrayAdapter

        spinnerOwnedAreaType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                agriAppraisalData.ApAgriOwnedAreaTypeIDHash = AreaTypeList[position].AreaTypeIDHash
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


        spinnerTenancyAreaType.adapter = arrayAdapter

        spinnerTenancyAreaType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                agriAppraisalData.ApAgriTenancyAreaTypeIDHash = AreaTypeList[position].AreaTypeIDHash
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun getIrrigationSourceData(){
        val n = SharedPref.getInstance(requireContext())
        val bi = n.getBusinessID()

        getDataFromApi.getIrrigationSourceType(bi, requireContext(), object : ApiResponseBack<List<ResultXXXXXXXXX>>{
            override fun onSuccess(data: List<ResultXXXXXXXXX>) {
                IrrigationSourceTypeList = data
                count1+=1
                checkData()

            }

            override fun onFailure(message: String) {
                Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setIrrigationTypeSpinner(){
        for(i in IrrigationSourceTypeList){
            irrigationTypeNames.add(i.IrrigationSourceTypeName)
        }
        val arrayAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, irrigationTypeNames
        )
        spinnerIrrigationSourceType.adapter = arrayAdapter

        spinnerIrrigationSourceType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                agriAppraisalData.ApAgriIrrigationSourceTypeIDHash = IrrigationSourceTypeList[position].IrrigationSourceTypeIDHash
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }



    private fun setCropNameSpinner(spinner: Spinner){

        val cropNameList = arrayListOf("Wheat", "Rice", "Sugarcane", "Cotton")
        val arrayAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, cropNameList
        )
        spinner.adapter = arrayAdapter
    }

//    private fun getLoanPurposeData(spinner: Spinner){
//        val n = SharedPref.getInstance(requireContext())
//        val bi = n.getBusinessID()
//
//        getDataFromApi.getLoanPurposeType(bi, requireContext(), object : ApiResponseBack<List<ResultXXXXXXXXXX>>{
//            override fun onSuccess(data: List<ResultXXXXXXXXXX>) {
//                LoanPurposeTypeList = data
//                //setLoanPurposeTypeSpinner(spinner)
//            }
//
//            override fun onFailure(message: String) {
//                Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }
//    private fun setLoanPurposeTypeSpinner(spinner: Spinner){
//        for(i in LoanPurposeTypeList){
//            loanPurposeTypeNames.add(i.LoanPurposeName)
//        }
//        val arrayAdapter = ArrayAdapter<String>(requireContext(),
//            android.R.layout.simple_spinner_dropdown_item, loanPurposeTypeNames
//        )
//        spinner.adapter = arrayAdapter
//
//    }

    private fun saveAgriAppraisalDataToApi(){

       // farmerTypeList.forEachIndexed { index, farmerType ->  }
        for(i in farmerTypeList){

            if(i.name == "Owner"){
                agriAppraisalData.ApAgriIsOwner = i.check
            }

            if(i.name == "Tenant"){
                agriAppraisalData.ApAgriIsTenant = i.check
            }

            if(i.name == "Owner" && i.name == "Tenant"){
                agriAppraisalData.ApAgriIsOwner = i.check
                agriAppraisalData.ApAgriIsTenant = i.check
            }


       }

        checkSoilAndLaser()
        sendDataToApi.sendAgriAppraisalData(agriAppraisalData,requireContext(),object : ApiResponseBack<ApiAgriAppraisalRequest>{
            override fun onSuccess(data: ApiAgriAppraisalRequest) {
                Toast.makeText(requireContext(), "Agriculture Appraisal Data Successfully sent to Api", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(message: String) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun checkData(){
        if(count1 == 4){
            setAreaTypeSpinner()
            setAgriAppraisalTypeSpinner()
            setIrrigationTypeSpinner()

            getAgriAppraisal()



        }
    }


    private fun populateloanUtilizationList(loanPurpose: EditText,amount: EditText, availableAmount : EditText, requiredAmount : EditText){

        loanPurpose.tag = countLoanUtilization

        listAgriLoanUtilization[loanPurpose.tag as Int].ApplicationIDHash = applicationID

        loanPurpose.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(loanPurpose.text.isNotEmpty()){
                    listAgriLoanUtilization[loanPurpose.tag as Int].LoanUtilizationTypeName = loanPurpose.text.toString()
                }
                if(loanPurpose.text.isEmpty()){
                    Toast.makeText(requireContext(),"Enter Loan Purpose",Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        amount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(amount.text.isNotEmpty()){
                    listAgriLoanUtilization[loanPurpose.tag as Int].Amount = amount.text.toString().toInt()
                }
                if(amount.text.isEmpty()){
                    Toast.makeText(requireContext(),"Enter Loan Amount",Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        availableAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(availableAmount.text.isNotEmpty()){
                    listAgriLoanUtilization[loanPurpose.tag as Int].AvailableAmount = availableAmount.text.toString().toInt()
                }
                if(availableAmount.text.isEmpty()){
                    Toast.makeText(requireContext(),"Enter Available Amount",Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        requiredAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(requiredAmount.text.isNotEmpty()){
                    listAgriLoanUtilization[loanPurpose.tag as Int].RequiredAmount = requiredAmount.text.toString().toInt()
                }
                if(requiredAmount.text.isEmpty()){
                    Toast.makeText(requireContext(),"Enter Required Amount",Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        countLoanUtilization+=1

    }

    private fun getAgriAppraisal(){
        val n = SharedPref.getInstance(requireContext())
        val bi = n.getBusinessID()
        getDataFromApi.getAgriAppraisal(bi,applicationID,requireContext(),object : ApiResponseBack<ResultXXXXXXXXXXXX>{
            override fun onSuccess(data: ResultXXXXXXXXXXXX) {
                agriAppraisalDataResponse = data
                setForm()
            }

            override fun onFailure(message: String) {
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setForm(){
        etAgriAreaSize.setText(agriAppraisalDataResponse.ApAgriLandArea.toString())
        etOwnedAreaSize.setText(agriAppraisalDataResponse.ApAgriOwnedArea.toString())
        etTenancyAreaSize.setText(agriAppraisalDataResponse.ApAgriTenancyArea.toString())

        agriAppraisalList.forEachIndexed { index, resultXXXXXXX ->
            if(resultXXXXXXX.AgriAppraisalTypeIDHash == agriAppraisalDataResponse.AgriAppraisalTypeIDHash){
                appraisalTypePosition = index
            }
        }
        spinnerAppraisalType.setSelection(appraisalTypePosition)

        AreaTypeList.forEachIndexed { index, resultXXXXXXXX ->
            if(resultXXXXXXXX.AreaTypeIDHash == agriAppraisalDataResponse.ApAgriLandAreaTypeIDHash){
                areaTypePosition = index
            }
        }
        spinnerAgriLandType.setSelection(areaTypePosition)

        AreaTypeList.forEachIndexed { index, resultXXXXXXXX ->
            if(resultXXXXXXXX.AreaTypeIDHash == agriAppraisalDataResponse.ApAgriOwnedAreaTypeIDHash){
                ownedAreaTypePosition = index
            }
        }
        spinnerOwnedAreaType.setSelection(ownedAreaTypePosition)

        AreaTypeList.forEachIndexed { index, resultXXXXXXXX ->
            if(resultXXXXXXXX.AreaTypeIDHash == agriAppraisalDataResponse.ApAgriTenancyAreaTypeIDHash){
                tenancyAreaTypePosition = index
            }
        }
        spinnerTenancyAreaType.setSelection(tenancyAreaTypePosition)

        IrrigationSourceTypeList.forEachIndexed { index, resultXXXXXXXXX ->
            if(resultXXXXXXXXX.IrrigationSourceTypeIDHash == agriAppraisalDataResponse.ApAgriIrrigationSourceTypeIDHash){
                irrigationSourceTypePosition = index
            }
        }
        spinnerIrrigationSourceType.setSelection(irrigationSourceTypePosition)

       etLandAddress.setText(agriAppraisalDataResponse.ApAgriLandAddress.toString())

        etExpectedProduction.setText(agriAppraisalDataResponse.ApAgriExpectedProduction.toString())
        etExpectedSale.setText(agriAppraisalDataResponse.ApAgriExpectedSale.toString())
        etExpectedExpenses.setText(agriAppraisalDataResponse.ApAgriExpectedExpenses.toString())
        etExpectedIncome.setText(agriAppraisalDataResponse.ApAgriExpectedIncome.toString())
        etAvailableAmount.setText(agriAppraisalDataResponse.ApAgriAvailableAmount.toString())
        etRequiredAmount.setText(agriAppraisalDataResponse.ApAgriRequiredAmount.toString())

        agriAppraisalDataResponse.AgriLoanUtilization.forEachIndexed { index, agriLoanUtilizationRequest ->
            generateLoanPurposeData(agriAppraisalDataResponse.AgriLoanUtilization[index].LoanUtilizationTypeName, agriAppraisalDataResponse.AgriLoanUtilization[index].Amount.toString(), agriAppraisalDataResponse.AgriLoanUtilization[index].AvailableAmount.toString(), agriAppraisalDataResponse.AgriLoanUtilization[index].RequiredAmount.toString())

        }

        agriAppraisalDataResponse.AgriLoanProduct.forEachIndexed { index, agriLoanProduct ->
            generateCropInfo(agriAppraisalDataResponse.AgriLoanProduct[index].FarmSeasonTypeIDHash, agriAppraisalDataResponse.AgriLoanProduct[index].PreviousProduction.toString())
        }

        setSoilAndLaser()
        setOwnerAndTenant(selectedPosition)








    }

    private fun setOwnerAndTenant(sp : ArrayList<Int>){
        if(agriAppraisalDataResponse.ApAgriIsOwner == true){
            sp.add(0)
        }
        if(agriAppraisalDataResponse.ApAgriIsTenant == true){
            sp.add(1)
        }
        if(agriAppraisalDataResponse.ApAgriIsOwner == true && agriAppraisalDataResponse.ApAgriIsTenant == true){
            sp.add(0)
            sp.add(1)
        }
    }

    private fun setSoilAndLaser(){
        if(agriAppraisalDataResponse.ApAgriIsSoilAnalysis == true){
            rbSoilYes.isChecked = true
        }
        if(agriAppraisalDataResponse.ApAgriIsSoilAnalysis == false){
            rbSoilNo.isChecked = true
        }
        if(agriAppraisalDataResponse.ApAgriIsLaserLevel == true){
            rbLaserYes.isChecked = true
        }
        if(agriAppraisalDataResponse.ApAgriIsLaserLevel == false){
            rbLaserNo.isChecked = true
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onSuccess(data: ArrayList<FarmerType>) {

        var farmerListtt : ArrayList<String> = ArrayList()

        farmerTypeList = data
        tvFarmerType.text = ""
        if (data != null){
            for(i in data){
                if(i.check == true){

                    farmerListtt.add(i.name)



            }
        }

            }
        for(i in farmerListtt){
            tvFarmerType.text = i + tvFarmerType.text
        }



    }

    override fun onFailure(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }


}
