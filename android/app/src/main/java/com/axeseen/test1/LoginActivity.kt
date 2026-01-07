package com.axeseen.test1

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.progressBar)

        btnLogin.setOnClickListener {
            if (validateInputs()) {
                doFakeLogin()
            }
        }
    }

    private fun validateInputs(): Boolean {
        var valid = true

        val email = etEmail.text?.toString()?.trim() ?: ""
        val password = etPassword.text?.toString() ?: ""

        if (email.isEmpty()) {
            tilEmail.error = "이메일을 입력하세요"
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "유효한 이메일을 입력하세요"
            valid = false
        } else {
            tilEmail.error = null
        }

        if (password.isEmpty()) {
            tilPassword.error = "비밀번호를 입력하세요"
            valid = false
        } else if (password.length < 6) {
            tilPassword.error = "비밀번호는 최소 6자리 이상이어야 합니다"
            valid = false
        } else {
            tilPassword.error = null
        }

        return valid
    }

    private fun doFakeLogin() {
        // 간단한 시뮬레이션: 버튼 비활성화 + 프로그레스바 표시 후 토스트
        btnLogin.isEnabled = false
        progressBar.visibility = View.VISIBLE

        // 실제 앱에서는 네트워크 호출이나 ViewModel을 사용하세요.
        progressBar.postDelayed({
            progressBar.visibility = View.GONE
            btnLogin.isEnabled = true
            Toast.makeText(this, "로그인 성공(샘플)", Toast.LENGTH_SHORT).show()
            // TODO: 로그인 성공 시 다음 화면으로 이동 처리
        }, 1400)
    }
}
