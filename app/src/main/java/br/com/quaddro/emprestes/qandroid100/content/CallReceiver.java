package br.com.quaddro.emprestes.qandroid100.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alguém ligando...", Toast.LENGTH_SHORT).show();
        Log.d("CALL", "Alguém chamando...");
    }
}
