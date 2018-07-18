package com.mahavira.partnersms.inventory.presentation.addproduct;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.inventory.BR;
import com.mahavira.partnersms.inventory.R;
import com.mahavira.partnersms.inventory.databinding.ActivityAddProductBinding;
import com.mahavira.partnersms.inventory.domain.entity.Boardgame;

public class AddProductActivity extends BaseActivity<ActivityAddProductBinding, AddProductViewModel> {

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
            Boardgame product = new Boardgame();
            product.setName("Test Product");
            product.setQuantity(3);
            getViewModel().attemptAddProduct(product);
        }
        return super.onOptionsItemSelected(item);
    }
}
