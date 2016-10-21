package br.com.quaddro.emprestes.qandroid100.api;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class QuaddroActivity extends AppCompatActivity {

    protected void setOnClickListener(int id, Class<?> cl) {
        Button btnView = (Button) findViewById(id);
        btnView.setOnClickListener(new OnClickAction(this, cl));
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
}
