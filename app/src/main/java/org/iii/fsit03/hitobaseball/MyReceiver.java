package org.iii.fsit03.hitobaseball;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MyReceiver extends BroadcastReceiver {
    private Class myClass;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.i("brad", "receive");
        String target = intent.getStringExtra("target");
        //Log.i("brad", ""+target);
        switch (target){
            case "Main":
                myClass = ManageTeamActivity.class;
                break;
            case "ManageTeam":
                myClass = ManageGameActivity.class;
                break;

        }
        ArrayList data = (ArrayList) intent.getSerializableExtra("data");
        Intent it = new Intent(context, myClass);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        it.putExtras(bundle);
        context.startActivity(it);
    }
}
