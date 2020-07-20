package com.howa.mvvmsampleapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.howa.mvvmsampleapp.R
import com.howa.mvvmsampleapp.databinding.ActivityLoginBinding
import com.howa.mvvmsampleapp.ui.home.HomeActivity
import com.howa.mvvmsampleapp.util.APIExceptions
import com.howa.mvvmsampleapp.util.NoInternetException
import com.howa.mvvmsampleapp.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {

        val email = binding.edtUsername.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        lifecycleScope.launch {
            try {

                val response = viewModel.userLogin(email, password)
                if (response.success!! && response.data != null) {
                    viewModel.saveLoggedUser(response.data)
                } else {
                    root_layout.snackbar(response.message!!)
                }

            } catch (e: APIExceptions) {
            } catch (e: NoInternetException) {
            }
        }
    }

/*    override fun onStarted() {

        progress_bar.show()
       }

    override fun onSuccess(response: User?) {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        root_layout.snackbar(message)
        progress_bar.hide()
    }*/


}