package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.wl.workutils.R;

import net.lemonsoft.lemonbubble.LemonBubble;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @Bind(R.id.edit_content)
    EditText editContent;
    @Bind(R.id.btn_onclick)
    Button btnOnclick;
    @Bind(R.id.layout_content)
    LinearLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_onclick)
    public void onViewClicked() {
        LemonBubble.showRight(this, "集成成功！", 10000);
    }
}
