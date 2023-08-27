package raf.rs.nutritiontracker.ui.fragments

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.database.MealDBConverter
import raf.rs.nutritiontracker.ui.viewmodels.MealDBViewModel
import raf.rs.nutritiontracker.model.entities.MealDetails
import raf.rs.nutritiontracker.ui.contracts.MainContract
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MealSaveFragment(private val meal: MealDetails?, private val editMode: Boolean = false) : Fragment(R.layout.meal_save) {
    private val mealDBViewModel: MainContract.DBViewModel by viewModel<MealDBViewModel>()

    private var datePickerDialog: DatePickerDialog? = null

    private lateinit var takePictureLauncher: ActivityResultLauncher<Intent>
    private var imagePath: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveMealThumb = view.findViewById<ImageView>(R.id.saveMealThumb)
        val saveMealStr = view.findViewById<TextView>(R.id.saveMealStr)
        val saveMealDate = view.findViewById<Button>(R.id.saveMealDate)
        val saveMealSpinner = view.findViewById<Spinner>(R.id.saveMealSpinner)
        val saveMealBtn = view.findViewById<Button>(R.id.saveMealBtn)

        Glide.with(saveMealThumb.context)
            .load(
                if (meal?.strImageSource == null) {
                    meal?.strMealThumb
                } else {
                    meal.strImageSource
                }
            )
            .into(saveMealThumb)

        saveMealStr.text = meal?.strMeal

        saveMealDate.text = getDate()
        saveMealDate.setOnClickListener {
            datePickerDialog?.show()
        }
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val date = getMonth(month + 1) + " " + day + " " + year
                saveMealDate!!.text = date
            }
        val cal: Calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            requireView().context,
            0,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )

        ArrayAdapter.createFromResource(
            view.context,
            R.array.meal_spinner,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            saveMealSpinner.adapter = arrayAdapter
        }

        saveMealBtn.setOnClickListener {
            val mealEntity = MealDBConverter.toMealEntity(meal)!!
            val date = Date()
            mealEntity.dateModified = date.time
            mealEntity.datePlanned = saveMealBtn.text as String
            mealEntity.dayPeriod = saveMealSpinner.selectedItem as String
            if (editMode) {
                mealDBViewModel.updateMealInDB(mealEntity, object : MainContract.MealDBCallback {
                    override fun onMealSuccess() {
                        Toast.makeText(view.context, "Meal inserted.", Toast.LENGTH_SHORT).show()
                        activity?.supportFragmentManager?.popBackStack()
                    }
                    override fun onMealError(error: Throwable) {
                        Toast.makeText(view.context, "Meal not inserted.", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            mealDBViewModel.insertMealInDB(mealEntity, object : MainContract.MealDBCallback {
                override fun onMealSuccess() {
                    Toast.makeText(view.context, "Meal inserted.", Toast.LENGTH_SHORT).show()
                    activity?.supportFragmentManager?.popBackStack()
                }
                override fun onMealError(error: Throwable) {
                    Toast.makeText(view.context, "Meal not inserted.", Toast.LENGTH_SHORT).show()
                }
            })
        }

        takePictureLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val imageBitmap = result.data?.extras?.get("data") as? Bitmap
                    if (imageBitmap != null) {
                        if (!imagePath.isNullOrBlank()) {
                            meal?.strImageSource = imagePath
                            meal?.strMealThumb = imagePath
                        }
                        saveMealThumb.setImageBitmap(imageBitmap)
                        try {
                            val fos = FileOutputStream(File(imagePath!!))
                            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                            fos.flush()
                            fos.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    } else {
                        Toast.makeText(view.context, "Mail not inserted.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        saveMealThumb.setOnClickListener {
            val camPerm = Manifest.permission.CAMERA
            if (ContextCompat.checkSelfPermission(
                    view.context,
                    camPerm
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val actionImageCaptureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                val externalFilesDir = view.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val imageFile = File.createTempFile("IMG_" + SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date()), ".jpeg", externalFilesDir)

                imagePath = imageFile.absolutePath
                actionImageCaptureIntent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE, "data")
                takePictureLauncher.launch(actionImageCaptureIntent)
            } else {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(camPerm), 123)
            }
        }
    }

    private fun getDate(): String {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH) + 1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        return "$year\\$month\\$day"
    }

    private fun getMonth(month: Int): String {
        when (month) {
            1 -> return "JAN"
            2 -> return "FEB"
            3 -> return "MAR"
            4 -> return "APR"
            5 -> return "MAY"
            6 -> return "JUN"
            7 -> return "JUL"
            8 -> return "AUG"
            9 -> return "SEP"
            10 -> return "OCT"
            11 -> return "NOV"
        }
        return "DEC"
    }
}