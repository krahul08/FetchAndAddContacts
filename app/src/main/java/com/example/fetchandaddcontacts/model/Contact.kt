package com.example.fetchandaddcontacts.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class Contact : BaseObservable() {

    @get:Bindable
    var name: String? = null

    @get:Bindable
    var phoneNumber: String? = null
}