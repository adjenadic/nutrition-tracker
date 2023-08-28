package raf.rs.nutritiontracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.ui.fragments.CategoriesFragment
import raf.rs.nutritiontracker.ui.fragments.FilterFragment
import raf.rs.nutritiontracker.ui.fragments.StatsFragment
import raf.rs.nutritiontracker.ui.fragments.WeeklyPlanFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        transferFragment(CategoriesFragment())

        bottomNavigationView = findViewById(R.id.bottomNav)
        Timber.e(bottomNavigationView.toString())

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.browseID -> {
                    transferFragment(CategoriesFragment())
                    true
                }

                R.id.filterID -> {
                    transferFragment(FilterFragment())
                    true
                }

                R.id.statsID -> {
                    transferFragment(StatsFragment())
                    true
                }

                R.id.planID -> {
                    transferFragment(WeeklyPlanFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun transferFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFL, fragment)
        fragmentTransaction.commit()
    }
}