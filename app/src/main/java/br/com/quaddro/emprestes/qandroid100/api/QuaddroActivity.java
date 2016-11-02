package br.com.quaddro.emprestes.qandroid100.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public abstract class QuaddroActivity extends AppCompatActivity {

    protected void setOnClickListener(int id, Class<?> cl) {
        Button btnView = (Button) findViewById(id);
        btnView.setOnClickListener(new OnClickAction(this, cl));
    }

    protected <T extends View> T getViewById(int id) {
        return (T) findViewById(id);
    }

    private class OnClickAction implements View.OnClickListener {

        private Context c;
        private Class<?> cl;

        OnClickAction(Context c, Class<?> cl) {
            super();

            this.c = c;
            this.cl = cl;
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(c, cl);

            startActivity(i);
        }
    }

    protected SharedPreferences getSharedPreferences() {
        return getSharedPreferences("br.com.quaddro.emprestes.qandroid100_preferences", MODE_PRIVATE);
    }

    protected boolean getBooleanOrFalse(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }
}
