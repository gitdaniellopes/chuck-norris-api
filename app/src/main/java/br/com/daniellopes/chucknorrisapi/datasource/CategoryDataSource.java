package br.com.daniellopes.chucknorrisapi.datasource;

import java.util.List;

import br.com.daniellopes.chucknorrisapi.interfaces.ChuckNorrisAPI;
import br.com.daniellopes.chucknorrisapi.interfaces.ListCategoriesCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDataSource {

    public void findAll(ListCategoriesCallback callback) {
        HTTPClient.retrofit().create(ChuckNorrisAPI.class)
                .findAll().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                callback.onComplete();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                callback.onError(t.getMessage());
                callback.onComplete();
            }
        });
    }
}
