package com.mahavira.partnersms.inventory.presentation.addproduct;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.inventory.BR;
import com.mahavira.partnersms.inventory.R;
import com.mahavira.partnersms.inventory.databinding.ActivityAddProductBinding;
import com.mahavira.partnersms.inventory.domain.entity.Boardgame;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends BaseActivity<ActivityAddProductBinding, AddProductViewModel> {

    Boardgame mProduct = new Boardgame();

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewModel().getAddProductResult().observe(this, addResult -> {
            if(addResult != null) {
                switch (addResult.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Add product success", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case ERROR:
                        Toast.makeText(this, "Add product failed, " + addResult.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().getAddProductClickedEvent().observe(this, __ -> addNewField());

        getDataBinding().setProduct(mProduct);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_product_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add_product) {
            List<String> components = createComponentList();
            mProduct.setComponents(components);
            getViewModel().attemptAddProduct(mProduct);
        }
        return super.onOptionsItemSelected(item);
    }

    private List<String> createComponentList() {
        List<String> result = new ArrayList<>();
        LinearLayout layout = getDataBinding().componentListLayout;
        int count = layout.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = layout.getChildAt(i);
            if(v instanceof EditText) {
                EditText et = (EditText) v;
                result.add(et.getText().toString());
            }
        }
        return result;
    }

    private void addNewField() {
        LinearLayout layout = getDataBinding().componentListLayout;
        EditText et = new EditText(this);
        layout.addView(et);
    }
}
