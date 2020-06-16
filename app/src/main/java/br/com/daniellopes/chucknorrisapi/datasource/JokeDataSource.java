package br.com.daniellopes.chucknorrisapi.datasource;

import br.com.daniellopes.chucknorrisapi.interfaces.ChuckNorrisAPI;
import br.com.daniellopes.chucknorrisapi.interfaces.JokeCallback;
import br.com.daniellopes.chucknorrisapi.model.Joke;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokeDataSource {

    public void findByJoke(JokeCallback callback, String category) {
        HTTPClient.retrofit().create(ChuckNorrisAPI.class)
                .findRandomBy(category).enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                callback.onComplete();
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                callback.onError(t.getMessage());
                callback.onComplete();
            }
        });
    }
}
