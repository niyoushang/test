package ceshi.com.ceshi;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nys.xmarqueeview.XMarqueeView;

import java.util.ArrayList;
import java.util.List;

import ceshi.com.ceshi.adapter.MarqueeViewAdapter;
import tv.danmaku.ijk.media.player.ffmpeg.IjkPreloader;

public class ListScrollActivity extends FragmentActivity {

    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_scroll);
        initView();
    }

    private void initView() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            mDatas.add("这是跑马灯内容" + (i + 1));
        }

        XMarqueeView marqueeviewone = (XMarqueeView) findViewById(R.id.marquee1);
        marqueeviewone.setAdapter(new MarqueeViewAdapter(mDatas, this));

        //刷新数据
//        marqueeViewAdapter.setData(mDatas);
    }
}
