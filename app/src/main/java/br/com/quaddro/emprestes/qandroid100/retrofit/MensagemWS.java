package br.com.quaddro.emprestes.qandroid100.retrofit;

import java.util.List;

import br.com.quaddro.emprestes.qandroid100.model.Mensagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MensagemWS {

    @POST("quaddro/novidades2/inserir")
    Call<Mensagem> inserir(@Body Mensagem mensagem);

    @GET("quaddro/novidades2/novidades")
    Call<List<Mensagem>> listarTodas();
}
