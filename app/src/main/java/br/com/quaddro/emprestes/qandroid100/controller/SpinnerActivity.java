package br.com.quaddro.emprestes.qandroid100.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class SpinnerActivity extends QuaddroActivity {

    private Spinner sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.spinner_view);

        sp = getViewById(R.id.sp_email);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this,
                R.array.emails, android.R.layout.simple_spinner_item);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(aa);
    }
}
