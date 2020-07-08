package ru.axdar.chatmessenger.ui

import android.app.Application
import dagger.Component
import ru.axdar.chatmessenger.presentation.injection.AppModule
import ru.axdar.chatmessenger.presentation.injection.CacheModule
import ru.axdar.chatmessenger.presentation.injection.RemoteModule
import ru.axdar.chatmessenger.presentation.injection.ViewModelModule
import ru.axdar.chatmessenger.ui.core.navigation.RouteActivity
import ru.axdar.chatmessenger.ui.register.RegisterActivity
import ru.axdar.chatmessenger.ui.register.RegisterFragment
import ru.axdar.chatmessenger.ui.firebase.FirebaseService
import ru.axdar.chatmessenger.ui.friends.FriendRequestsFragment
import ru.axdar.chatmessenger.ui.friends.FriendsFragment
import ru.axdar.chatmessenger.ui.home.ChatsFragment
import ru.axdar.chatmessenger.ui.home.HomeActivity
import ru.axdar.chatmessenger.ui.login.LoginFragment
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

    //activities
    fun inject(activity: RegisterActivity)
    fun inject(activity: RouteActivity)
    fun inject(activity: HomeActivity)

    //fragments
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ChatsFragment)
    fun inject(fragment: FriendsFragment)
    fun inject(fragment: FriendRequestsFragment)

    //services
    fun inject(service: FirebaseService)
}