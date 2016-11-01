package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import br.com.quaddro.emprestes.qandroid100.R;

public class SharedPreferenceActivity extends PreferenceActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.shared_preference_view);
    }
}