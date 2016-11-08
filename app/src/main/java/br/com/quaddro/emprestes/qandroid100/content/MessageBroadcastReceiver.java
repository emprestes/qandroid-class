package br.com.quaddro.emprestes.qandroid100.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MessageBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 5s -> ANR
        Log.i("receiver", "onReceive");
    }
}
