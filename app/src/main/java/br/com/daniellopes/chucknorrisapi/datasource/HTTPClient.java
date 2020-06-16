package br.com.daniellopes.chucknorrisapi.datasource;

import br.com.daniellopes.chucknorrisapi.interfaces.ChuckNorrisAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HTTPClient {
    static Retrofit retrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ChuckNorrisAPI.BASE_URL)
                .build();
    }
}
