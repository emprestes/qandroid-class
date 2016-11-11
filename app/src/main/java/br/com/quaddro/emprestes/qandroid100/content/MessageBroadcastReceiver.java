package br.com.quaddro.emprestes.qandroid100.content;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import br.com.quaddro.emprestes.qandroid100.controller.MensagemService;

public class MessageBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 5s -> ANR
        Log.i("receiver", "onReceive");
        Toast.makeText(context, "Ligado!!!!", Toast.LENGTH_SHORT).show();

        AlarmManager am;
        PendingIntent pi;

        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        pi = PendingIntent.getService(context,
                100,
                new Intent(context, MensagemService.class),
                PendingIntent.FLAG_ONE_SHOT);

        am.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                pi);

        Log.d("receiver", "AlarmManager configurado!");
    }
}
