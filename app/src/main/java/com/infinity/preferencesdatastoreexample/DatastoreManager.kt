package com.infinity.preferencesdatastoreexample

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map


const val USER_DATASTORE ="datastore"
class DataStoreManager(val context: Context ){

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)
    companion object {

        val NAME = stringPreferencesKey("NAME")
        val PHONE_NUMBER = stringPreferencesKey("PHONE_NUMBER")
        val ADDRESS = stringPreferencesKey("ADDRESS")

    }



suspend fun savetoDataStore(phonebook: Phonebook) {
    context.dataStore.edit {

        it[NAME] = phonebook.name
        it[PHONE_NUMBER] = phonebook.phone
        it[ADDRESS] = phonebook.address

    }
}
suspend fun getFromDataStore() = context.dataStore.data.map {
    Phonebook(
        name = it[NAME]?:"",
        phone = it[PHONE_NUMBER]?:"",
        address = it[ADDRESS]?:""
    )
}
}