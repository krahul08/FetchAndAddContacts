package com.example.fetchandaddcontacts.viewmodel

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import com.example.fetchandaddcontacts.model.Contact
import com.example.fetchandaddcontacts.model.Repository

class ContactViewModel(context: Context?) : BaseObservable() {

    private val contacts: ObservableArrayList<Contact> = ObservableArrayList()
    private val repository: Repository = Repository(context!!)

    fun getContacts(): List<Contact> {
        contacts.addAll(repository.fetchContacts())
        return contacts
    }

}