package ceshi.com.ceshi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nys.xmarqueeview.XMarqueeView;
import com.nys.xmarqueeview.XMarqueeViewAdapter;

import java.util.List;

import ceshi.com.ceshi.R;

/**
 * @author: xiaohaibin.
 * @time: 2018/6/6
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 创建MarqueeView适配器
 */
public class MarqueeViewAdapter extends XMarqueeViewAdapter<String> {

    private Context mContext;
    public MarqueeViewAdapter(List<String> datas, Context context) {
        super(datas);
        mContext = context;
    }

    @Override
    public View onCreateView(XMarqueeView parent) {
        //跑马灯单个显示条目布局，自定义
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marqueeview, null);
    }

    @Override
    public void onBindView(View parent, View view, final int position) {
        //布局内容填充
        TextView tvOne = (TextView) view.findViewById(R.id.tv_gift_title);
        tvOne.setText(mDatas.get(position));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}