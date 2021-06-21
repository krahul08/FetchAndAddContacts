package com.example.fetchandaddcontacts.utils

import com.example.fetchandaddcontacts.view.MainActivity

class PermissionUtils(private val activity: MainActivity) {

//    this class is working fine but have issue with showing contacts on time in main activity, so as for now commented


//    fun isPermissionGranted(): Boolean {
//
//        var isPermissionGranted = false
//
//        Dexter.withContext(activity)
//            .withPermissions(
//                Manifest.permission.READ_CONTACTS,
//                Manifest.permission.WRITE_CONTACTS
//            ).withListener(object : MultiplePermissionsListener {
//                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
//                    isPermissionGranted = true
//                }
//
//                override fun onPermissionRationaleShouldBeShown(
//                    permissions: List<PermissionRequest?>?,
//                    token: PermissionToken?
//                ) {
//                    token?.continuePermissionRequest()
//                }
//            }).check()
//
//        return isPermissionGranted
//    }
}