package com.pdm.imc_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import java.text.DecimalFormat
import kotlin.properties.Delegates

class ImcResultActivity : AppCompatActivity() {
    private lateinit var btnreCalculate: AppCompatButton
    private lateinit var resul : TextView
    private lateinit var imc :TextView
    private lateinit var descrip:TextView
    private var num: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_result)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initUI() {
        iniresul(num)
        inidescrip(resul.toString())
    }

    private fun initComponents() {
        imc = findViewById(R.id.IMC)
        resul = findViewById(R.id.viewResul)
        descrip = findViewById(R.id.Descrip)
        btnreCalculate = findViewById(R.id.btnreCalcul)
        val valorDelIntent = intent.extras?.getDouble("IMC_RESULT")
        valorDelIntent?.let {
            num = it
        }
        imc.text = "$num"
    }


    private fun initListeners() {
        initComponents()
        btnreCalculate.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, ImcCalculatorActivity::class.java)
            startActivity(intent)

        })



    }


    private fun iniresul(num :Double) {
        return when {

        num < 18.5 -> resul.text="Bajo peso"
        num < 24.9 -> resul.text="Peso normal"
        num < 29.9 -> resul.text="Sobrepeso"
        num < 34.9 -> resul.text="Obesidad grado 1"
        num < 39.9 -> resul.text="Obesidad grado 2"
        else -> resul.text="Obesidad grado 3"
    }
    }


    private fun inidescrip(descripcion : String){
        val mystring=descripcion
         when (mystring){

             "Bajo peso" -> descrip.text="Es importante seguir una dieta saludable y realizar ejercicio de forma frecuente. También es fundamental descansar de forma adecuada. "
             "Peso normal" ->descrip.text=" Sigue así "
             "Sobrepeso" ->descrip.text="Perder peso a través de una alimentación saludable, más actividad física y otros cambios en las rutinas habituales "
             "Obesidad grado 1" ->descrip.text=" Sigue un patrón alimentario cercano a la dieta mediterránea, es decir, bajo en grasas y rico en frutas y verduras. En cuanto al deporte, la constancia es fundamental."
             "Obesidad grado 2" ->descrip.text="Prueba la dieta flexitariana, que enfatiza frutas, verduras, granos integrales y proteínas vegetales y marca objetivos realistas para perder peso"
             "Obesidad grado 3" ->descrip.text="Crear nuevos hábitos alimenticios y de actividad física; en ciertos casos, una cirugía gastrointestinal también puede considerarse para tratar este tipo de obesidad"

        }



    }
}