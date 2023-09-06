package amey.bhogle.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null
    private var ageInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button = findViewById(R.id.button)
        selectedDate = findViewById(R.id.tvselectedDate)
        ageInMinutes = findViewById(R.id.tvAgdInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    fun clickDatePicker(){
        val myClaendar = Calendar.getInstance()
        val year = myClaendar.get(Calendar.YEAR)
        val month = myClaendar.get(Calendar.MONTH)
        val day = myClaendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { View, selectedyear, selectedmonth,
                                                 selectedday ->
                Toast.makeText(this,"The year is $selectedyear," +
                        " month is ${selectedmonth+1}, Day is $selectedday",
                    Toast.LENGTH_LONG).show()

                val displayeddate = "$selectedday/${selectedmonth+1}/$selectedyear"
                selectedDate?.text = displayeddate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val date = sdf.parse(displayeddate)
                date?.let {
                    val selectedDateInMinutes = date.time / 60000

                    val currentSelectedDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentSelectedDate.let {
                        val currentDateInMinutes = currentSelectedDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        ageInMinutes?.text = differenceInMinutes.toString()
                    }

                }
            }
            ,year,month,day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }


}