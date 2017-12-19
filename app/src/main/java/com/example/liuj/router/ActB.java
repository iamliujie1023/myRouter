package com.example.liuj.router;

import android.os.Bundle;

import com.example.liuj.router.annotation.Router;

@Router(target = Const.ACT_B)
public class ActB extends BaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
