package br.com.quaddro.emprestes.qandroid100.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "SMS chegando...", Toast.LENGTH_SHORT).show();
        Log.d("SMS", "SMS chegando...");
    }
}
