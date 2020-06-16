package br.com.daniellopes.chucknorrisapi.interfaces;

import br.com.daniellopes.chucknorrisapi.model.Joke;

public interface JokeCallback {

    void onSuccess(Joke response);

    void onError(String message);

    void onComplete();
}
