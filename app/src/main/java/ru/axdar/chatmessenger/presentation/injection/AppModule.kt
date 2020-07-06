package ru.axdar.chatmessenger.presentation.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.axdar.chatmessenger.data.account.AccountCache
import ru.axdar.chatmessenger.data.account.AccountRemote
import ru.axdar.chatmessenger.data.account.AccountRepositoryImpl
import ru.axdar.chatmessenger.data.friends.FriendsRemote
import ru.axdar.chatmessenger.data.friends.FriendsRepositoryImpl
import ru.axdar.chatmessenger.domain.account.AccountRepository
import ru.axdar.chatmessenger.domain.type.friends.FriendsRepository
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideAccountRepository(remote: AccountRemote, cache: AccountCache): AccountRepository {
        return AccountRepositoryImpl(remote, cache)
    }

    @Provides
    @Singleton
    fun provideFriendsRepository(remote: FriendsRemote, accountCache: AccountCache): FriendsRepository {
        return FriendsRepositoryImpl(accountCache, remote)
    }
}