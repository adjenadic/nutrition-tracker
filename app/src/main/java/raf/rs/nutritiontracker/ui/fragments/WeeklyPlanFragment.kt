package raf.rs.nutritiontracker.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import raf.rs.nutritiontracker.R

class WeeklyPlanFragment : Fragment(R.layout.weekly_plan_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnMonday: Button = view.findViewById(R.id.btnMonday)
        val btnTuesday: Button = view.findViewById(R.id.btnTuesday)
        val btnWednesday: Button = view.findViewById(R.id.btnWednesday)
        val btnThursday: Button = view.findViewById(R.id.btnThursday)
        val btnFriday: Button = view.findViewById(R.id.btnFriday)
        val btnSaturday: Button = view.findViewById(R.id.btnSaturday)
        val btnSunday: Button = view.findViewById(R.id.btnSunday)
        val btnReview: Button = view.findViewById(R.id.btnReview)

        btnMonday.setOnClickListener {
            fragmentTransaction("Monday")
        }
        btnTuesday.setOnClickListener {
            fragmentTransaction("Tuesday")
        }
        btnWednesday.setOnClickListener {
            fragmentTransaction("Wednesday")
        }
        btnThursday.setOnClickListener {
            fragmentTransaction("Thursday")
        }
        btnFriday.setOnClickListener {
            fragmentTransaction("Friday")
        }
        btnSaturday.setOnClickListener {
            fragmentTransaction("Saturday")
        }
        btnSunday.setOnClickListener {
            fragmentTransaction("Sunday")
        }

        btnReview.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainFL, ReviewPlanFragment())
            fragmentTransaction.addToBackStack("back4")
            fragmentTransaction.commit()
        }
    }

    private fun fragmentTransaction(day: String) {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFL, DailyPlanFragment(day))
        fragmentTransaction.addToBackStack("back3")
        fragmentTransaction.commit()
    }
}