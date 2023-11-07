package com.donation.authdonationtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configura os inputs de email e senha e o botão de login
        setupLoginInterface()

        loginButton.setOnClickListener {
            performLogin()
        }
    }

    private fun setupLoginInterface() {
        emailInput = findViewById(R.id.emailInput) // ID do EditText de email
        passwordInput = findViewById(R.id.passwordInput) // ID do EditText de senha
        loginButton = findViewById(R.id.loginButton) // ID do botão de login
    }

    private fun performLogin() {
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, insira e-mail e senha.", Toast.LENGTH_SHORT).show()
            return
        }

        val user = Authenticator.login(email, password)
        if (user != null) {
            // Login bem-sucedido
            Toast.makeText(this, "Login bem-sucedido para o usuário: ${user.username}", Toast.LENGTH_SHORT).show()
            navigateToTokenGenerator()
        } else {
            // Login falhou
            Toast.makeText(this, "Login falhou. Por favor, tente novamente.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToTokenGenerator() {
        val intent = Intent(this, TokenGeneratorActivity::class.java)
        startActivity(intent)
        finish() // Finaliza a MainActivity
    }
}
