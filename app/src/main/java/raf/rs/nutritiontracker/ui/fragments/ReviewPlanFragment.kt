package raf.rs.nutritiontracker.ui.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.PlanViewModel

class ReviewPlanFragment : Fragment(R.layout.review_plan_layout) {
    private val planViewModel: MainContract.PlanViewModel by viewModel<PlanViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val planChosenDay: TextView = view.findViewById(R.id.planChosenDay)
        val planEmail: EditText = view.findViewById(R.id.planEmail)
        val sendEmailButton: Button = view.findViewById(R.id.sendEmailButton)

        val chosenPlan = planViewModel.getChosenPlan().value

        val sb = StringBuilder()
        val sb2 = StringBuilder()
        for (key in chosenPlan?.keys!!) {
            val value = chosenPlan[key]
            sb.append(key)
            sb2.append(key)
            sb.append(": \n")
            sb2.append(": \n")
            for (key2 in value?.keys!!) {
                val xd = value[key2]
                sb.append(key2)
                sb2.append(key2)
                sb.append(": ")
                sb2.append(": ")
                sb.append(xd!!.strMeal)
                sb2.append(xd.toString())
                sb.append("\n")
                sb2.append("\n")
            }
        }
        planChosenDay.text = sb

        sendEmailButton.setOnClickListener {
            val intent = Intent()
                .setAction(Intent.ACTION_SEND)
                .setType("message/rfc822")
                .putExtra(Intent.EXTRA_EMAIL, arrayOf(planEmail.text.toString()))
                .putExtra(Intent.EXTRA_SUBJECT, "Nutrition Tracker Weekly Plan")
                .putExtra(Intent.EXTRA_TEXT, sb2.toString())
            try {
                startActivity(Intent.createChooser(intent, "Send mail"))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "There are no email clients installed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}