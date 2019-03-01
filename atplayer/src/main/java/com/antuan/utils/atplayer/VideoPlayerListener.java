package com.antuan.utils.atplayer;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 提供回调的接口
 * Created by GuoShaoHong on 2017/3/15.
 */

public abstract class VideoPlayerListener implements IMediaPlayer.OnBufferingUpdateListener
        ,IMediaPlayer.OnPlayingUpdateListener, IMediaPlayer.OnCompletionListener
        , IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener
        , IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnErrorListener
        , IMediaPlayer.OnSeekCompleteListener,IjkMediaPlayer.OnNativeInvokeListener
{
}
