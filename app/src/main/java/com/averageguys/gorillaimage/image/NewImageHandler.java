package com.averageguys.gorillaimage.image;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.averageguys.gorillaimage.R;

/**
 * Created by Omatt on 4/3/2015.
 */
public class NewImageHandler {
    public static void showPopup(final Activity activity, final int REQUEST_IMAGE_CAPTURE, final int REQUEST_PICK_IMAGE) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.popup_new_image_menu);

        Button btnCamera = (Button) dialog.findViewById(R.id.btn_new_image_camera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                CameraHandler.gotoCamera(activity, REQUEST_IMAGE_CAPTURE);
            }
        });

        Button btnGallery = (Button) dialog.findViewById(R.id.btn_new_image_gallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                GalleryHandler.gotoPhotoGallery(activity, REQUEST_PICK_IMAGE);
            }
        });

        Button btnCancel = (Button) dialog.findViewById(R.id.btn_new_image_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
