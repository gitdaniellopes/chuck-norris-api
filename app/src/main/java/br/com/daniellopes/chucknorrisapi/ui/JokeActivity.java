package br.com.daniellopes.chucknorrisapi.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import br.com.daniellopes.chucknorrisapi.R;
import br.com.daniellopes.chucknorrisapi.datasource.JokeDataSource;
import br.com.daniellopes.chucknorrisapi.model.Joke;
import br.com.daniellopes.chucknorrisapi.presenter.JokePresenter;

public class JokeActivity extends AppCompatActivity {

    public static final String CATEGORY_KEY = "category_key";
    private String category;
    private ProgressDialog progress;
    private FloatingActionButton fab;

    private TextView txtJoke;
    private ImageView imgJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bind();

        getCategorySelected();
        configureActionBar();

        JokeDataSource datasource = new JokeDataSource();
        JokePresenter presenter = new JokePresenter(this, datasource);

        presenter.findJokeBy(category);
        buttonClickFloat(presenter);
    }

    private void buttonClickFloat(JokePresenter presenter) {
        fab.setOnClickListener(view -> {
            presenter.findJokeBy(category);
        });
    }

    private void bind() {
        txtJoke = findViewById(R.id.text_view_joke);
        imgJoke = findViewById(R.id.image_view_joke);
        fab = findViewById(R.id.fab);
    }

    private void getCategorySelected() {
        if (getIntent().getExtras() != null) {
            category = getIntent().getExtras().getString(CATEGORY_KEY);
            assert category != null;
        }
    }

    public void showJoke(Joke joke) {
        txtJoke.setText(joke.getValue());
        Picasso.get().load(joke.getIconUrl()).into(imgJoke);
    }

    private void configureActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(category);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void showFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showProgressBar() {
        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setMessage(getString(R.string.loading));
            progress.setIndeterminate(true);
            progress.setCancelable(true);
        }
        progress.show();
    }

    public void hideProgressBar() {
        if (progress != null) {
            progress.hide();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }

}