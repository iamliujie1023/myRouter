package com.example.liuj.router;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.liuj.router.annotation.Router;

/**
 * Created by liuj on 2017/12/8.
 */
@Router(target = Const.ACT_A)
public class BaseAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getClass().getSimpleName());
    }

}
