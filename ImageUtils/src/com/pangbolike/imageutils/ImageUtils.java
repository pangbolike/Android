package com.pangbolike.imageutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * Created by alexpang on 2016/6/22.
 */
public class ImageUtils {


    /**
     * 取bitmap中心的圆形区域
     * @param bitmap
     * @param config 可为空，此时使用ARGB_8888
     * @return
     */
    public static Bitmap getCenterCircleBitMap(Bitmap bitmap, Bitmap.Config config){
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

    /**
     * android 系统从4.4开始,decodeFile不再有缓冲区
     * 所以封装此方法,增加了缓冲区,减少文件读写次数,提高性能
     * @param file
     * @return
     */
    public static Bitmap decodeFile(String file){
        try{
            return BitmapFactory.decodeStream(new BufferedInputStream(new FileInputStream(file), 8192));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 替换bitmap中非透明像素的颜色为color
     * 会创建一个可变更的新bitmap返回
     * @param bitmap
     * @param color
     * @return
     */
    public static Bitmap replaceContentColor(Bitmap bitmap, int color){
        if (null != bitmap){
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            if (null != bitmap){
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                for (int i = 0 ; i < width ; i++){
                    for (int j = 0; j < height ; j++){
                        int trans = (bitmap.getPixel(i, j) & 0xff000000);
                        if (trans != 0){
                            //保留原有透明度，不然内容区域看上去会变胖
                            bitmap.setPixel(i, j, (color & 0x00ffffff) | trans);
                        }
                    }
                }
            }
        }
        return bitmap;
    }
}
