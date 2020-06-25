package ru.axdar.chatmessenger.ui

import android.app.Application
import dagger.Component
import ru.axdar.chatmessenger.presentation.injection.AppModule
import ru.axdar.chatmessenger.presentation.injection.CacheModule
import ru.axdar.chatmessenger.presentation.injection.RemoteModule
import ru.axdar.chatmessenger.presentation.injection.ViewModelModule
import ru.axdar.chatmessenger.ui.activity.RegisterActivity
import ru.axdar.chatmessenger.ui.fragment.RegisterFragment
import ru.axdar.chatmessenger.ui.service.FirebaseService
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}

@Singleton
@Component(modules = [AppModule::class, CacheModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: RegisterActivity)

    fun inject(fragment: RegisterFragment)

    fun inject(service: FirebaseService)
}