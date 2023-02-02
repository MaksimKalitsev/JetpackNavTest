package ua.com.foxminded.jetpacknavtest.di

import android.app.Service
import android.content.Context
import androidx.fragment.app.Fragment
import ua.com.foxminded.jetpacknavtest.App

val Service.appComponent: AppComponent get() = (application as App).appComponent

val Fragment.appComponent: AppComponent get() = (requireActivity().application as App).appComponent

val Context.appComponent: AppComponent get() = (applicationContext as App).appComponent

val Service.userComponent: UserComponent? get() = (application as App).loginManager.userComponent

//    val CoroutineWorker.userComponent: UserComponent? get() = (applicationContext as App).loginManager.userComponent

val Fragment.userComponent: UserComponent? get() = (requireActivity().application as App).loginManager.userComponent

val Context.userComponent: UserComponent? get() = (applicationContext as App).loginManager.userComponent