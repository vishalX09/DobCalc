package silver.struggle.dobcalc

import android.app.DatePickerDialog
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

     private var tvSelectDate :  TextView? =  null
    private  var tvAgeInMInutes : TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.buttonDatePicker)
        tvSelectDate = findViewById(R.id.tvSelectDate)
        tvAgeInMInutes = findViewById(R.id.tvAgeInMinute)

        btnDatePicker.setOnClickListener {
            clickdatepicker()
        }
    }
    private fun clickdatepicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, SelectedYear, Selectedmonth, SelecteddayOfMonth ->
                Toast.makeText(this, "year was $SelectedYear , month was ${Selectedmonth+1} ,  Day was $SelecteddayOfMonth", Toast.LENGTH_LONG).show()
                val selectdate = "$SelecteddayOfMonth/${Selectedmonth+1}/$SelectedYear"
                tvSelectDate?.text = selectdate

                val sdf = SimpleDateFormat("DD/MM/yyyy" , Locale.ENGLISH)
                val theDate = sdf.parse(selectdate)
                theDate?.let {
                    val selectedDateInMinutes  = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMInutes?.text = differenceInMinutes.toString()
                    }

                }




            },
            year,
            month,
            day,
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}