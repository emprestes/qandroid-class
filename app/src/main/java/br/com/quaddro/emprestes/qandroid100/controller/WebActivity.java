package br.com.quaddro.emprestes.qandroid100.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class WebActivity extends QuaddroActivity {

    private EditText url;
    private Button go;
    private WebView content;
    private WebViewClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.web_view);

        url = getViewById(R.id.uri);
        go = getViewById(R.id.go);
        content = getViewById(R.id.content);
        client = new WebViewClient();

        content.getSettings().setJavaScriptEnabled(Boolean.TRUE);
        content.setWebViewClient(client);

        go.setOnClickListener(new View.OnClickListener() {
            private final String http = "http://";
//            private final String www = "www.";

            @Override
            public void onClick(View v) {
                String u;

                u = url.getText().toString();
//                u = u.contains(www)  ? u : www.concat(u);
                u = u.contains(http) ? u : http.concat(u);

                content.loadUrl(u);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = new MenuInflater(this);

        mi.inflate(R.menu.web_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void onMenuClicked(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.web_back:
                // Ou vc usa OOP, ou vc usa POG (anti-pattern)
                if (content.canGoBack()) {
                    content.goBack();
                }
                break;
            case R.id.web_forward:
                if (content.canGoForward()) {
                    content.goForward();
                }
                break;
        }
    }
}
