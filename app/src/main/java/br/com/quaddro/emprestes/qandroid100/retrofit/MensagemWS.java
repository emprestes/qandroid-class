package br.com.quaddro.emprestes.qandroid100.retrofit;

import java.util.List;

import br.com.quaddro.emprestes.qandroid100.model.Mensagem;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MensagemWS {

    @GET("quaddro/novidades2/novidades")
    Call<List<Mensagem>> listarTodas();
}
