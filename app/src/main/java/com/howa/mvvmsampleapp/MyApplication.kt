package com.howa.mvvmsampleapp

import android.app.Application
import com.howa.mvvmsampleapp.data.db.AppDatabase
import com.howa.mvvmsampleapp.data.network.APIService
import com.howa.mvvmsampleapp.data.network.NetworkInterceptor
import com.howa.mvvmsampleapp.data.preferences.PreferenceProvider
import com.howa.mvvmsampleapp.data.repositories.ProductsRepository
import com.howa.mvvmsampleapp.data.repositories.UserRepo
import com.howa.mvvmsampleapp.ui.auth.AuthViewModelFactory
import com.howa.mvvmsampleapp.ui.home.products.ProductsModelFactory
import com.howa.mvvmsampleapp.ui.home.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton { NetworkInterceptor(instance()) }
        bind() from singleton { APIService(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepo(instance(), instance()) }
        bind() from singleton { ProductsRepository(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { ProductsModelFactory(instance()) }
    }

}