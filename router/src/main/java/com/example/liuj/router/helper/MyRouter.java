package com.example.liuj.router.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by liuj on 2017/12/8.
 */
public class MyRouter {

    public static final HashMap<String, Class<? extends Activity>> mappingList = new HashMap<>();

    public static final void map(String target, Class<? extends Activity> act) {
        if (mappingList.containsKey(target)) {
            String msg = String.format("%s, taget:%s, ", "target must be unique", target, act.getName());
            throw new RuntimeException(msg);
        }

        mappingList.put(target, act);
    }

    public static final void open(Context context, String target) {
        if (!mappingList.containsKey(target)) {
            Toast.makeText(context, "target 不存在", Toast.LENGTH_LONG).show();
            return;
        }
        Class<? extends Activity> activity = mappingList.get(target);
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

}