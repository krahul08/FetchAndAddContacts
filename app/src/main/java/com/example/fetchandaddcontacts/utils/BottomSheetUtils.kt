package com.example.fetchandaddcontacts.utils

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.fetchandaddcontacts.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetUtils : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.content_dialog_bottom_sheet, container, false)

        val save: Button = view.findViewById(R.id.saveContact) as Button
        val name: EditText = view.findViewById(R.id.nameEditText) as EditText
        val phoneNumber: EditText = view.findViewById(R.id.phoneEditText) as EditText

        save.setOnClickListener {
            val intent = Intent(ContactsContract.Intents.Insert.ACTION)
            intent.type = ContactsContract.RawContacts.CONTENT_TYPE

            intent.putExtra(ContactsContract.Intents.Insert.NAME, name.text.toString())
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber.text).putExtra(
                ContactsContract.Intents.Insert.PHONE_TYPE,
                ContactsContract.CommonDataKinds.Phone.TYPE_WORK
            )
            startActivity(intent)
            dismiss()
        }

        return view
    }
}