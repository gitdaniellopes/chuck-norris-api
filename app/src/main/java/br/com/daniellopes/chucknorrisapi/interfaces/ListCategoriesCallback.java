package br.com.daniellopes.chucknorrisapi.interfaces;

import java.util.List;

public interface ListCategoriesCallback {

    void onSuccess(List<String> response);

    void onError(String message);

    void onComplete();
}
