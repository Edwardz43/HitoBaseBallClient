package org.iii.fsit03.hitobaseball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList data;
    private DatagetterThread datagetterThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view){
        datagetterThread = new DatagetterThread("team");
    }

    public void next(){
        data = datagetterThread.getData();
        Intent it = new Intent(this, ManageTeamActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        it.putExtras(bundle);
        startActivity(it);
    }
}
