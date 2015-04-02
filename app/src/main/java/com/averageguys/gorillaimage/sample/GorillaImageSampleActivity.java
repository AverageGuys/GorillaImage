package com.averageguys.gorillaimage.sample;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.averageguys.gorillaimage.CircleCropImageView;
import com.averageguys.gorillaimage.image.ImageHelper;
import com.averageguys.gorillaimage.R;
import com.averageguys.gorillaimage.utils.GlobalValues;


public class GorillaImageSampleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorilla_image_sample);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new GorillaImageSampleFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gorilla_image_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment aboutFragment = getSupportFragmentManager().findFragmentByTag(GlobalValues.FRAG_TAG_ABOUT);
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            if (aboutFragment == null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                ft.replace(R.id.container, new AboutFragment(), GlobalValues.FRAG_TAG_ABOUT);
                ft.addToBackStack(GlobalValues.FRAG_TAG_MAIN);
                ft.commit();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class GorillaImageSampleFragment extends Fragment {

        public GorillaImageSampleFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_gorilla_image_sample, container, false);

            CircleCropImageView mCircleCropImageView = (CircleCropImageView) rootView.findViewById(R.id.cc_imgview);
            // Scale to display image on ImageView efficiently
            mCircleCropImageView.setImageBitmap(ImageHelper.setImageBitmapFromResource(Math.round(getResources().getDimension(R.dimen.img_dimension)),
                    Math.round(getResources().getDimension(R.dimen.img_dimension)), getResources(), R.drawable.img_gorilla));

            return rootView;
        }
    }
}
