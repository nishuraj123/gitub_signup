package com.ditya.singup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
/*import com.ditya.singup.databinding.ActivityLogInBinding
import com.ditya.singup.databinding.ActivityMainBinding*/
import com.ditya.singup.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

   /* private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }*/

    lateinit var binding: ActivitySignupBinding
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()

        /*binding.signup12.setOnClickListener{
            startActivity(Intent(this,dashboard::class.java))
            finish()
        }*/

        binding.signup12.setOnClickListener{

            val fullname:String = binding.fullname.text.toString()
            val email:String = binding.email.text.toString()
            val phoneno:String = binding.phoneno.text.toString()
            val password:String = binding.pass2.text.toString()
            val confpassword:String = binding.conp.text.toString()


            if (fullname.isEmpty()){
                binding.fullname.error = "Please enter the full name"
                binding.fullname.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()){
                binding.email.error = "Please enter valid email"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.email.error = "Invalid email!"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            if (phoneno.isEmpty()){
                binding.phoneno.error = "Please enter the phone no"
                binding.phoneno.requestFocus()
                return@setOnClickListener
            }
            if (phoneno.length < 10){
                binding.phoneno.error = "Enter correct mobile number"
                binding.phoneno.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 8){
                binding.pass2.error = "Minimum 8 characters password"
                binding.pass2.requestFocus()
                return@setOnClickListener
            }
            if (password != confpassword){
                binding.pass2.error = "Password not matches, try again"
                binding.pass2.requestFocus()
                return@setOnClickListener
            }
            RegisterFirebase(email,password)
           /* if(fullname.isEmpty() || email.isEmpty() || phoneno.isEmpty()|| password.isEmpty()||confpassword.isEmpty()){

                Toast.makeText(this,"Please fill all details",Toast.LENGTH_SHORT).show()

            }else if (password != confpassword){
                Toast.makeText(this,"Passwords are not matching",Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this) { task ->

                        if (task.isSuccessful){
                            Toast.makeText(this,"Sign up successful",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,LogInActivity::class.java))
                            finish()

                        }
                        else{
                            Toast.makeText(this,"Sign up failed : ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                        }

                    }
            }*/

        }
    }

    private fun RegisterFirebase(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"User sign up successfully ",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,LogInActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }
}