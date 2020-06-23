package ru.axdar.chatmessenger.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.axdar.chatmessenger.R
import ru.axdar.chatmessenger.cache.AccountCacheImpl
import ru.axdar.chatmessenger.cache.SharedPrefsManager
import ru.axdar.chatmessenger.data.account.AccountRepositoryImpl
import ru.axdar.chatmessenger.domain.account.Register
import ru.axdar.chatmessenger.remote.account.AccountRemoteImpl
import ru.axdar.chatmessenger.remote.core.NetworkHandler
import ru.axdar.chatmessenger.remote.core.Request
import ru.axdar.chatmessenger.remote.service.ServiceFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sharedPrefs = this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)

        val accountCache = AccountCacheImpl(SharedPrefsManager(sharedPrefs))
        val accountRemote = AccountRemoteImpl(Request(NetworkHandler(this)), ServiceFactory.makeService(true))
        val accountRepository = AccountRepositoryImpl(accountRemote, accountCache)

        accountCache.saveToken("34567")

        val register = Register(accountRepository)
        register(Register.Params("abcd@m.com", "abcd", "321")) {
            it.either({ f ->
                Toast.makeText(this, f.javaClass.simpleName, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(this, "Аккаунт создан", Toast.LENGTH_SHORT).show()
            })
        }
    }
}