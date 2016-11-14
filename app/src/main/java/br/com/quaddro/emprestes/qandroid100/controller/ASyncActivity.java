package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class ASyncActivity extends QuaddroActivity {

    private Button btn;
    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.async_view);

        btn = getViewById(R.id.bt_time);
        et = getViewById(R.id.et_time);
        tv = getViewById(R.id.tv_result);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = et.getText().toString();
                AsyncTaskAction action = new AsyncTaskAction();

                action.execute(time);
            }
        });
    }

    private class AsyncTaskAction extends AsyncTask<String, String, String> {

        private String resp;
        private ProgressDialog pd;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Trabalhando..");
            resp = "Iniciado";
            try {
                int time = Integer.valueOf(params[0]) * 1_000;
                // long t = 999_999_999_99L;

                Thread.sleep(time);
                resp = "Trabalhei por " + params[0] + "s";
            } catch (Exception cause) {
                Log.e("ASYNC", "Problemas", cause);
            }

            return resp;
        }

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(ASyncActivity.this,
                    "AsyncTask",
                    String.format("em andamento por %ss", tv.getText()));
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            tv.setText(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            tv.setText(values[0]);
        }
    }
}
