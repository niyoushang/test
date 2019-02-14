package tv.danmaku.ijk.media.player.ffmpeg;

//m3u预加载
public class M3uPreloader {

    protected long mNativeAddress=0;
    protected boolean mStarted=false;

    public M3uPreloader(){
        mNativeAddress = nativeCreate();
    }

    //析构
    protected void finalize() {
        if(mNativeAddress != 0) {
            stop();
            nativeRelease(mNativeAddress);
            mNativeAddress = 0;
        }
    }

    //设置缓存路径
    public void setCachePath(String path){
        if(mNativeAddress != 0) {
            nativeSetCachePath(mNativeAddress,path);
        }
    }

    //重试次数
    public void setReloadCount(int count){
        if(mNativeAddress != 0) {
            nativeSetReloadCount(mNativeAddress,count);
        }
    }

    //设置缓存的切片数
    public void setCacheCount(int count){
        if(mNativeAddress != 0) {
            nativeSetCacheCount(mNativeAddress,count);
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

    protected native long nativeCreate();
    protected native void nativeRelease(long holder);
    protected native void nativeSetCachePath(long holder,String path);
    protected native void nativeSetReloadCount(long holder,int count);
    protected native void nativeSetCacheCount(long holder,int count);
    protected native boolean nativeStart(long holder,String url);
    protected native void nativeStop(long holder);
}
