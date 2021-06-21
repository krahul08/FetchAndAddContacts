package com.example.fetchandaddcontacts.model

import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import java.util.*
import kotlin.collections.ArrayList


class Repository(private val context: Context) {

    private val contactsList: ArrayList<Contact> = ArrayList()


    fun fetchContacts(): ArrayList<Contact> {

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val cr: ContentResolver = context.contentResolver

        val cursor = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        if (cursor != null) {
            val mobileNoSet = HashSet<String>()
            cursor.use { _ ->
                val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val numberIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                var name: String
                var number: String
                while (cursor.moveToNext()) {
                    name = cursor.getString(nameIndex)
                    number = cursor.getString(numberIndex)
                    number = number.replace(" ", "")
                    if (!mobileNoSet.contains(number)) {
                        val contact = Contact()
                        contact.name = name
                        contact.phoneNumber = number
                        contactsList.add(contact)
                        mobileNoSet.add(number)
                    }
                }
            }
        }
        return contactsList
    }

}


