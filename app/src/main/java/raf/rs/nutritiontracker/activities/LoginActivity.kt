package raf.rs.nutritiontracker.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.model.entities.User

class LoginActivity : AppCompatActivity() {
    private var user: User = User(0, "user", "user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()

        setContentView(R.layout.login_layout)

        val fieldUsername: TextView = findViewById(R.id.fieldUsername)
        fieldUsername.text = "user"
        val fieldPassword: TextView = findViewById(R.id.fieldPassword)
        fieldPassword.text = "user"
        val loginButton: Button = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            if (fieldUsername.text.toString() == user.username && fieldPassword.text.toString() == user.password) {
                Toast.makeText(this, "Successfully logged in.", Toast.LENGTH_SHORT).show()
                startActivity()
            } else {
                Toast.makeText(this, "Failed to log in.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}