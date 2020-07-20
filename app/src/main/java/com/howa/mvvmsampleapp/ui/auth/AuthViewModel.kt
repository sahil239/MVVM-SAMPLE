package com.howa.mvvmsampleapp.ui.auth

import androidx.lifecycle.ViewModel
import com.howa.mvvmsampleapp.data.db.entities.User
import com.howa.mvvmsampleapp.data.repositories.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthViewModel(private val userRepo: UserRepo) : ViewModel() {


    fun getLoggedInUser() = userRepo.getUser()

    suspend fun userLogin(email: String, password: String) =
        withContext(Dispatchers.IO) { userRepo.userLogin(email, password) }

    suspend fun saveLoggedUser(user: User) = userRepo.saveUser(user = user)
/*
    fun onLoginButtonClicked(view : View?){

        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){

            authListener?.onFailure("$email Fail $password")
            return;
        }

        Coroutines.main {
            try {

                val response = userRepo.userLogin(email!!,password!!)
                response.data?.let {
                    if(response.success!!){
                        authListener?.onSuccess(it)
                        userRepo.saveUser(it)

                    }else{
                        authListener?.onFailure(response.message!!)
                    }
                    return@main
                }
                authListener?.onFailure(response.message!!)

            }catch (e: APIExceptions){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }

         }
    }
*/
}