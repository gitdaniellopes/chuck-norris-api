package br.com.daniellopes.chucknorrisapi.interfaces;

import java.util.List;

import br.com.daniellopes.chucknorrisapi.model.Joke;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChuckNorrisAPI {

    String BASE_URL = "https://api.chucknorris.io/";

    @GET("jokes/categories")
    Call<List<String>> findAll();

    @GET("jokes/random")
    Call<Joke> findRandomBy(@Query("category") String category);
}
