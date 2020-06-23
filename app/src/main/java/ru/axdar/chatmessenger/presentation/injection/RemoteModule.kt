package ru.axdar.chatmessenger.presentation.injection

import dagger.Module
import dagger.Provides
import ru.axdar.chatmessenger.BuildConfig
import ru.axdar.chatmessenger.data.account.AccountRemote
import ru.axdar.chatmessenger.remote.account.AccountRemoteImpl
import ru.axdar.chatmessenger.remote.core.Request
import ru.axdar.chatmessenger.remote.service.ApiService
import ru.axdar.chatmessenger.remote.service.ServiceFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ServiceFactory.makeService(BuildConfig.DEBUG)

    @Singleton
    @Provides
    fun provideAccountRemote(request: Request, apiService: ApiService): AccountRemote {
        return AccountRemoteImpl(request, apiService)
    }
}