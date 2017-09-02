package org.iii.fsit03.hitobaseball;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("Main");
        registerReceiver(myReceiver,filter);

    }

    @Override
    public void finish() {
        unregisterReceiver(myReceiver);
        super.finish();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    public void start(View view){
        Intent it = new Intent(this, MyIntentService.class);
        it.putExtra("option", "0");
        startService(it);
    }
}
