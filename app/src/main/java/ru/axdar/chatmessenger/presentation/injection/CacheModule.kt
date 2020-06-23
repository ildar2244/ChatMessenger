package ru.axdar.chatmessenger.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.axdar.chatmessenger.cache.AccountCacheImpl
import ru.axdar.chatmessenger.cache.SharedPrefsManager
import ru.axdar.chatmessenger.data.account.AccountCache
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAccountCache(prefsManager: SharedPrefsManager): AccountCache =
        AccountCacheImpl(prefsManager)
}