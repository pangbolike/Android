package image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by asus on 2015/5/10.
 */
public class AsyncImageView extends ImageView{

    private String TAG = "AsyncImageView";

    private String mUri;

    private File mCache;

    private String mCacheFileName;

    private Bitmap.CompressFormat mPicType;

    public AsyncImageView(Context context) {
        this(context,null);
    }

    public AsyncImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AsyncImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCache = context.getExternalCacheDir();
        Log.v(TAG, "cache path = " + mCache);
    }


    public String getUri(){
        return mUri;
    }

    public void setUri(String uri){
        mUri = uri;
        if (mUri.startsWith("/")){
            //Local File
            setImageBitMap(uri);
        }
        else{
            File cacheFile = new File(getCacheFileName());
            if (cacheFile.exists()){
                setImageBitMap(getCacheFileName());
            }
            else{
                //NetWork File
                this.setScaleType(ScaleType.CENTER_INSIDE);
                downLoad(mUri);
            }
        }
    }

    private void setImageBitMap(String filePath){
        if (TextUtils.isEmpty(filePath))
            return ;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        this.setImageBitmap(bitmap);
        this.setScaleType(ScaleType.CENTER);
    }

    private String getCacheFileName(){
        if (mCacheFileName == null){
            mCacheFileName = mCache + "/" + Utils.MD5(mUri);
            int index = mUri.lastIndexOf(".");
            if (mUri.substring(index).equals(".jpg")){
                mPicType = Bitmap.CompressFormat.JPEG;
            }
            else if (mUri.substring(index).equals(".png")){
                mPicType = Bitmap.CompressFormat.PNG;
            }
            else{
                mPicType = Bitmap.CompressFormat.WEBP;
            }
            mCacheFileName += getExt(mPicType);
        }
        return mCacheFileName;
    }

    private String getExt(Bitmap.CompressFormat type){
        if (type == Bitmap.CompressFormat.JPEG)
            return ".jpg";
        else
            return ".png";
    }

    private Bitmap.CompressFormat getPicType(){
        return mPicType;
    }
    Download d;
    private void downLoad(String url){
        if (d != null){
            d.cancel(true);
            d = null;
        }
        d = new Download(this);
        d.execute(url);
    }

    private class Download extends AsyncTask<String, Void, String> {

        private AsyncImageView aiv;

        public Download(AsyncImageView aiv) {
            this.aiv = aiv;
        }

        @Override
        protected void onPostExecute(String filePath) {
            aiv.setImageBitMap(filePath);
        }

        @Override
        protected String doInBackground(String... urls) {
            URL url;
            InputStream in;
            BufferedInputStream buf;

            try {
                url = new URL(urls[0]);
                in = url.openStream();
                buf = new BufferedInputStream(in);
                Bitmap bMap = BitmapFactory.decodeStream(buf);
                if (in != null) {
                    in.close();
                }
                if (buf != null) {
                    buf.close();
                }
                if (isCancelled()) return null;

                try {
                    FileOutputStream out = new FileOutputStream(aiv.getCacheFileName());
                    bMap.compress(aiv.getPicType(), 100, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return aiv.getCacheFileName();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
