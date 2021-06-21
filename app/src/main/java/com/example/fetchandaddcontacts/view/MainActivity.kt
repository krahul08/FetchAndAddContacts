package com.example.fetchandaddcontacts.view

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchandaddcontacts.R
import com.example.fetchandaddcontacts.adapter.ContactsAdapter
import com.example.fetchandaddcontacts.databinding.ActivityMainBinding
import com.example.fetchandaddcontacts.utils.BottomSheetUtils
import com.example.fetchandaddcontacts.utils.PermissionUtils
import com.example.fetchandaddcontacts.viewmodel.ContactViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var adapter: ContactsAdapter
    private lateinit var permissionUtils: PermissionUtils


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewInitialisation()
        listenerInitialisation()
        checkPermissions()
    }

    private fun viewInitialisation() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        contactViewModel = ContactViewModel(applicationContext)
        mainBinding.viewModel = contactViewModel
    }

    private fun listenerInitialisation() {
        permissionUtils = PermissionUtils(this)

        mainBinding.addContact.setOnClickListener {
            BottomSheetUtils().show(supportFragmentManager, "Add Contact Dialog")
        }
    }

    private fun checkPermissions() {
//        if (permissionUtils.isPermissionGranted()) {
//            initRecyclerView()
//        }
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    initRecyclerView()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }

    private fun initRecyclerView() {
        mainBinding.contactListView.layoutManager =
            LinearLayoutManager(mainBinding.contactListView.context)
        adapter = ContactsAdapter()

        adapter.setContacts(contactViewModel.getContacts())
        mainBinding.contactListView.adapter = adapter

    }
}