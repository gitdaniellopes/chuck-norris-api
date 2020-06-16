package br.com.daniellopes.chucknorrisapi.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwray.groupie.GroupAdapter;

import java.util.List;

import br.com.daniellopes.chucknorrisapi.R;
import br.com.daniellopes.chucknorrisapi.datasource.CategoryDataSource;
import br.com.daniellopes.chucknorrisapi.model.CategoryItem;
import br.com.daniellopes.chucknorrisapi.presenter.CategoryPresenter;

import static br.com.daniellopes.chucknorrisapi.ui.JokeActivity.CATEGORY_KEY;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GroupAdapter groupAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();

        groupAdapter = new GroupAdapter();
        groupAdapter.setOnItemClickListener((item, view) -> {
            final Intent intent = new Intent(this, JokeActivity.class);
            intent.putExtra(CATEGORY_KEY, ((CategoryItem) item).getCategoryName());
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groupAdapter);

        CategoryDataSource dataSource = new CategoryDataSource();
        CategoryPresenter categoryPresenter = new CategoryPresenter(this, dataSource);

        categoryPresenter.requestAll();
    }

    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
        }
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }

    public void showFailure(String message) {
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }

    public void showCategories(List<CategoryItem> categoryItems) {
        groupAdapter.addAll(categoryItems);
        groupAdapter.notifyDataSetChanged();
    }

    private void bind() {
        recyclerView = findViewById(R.id.recycle_view_category);
    }

}