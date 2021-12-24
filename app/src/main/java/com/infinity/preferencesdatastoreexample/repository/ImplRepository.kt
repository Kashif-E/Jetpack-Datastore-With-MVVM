package com.infinity.preferencesdatastoreexample.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.infinity.preferencesdatastoreexample.Phonebook
import kotlinx.coroutines.flow.map


const val DataStore_NAME = "PHONEBOOK"

val Context.datastore : DataStore< Preferences> by  preferencesDataStore(name = DataStore_NAME)


class ImplRepository(private val context: Context) : Abstract {

    companion object{
        val NAME = stringPreferencesKey("NAME")
        val PHONE_NUMBER = stringPreferencesKey("PHONE")
        val address = stringPreferencesKey("ADDRESS")
    }

    override suspend fun savePhoneBook(phonebook: Phonebook) {
        context.datastore.edit { phonebooks->
            phonebooks[NAME] = phonebook.name
            phonebooks[PHONE_NUMBER]= phonebook.phone
            phonebooks[address]= phonebook.address

        }

    }

    override suspend fun getPhoneBook() = context.datastore.data.map { phonebook ->
        Phonebook(
            name = phonebook[NAME]!!,
            address =  phonebook[address]!!,
            phone = phonebook[PHONE_NUMBER]!!
        )
    }
}