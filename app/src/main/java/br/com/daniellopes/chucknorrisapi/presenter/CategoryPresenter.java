package br.com.daniellopes.chucknorrisapi.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.daniellopes.chucknorrisapi.datasource.CategoryDataSource;
import br.com.daniellopes.chucknorrisapi.interfaces.ListCategoriesCallback;
import br.com.daniellopes.chucknorrisapi.model.CategoryItem;
import br.com.daniellopes.chucknorrisapi.ui.MainActivity;
import br.com.daniellopes.chucknorrisapi.util.Colors;

public class CategoryPresenter implements ListCategoriesCallback {

    private final MainActivity view;
    private final CategoryDataSource dataSource;

    public CategoryPresenter(MainActivity view, CategoryDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void requestAll() {
        this.view.showProgressDialog();
        this.dataSource.findAll(this);
    }

    @Override
    public void onSuccess(List<String> response) {
        List<CategoryItem> categoryItems = new ArrayList<>();
        for (String val : response) {
            categoryItems.add(new CategoryItem(val, Colors.randomColor()));
        }
        view.showCategories(categoryItems);
    }

    @Override
    public void onError(String message) {
        this.view.showFailure(message);
    }

    @Override
    public void onComplete() {
        this.view.hideProgressDialog();
    }
}
