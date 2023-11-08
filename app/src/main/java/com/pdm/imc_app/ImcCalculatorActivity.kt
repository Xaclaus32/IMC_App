package com.pdm.imc_app

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat


class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var viewAltura: CardView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvHeight:TextView
    private  var isMaleSelected =true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imccalculatoractivity)
        initComponents()
        initListeners()
        initUI()

    }

    private fun initUI() {
        setGenderColor()
    }

    private fun initListeners() {
        initComponents()
        viewMale.setOnClickListener(View.OnClickListener {

            setGenderColor()

        })
        viewFemale.setOnClickListener(View.OnClickListener {

            setGenderColor()

        })

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

}