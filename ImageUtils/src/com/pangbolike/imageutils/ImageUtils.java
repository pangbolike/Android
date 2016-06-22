package com.pangbolike.imageutils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.WindowManager;

/**
 * Created by alexpang on 2016/6/22.
 */
public class ImageUtils {


    /**
     *
     * @param bitmap
     * @param config 可为空，此时使用ARGB_8888
     * @return
     */
    public static Bitmap getCenterCircleBitMap(Bitmap bitmap,Bitmap.Config config){
        if (null == bitmap){
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int left = 0, top = 0, right = 0, bottom = 0;
        float r = 0;

        //计算圆的大小还开始位置
        if (width > height){
            int d = (width - height) / 2;
            left = d;
            right = width - d;
            bottom = height;
            r = height / 2;
            width = height;
        }else{
            int d = (height - width) / 2;
            right = width;
            top = d;
            bottom = height - d;
            r = width / 2;
        }

        float[] rArray = {r,r,r,r,r,r,r,r};

        if (null == config){
            config = Bitmap.Config.ARGB_8888;
        }

        Bitmap output = Bitmap.createBitmap(width,width, config);

        Canvas canvas = new Canvas(output);
        RectF rf = new RectF(0, 0, width, width);
        Rect src = new Rect(left,top,right,bottom);
        Path path = new Path();
        Paint paint = new Paint();

        path.addRoundRect(rf, rArray, Path.Direction.CW);
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, rf, paint);

        return output;
    }
}
