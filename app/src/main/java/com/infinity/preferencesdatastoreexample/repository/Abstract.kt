package com.infinity.preferencesdatastoreexample.repository

import com.infinity.preferencesdatastoreexample.Phonebook
import kotlinx.coroutines.flow.Flow

interface Abstract {

    suspend fun savePhoneBook(phonebook: Phonebook)

    suspend fun getPhoneBook():Flow<Phonebook>
}