package tv.danmaku.ijk.media.player.ffmpeg;
import com.antuan.utils.atplayer.VideobackInterFace;

import tv.danmaku.ijk.media.player.pragma.DebugLog;

//m3u预加载
public class IjkPreloader {
    private final static String TAG = IjkPreloader.class.getName();
    protected long mNativeAddress=0;
    protected boolean mStarted=false;

    public IjkPreloader(){
        mNativeAddress = nativeCreate();
    }

    public VideobackInterFace videobackInterFace;


    //析构
    protected void finalize() {
        release();
    }

    //设置缓存路径
    public void setCachePath(String path){
        if(mNativeAddress != 0) {
            nativeSetCachePath(mNativeAddress,path);
        }
    }

    //设置缓存大小可以设置为500KB
    public void setCacheSize(int bytes){
        if(mNativeAddress != 0) {
            nativeSetCacheSize(mNativeAddress,bytes);
        }
    }

    //开始缓存
    public void start(String url){
        if(mNativeAddress != 0 && !mStarted) {
            nativeStart(mNativeAddress,url);
            mStarted = true;
        }
    }

    //停止缓存
    public void stop(){
        if(mNativeAddress != 0 && mStarted) {
            nativeStop(mNativeAddress);
            mStarted = false;
        }
    }

    //释放
    public void release(){
        if(mNativeAddress != 0) {
            stop();
            nativeRelease(mNativeAddress);
            mNativeAddress = 0;
        }
    }

    //jni缓存完毕调用
    protected  void onFinish(int ret){
        DebugLog.d(TAG,"preload finish "+ret);
        if(videobackInterFace != null) {
            videobackInterFace.onFinish(ret);
        }
    }

    protected native long nativeCreate();
    protected native void nativeRelease(long holder);
    protected native void nativeSetCachePath(long holder,String path);
    protected native boolean nativeStart(long holder,String url);
    protected native void nativeStop(long holder);
    protected native void nativeSetCacheSize(long holder,int size);
}
