package com.ditya.singup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ditya.singup.databinding.ActivityLogInBinding
import com.ditya.singup.databinding.ActivitySignupBinding
import com.ditya.singup.databinding.ActivityWelcomeScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogInActivity : AppCompatActivity() {

    /*private val binding: ActivityLogInBinding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
    }*/

    lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.sign.setOnClickListener{
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
            finish()
        }


         auth = FirebaseAuth.getInstance()


        binding.log.setOnClickListener{
            val username = binding.Uname.text.toString()
            val password1 = binding.Upass.text.toString()

           /* if (username.isEmpty() || password1.isEmpty()){

                Toast.makeText(this,"Please fill all details", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(username,password1)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Login successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,DashboardActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this,"Login failed${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }*/

            if (username.isEmpty()){
                binding.Uname.error= "Please enter the username"
                binding.Uname.requestFocus()
                return@setOnClickListener
            }

            if (password1.isEmpty()){
                binding.Upass.error= "Minimum 8 characters password"
                binding.Upass.requestFocus()
                return@setOnClickListener
            }
            if (password1.length <8){
                binding.Upass.error= "Wrong password"
                binding.Upass.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(username,password1)

    }


    }




    private fun LoginFirebase(username: String, password1: String) {
        auth.signInWithEmailAndPassword(username,password1)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"User sign up successfully $username",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,DashboardActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }



        }


    }

    override fun onStart() {
        super.onStart()


        val currentUser: FirebaseUser? = auth.currentUser

        if (currentUser != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }
}