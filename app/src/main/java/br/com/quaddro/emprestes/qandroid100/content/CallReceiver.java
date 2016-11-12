package br.com.quaddro.emprestes.qandroid100.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        Toast.makeText(context, "Alguém ligando...", Toast.LENGTH_SHORT).show();
        Log.d("CALL", "Alguém chamando...");
        TelephonyManager tm;

        tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        tm.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case 1: // telefone tocando...
                        Toast.makeText(context,
                                String.format("Chamada de: %s", incomingNumber),
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        },
        PhoneStateListener.LISTEN_CALL_STATE);
    }
}
