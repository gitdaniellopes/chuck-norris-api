package br.com.daniellopes.chucknorrisapi.presenter;

import br.com.daniellopes.chucknorrisapi.datasource.JokeDataSource;
import br.com.daniellopes.chucknorrisapi.interfaces.JokeCallback;
import br.com.daniellopes.chucknorrisapi.model.Joke;
import br.com.daniellopes.chucknorrisapi.ui.JokeActivity;

public class JokePresenter implements JokeCallback {

    private JokeActivity view;
    private JokeDataSource dataSource;

    public JokePresenter(JokeActivity view, JokeDataSource datasource) {
        this.view = view;
        this.dataSource = datasource;
    }

    public void findJokeBy(String category) {
        view.showProgressBar();
        dataSource.findByJoke(this, category);
    }

    @Override
    public void onSuccess(Joke response) {
        view.showJoke(response);
    }

    @Override
    public void onError(String message) {
        view.showFailure(message);
    }

    @Override
    public void onComplete() {
        view.hideProgressBar();
    }

}
