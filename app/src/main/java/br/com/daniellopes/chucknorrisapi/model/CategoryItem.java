package br.com.daniellopes.chucknorrisapi.model;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import br.com.daniellopes.chucknorrisapi.R;

public class CategoryItem extends Item<ViewHolder> {

    private final String categoryName;
    private final int bgColor;

    public CategoryItem(String categoryName, int bgColor) {
        this.categoryName = categoryName;
        this.bgColor = bgColor;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        final TextView txtNameCategory = viewHolder.itemView.findViewById(R.id.text_view_name_category);
        txtNameCategory.setText(categoryName);
        viewHolder.itemView.setBackgroundColor(bgColor);
    }

    @Override
    public int getLayout() {
        return R.layout.item_category;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
