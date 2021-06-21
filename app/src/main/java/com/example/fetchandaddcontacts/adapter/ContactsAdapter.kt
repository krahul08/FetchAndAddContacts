package com.example.fetchandaddcontacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchandaddcontacts.R
import com.example.fetchandaddcontacts.adapter.ContactsAdapter.ContactViewHolder
import com.example.fetchandaddcontacts.databinding.ItemContactListBinding
import com.example.fetchandaddcontacts.model.Contact

class ContactsAdapter : RecyclerView.Adapter<ContactViewHolder>() {

    private lateinit var contacts: List<Contact>

    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ContactViewHolder {
        val binding: ItemContactListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context), R.layout.item_contact_list,
            viewGroup, false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(contactViewHolder: ContactViewHolder, i: Int) {
        contactViewHolder.onBind(contacts[i])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    class ContactViewHolder(private val binding: ItemContactListBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun onBind(contact: Contact) {
            binding.contactName.text = contact.name
            binding.contactNumber.text = contact.phoneNumber
            binding.executePendingBindings()
        }
    }
}