package com.codicius.breviary

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.util.*

public class MainActivity : AppCompatActivity() {

    val offices = arrayOf("MP", "NP", "EP", "CP")

    fun launchBreviary(datePicker: DatePicker, office: String) {
        val uri = Uri.parse("http://www.stbedeproductions.com/breviary/mobile/mobile_office.php?m=${ datePicker.getMonth() + 1 }&d=${ datePicker.getDayOfMonth() }&y=${ datePicker.getYear() }&office=${office}&cookie_value=2211204111000undefined0undefinedundefinedundefinedundefined000undefinedundefined0020121undefined01undefined1100011100")
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(browserIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker = findViewById(R.id.datePicker) as DatePicker
        val spinner = findViewById(R.id.spinner) as Spinner
        val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, offices)
        spinner.setAdapter(adapter)

        val now = Calendar.getInstance()
        spinner.setSelection(selectPrayer(now))

        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            launchBreviary(datePicker, spinner.getSelectedItem() as String)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun selectPrayer(time: Calendar): Int =
        when (time.get(Calendar.HOUR_OF_DAY)) {
            in 5..11 -> 0
            in 12..17 -> 1
            in 18..20 -> 2
            else -> 3
        }
}
