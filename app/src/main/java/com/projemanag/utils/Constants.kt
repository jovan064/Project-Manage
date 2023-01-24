package com.projemanag.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import com.projemanag.activities.MyProfileActivity
import com.projemanag.utils.Constants.PICK_IMAGE_REQUEST_CODE

object Constants {

     const val READ_STORAGE_PERMISSION_CODE = 1
     const val PICK_IMAGE_REQUEST_CODE = 2
     const val USERS: String = "users"
     const val IMAGE: String = "image"
     const val NAME: String = "name"
     const val MOBILE: String = "mobile"
     const val BOARDS: String = "boards"
     const val ASSIGNED_TO: String = "assignedTo"
     const val DOCUMENT_ID: String = "documentId"

     fun showImageChooser(activity: Activity) {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
       activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

     fun getFileExtension(activity: Activity, uri: Uri?): String? {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }
}