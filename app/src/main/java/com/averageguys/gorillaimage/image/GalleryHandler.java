package com.averageguys.gorillaimage.image;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Omatt on 4/3/2015.
 */
public class GalleryHandler {
    public static void gotoPhotoGallery(Activity activity, int REQUEST_PICK_IMAGE) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_IMAGE);
    }
}
