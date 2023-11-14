package com.pdm.imc_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat


class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var viewAltura: CardView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvHeight:TextView
    private lateinit var viewPeso:CardView
    private lateinit var viewEdda:CardView
    private lateinit var numeroPeso:TextView
    private lateinit var numeroEdad:TextView
    private lateinit var sumarEdad:FloatingActionButton
    private lateinit var sumarPeso:FloatingActionButton
    private lateinit var restarEdad:FloatingActionButton
    private lateinit var restarPeso:FloatingActionButton
    private lateinit var btnCalculate:AppCompatButton
    private  var isMaleSelected =true
    private var peso: Int = 0
    private var edad: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imccalculatoractivity)
        initComponents()
        initListeners()
        initUI()

    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()

    }

    private fun initListeners() {
        initComponents()
        viewMale.setOnClickListener(View.OnClickListener {

            setGenderColor()

        })
        viewFemale.setOnClickListener(View.OnClickListener {

            setGenderColor()

        })
        sumarEdad.setOnClickListener(View.OnClickListener {

            edad++
            setAge()

        })
        sumarPeso.setOnClickListener(View.OnClickListener {

            peso++
            setWeight()

        })
        restarPeso.setOnClickListener(View.OnClickListener {
            peso--
            setWeight()


        })
        restarEdad.setOnClickListener(View.OnClickListener {
            edad--
            setAge()


        })


        btnCalculate.setOnClickListener(View.OnClickListener {
            peso.toDouble()
            tvHeight

            if (peso > 0 ) {

                val result = calculateIMC()


                navigateToResult(result)
            } else {

                Toast.makeText(applicationContext, "Pon unos valores con los que se pueda trabajar", Toast.LENGTH_SHORT).show()


            }


        })

    }

    private fun calculateIMC(): Double {

        return peso.toDouble() / (tvHeight.text.toString().toDouble() * tvHeight.text.toString().toDouble())
    }


    private fun navigateToResult(result: Double) {

        val intent = Intent(this, ImcResultActivity::class.java)


        intent.putExtra("IMC_RESULT", result)


        startActivity(intent)
    }









    private fun setGenderColor() {


            viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
            viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))


    }


    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        viewAltura= findViewById(R.id.viewAltura)
        rsHeight= findViewById(R.id.rsHeight)
        tvHeight=findViewById(R.id.tvHeight)
        viewEdda=findViewById(R.id.viewEdda)
        viewPeso=findViewById(R.id.viewPeso)
        numeroEdad=findViewById(R.id.tvEdad)
        numeroPeso=findViewById(R.id.tvPeso)
        sumarEdad=findViewById(R.id.btnAddtAge)
        sumarPeso=findViewById(R.id.btnAddPeso)
        restarEdad=findViewById(R.id.btnSubtractAge)
        restarPeso=findViewById(R.id.btnSubtractpeso)
        btnCalculate=findViewById(R.id.btnCalcul)
        rsHeight.addOnChangeListener { _, value, _ ->
            //tvHeight.text = value.toString()
            tvHeight.text = DecimalFormat("#.##").format(value) + " cm"
        }
    }




    private  fun getBackgroundColor(isComponentSelected: Boolean): Int  {
        val colorReference = if(isComponentSelected) {
            R.color.bg_comp_sel
        } else {
            R.color.bg_comp
        }
        return ContextCompat.getColor(this,colorReference)
    }


    private fun setWeight() {
        numeroPeso.text = peso.toString()
    }

    private fun setAge() {
        numeroEdad.text = edad.toString()
    }
}



