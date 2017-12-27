package com.example.liuj.router;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.liuj.router.helper.MyRouter;
import com.example.liuj.router.mapping.RouterMapping;

public class MainActivity extends BaseAct implements View.OnClickListener {

    private Button mBtn1, mBtn2, mBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            RouterMapping.map();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initView();

        Bundle bundle = getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras();
        String msg = bundle.getString("HELLO");
        if (TextUtils.isEmpty(msg)) {
            msg = "HI";
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

    private void initView() {
        mBtn1 = findViewById(R.id.btn_1);
        mBtn2 = findViewById(R.id.btn_2);
        mBtn3 = findViewById(R.id.btn_3);


        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == mBtn1) {
            MyRouter.open(this, Const.ACT_A);
        } else if (v == mBtn2) {
            MyRouter.open(this, Const.ACT_B);
        } else if (v == mBtn3) {
            MyRouter.open(this, Const.ACT_X);
        }
    }

}