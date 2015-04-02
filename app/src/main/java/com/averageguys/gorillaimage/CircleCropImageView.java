package com.averageguys.gorillaimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Omatt on 4/1/2015.
 */
public class CircleCropImageView extends ImageView {
    public CircleCropImageView(Context context) {
        super(context);
    }

    public CircleCropImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleCropImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    private void setUp(){
        setScaleType(ScaleType.CENTER_CROP);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        if(bitmap.getWidth() < bitmap.getHeight() ) {
            canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                    bitmap.getWidth() / 2, paint);
        } else{
            canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                    bitmap.getHeight() / 2, paint);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        super.setImageBitmap(output);
    }

    @Override
    public void setImageResource(int resId) {
        Bitmap imgBitmap = BitmapFactory.decodeResource(getResources(), resId);
        setImageBitmap(imgBitmap);
    }

    @Override
    public void setBackgroundResource(int resId) {
        Bitmap imgBitmap = BitmapFactory.decodeResource(getResources(), resId);
        setImageBitmap(imgBitmap);
    }
}
