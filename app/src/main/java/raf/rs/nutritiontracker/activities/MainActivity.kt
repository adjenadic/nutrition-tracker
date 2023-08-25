package raf.rs.nutritiontracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.ui.fragments.CategoriesFragment
import raf.rs.nutritiontracker.ui.fragments.FilterFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        createAllMealsFragment()

        bottomNavigationView = findViewById(R.id.bottomNav)
        Timber.e(bottomNavigationView.toString())

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.browseID -> {
                    createAllMealsFragment()
                    true
                }

                R.id.filterID -> {
                    createFilterFragment()
                    true
                }

                R.id.statsID -> {
//                    createProfileFragment()
                    true
                }

                else -> false
            }
        }
    }

    private fun createAllMealsFragment() {
        val fragment = CategoriesFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFL, fragment)
        fragmentTransaction.commit()
    }

    private fun createFilterFragment() {
        val fragment = FilterFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFL, fragment)
        fragmentTransaction.commit()
    }
}