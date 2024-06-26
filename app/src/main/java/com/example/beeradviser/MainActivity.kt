package com.example.beeradviser

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // getting reference to the views
        val findBeer = findViewById<Button>(R.id.find_beer)
        val brands = findViewById<TextView>(R.id.brands)
        val beerColor = findViewById<Spinner>(R.id.beer_color)

        // beer colors map
        val beerBrands = mapOf(
            "Light" to listOf("Pilsner Urquell", "Budweiser", "Heineken", "Miller Lite"),
            "Amber" to listOf("Alaskan Amber", "Stone Levitation Ale", "Bell’s Amber Ale", "at Tire Amber Ale"),
            "Brown" to listOf("Newcastle Brown Ale", "Samuel Smith's Nut Brown Ale", "Brooklyn Brown Ale", "Dogfish Head Indian Brown Ale"),
            "Dark" to listOf("Guinness Draught", "Samuel Adams Boston Lager", "Sierra Nevada Porter", "Founders Porter")
        )

        // lambda to create a new string with \n character
        val finalString: (x:List<String>) -> String = {it.joinToString(separator = "\n")}

        // method that will return a list of beers based on selected color
        fun getBeers(selectedColor: String) {
            brands.text = beerBrands[selectedColor]?.let { finalString(it) }
        }

        findBeer.setOnClickListener {
            val color = beerColor.selectedItem.toString() // getting the current value selected in the spinner
            getBeers(color)
        }
    }
}