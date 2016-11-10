package br.com.quaddro.emprestes.qandroid100.util;

import br.com.quaddro.emprestes.qandroid100.retrofit.MensagemWS;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MensagemRetrofitHelper {

    private static final String BASE_URL = "http://www.keikomayama.com.br/";

    private static MensagemRetrofitHelper HELPER;

    private MensagemWS ws;

    public static MensagemRetrofitHelper getInstance() {
        if (HELPER == null) {
            HELPER = new MensagemRetrofitHelper();
        }

        return HELPER;
    }

    private MensagemRetrofitHelper() {
        super();

        init();
    }

    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ws = retrofit.create(MensagemWS.class);
    }

    public MensagemWS getWs() {
        return ws;
    }
}
