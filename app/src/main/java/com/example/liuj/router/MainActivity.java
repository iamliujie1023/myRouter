package com.example.liuj.router;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.liuj.router.helper.MyRouter;
import com.example.liuj.router.mapping.RouterMapping;

public class MainActivity extends BaseAct implements View.OnClickListener{

    private Button mBtn1, mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RouterMapping.map();

        initView();
    }

    private void initView() {
        mBtn1 = findViewById(R.id.btn_1);
        mBtn2 = findViewById(R.id.btn_2);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == mBtn1) {
            MyRouter.open(this, Const.ACT_A);
        } else if (v == mBtn2) {
            MyRouter.open(this, Const.ACT_B);
        }
    }

}