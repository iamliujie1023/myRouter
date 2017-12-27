package com.example.liuj.router;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.liuj.router.annotation.Router;

/**
 * Created by liuj on 2017/12/27.
 */
@Router(target = Const.ACT_X)
public class ActX extends BaseAct {

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "ActX onNewIntent", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}