package ceshi.com.ceshi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nys.test.common.DateUtil;

public class MainActivity extends AppCompatActivity {

    private String str ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DateUtil.isToday(System.currentTimeMillis());

        TextView tv_dy = findViewById(R.id.tv_dy);

        TextView tv_marquee = findViewById(R.id.tv_marquee);

        tv_dy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TikTokActivity.class);
                startActivity(intent);
            }
        });

        tv_marquee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListScrollActivity.class);
                startActivity(intent);
            }
        });

    }
}
