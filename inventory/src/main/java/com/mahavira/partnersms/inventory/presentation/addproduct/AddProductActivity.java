package com.mahavira.partnersms.inventory.presentation.addproduct;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.mahavira.partnersms.base.entity.Boardgame;
import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.base.presentation.ExtraInjectable;
import com.mahavira.partnersms.inventory.BR;
import com.mahavira.partnersms.inventory.R;
import com.mahavira.partnersms.inventory.databinding.ActivityAddProductBinding;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcels;

public class AddProductActivity extends BaseActivity<ActivityAddProductBinding, AddProductViewModel> implements
        ExtraInjectable {

    public static final String PRODUCT_EXTRA = "product";

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
            if (addResult != null) {
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

        getViewModel().getRemoveProductResult().observe(this, removeResult -> {
            assert removeResult != null;
            switch (removeResult.status) {
                case SUCCESS: {
                    Toast.makeText(this, "Delete product successful", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }
                case ERROR: {
                    Toast.makeText(this, "Delete product failed, " + removeResult.message, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });

        getViewModel().getAddProductClickedEvent().observe(this, __ -> addNewField());

        getDataBinding().setProduct(mProduct);

        invalidateOptionsMenu();
    }

    @Override
    public void injectExtras(@NonNull final Bundle extras) {
        if (extras.containsKey(PRODUCT_EXTRA)) {
            mProduct = Parcels.unwrap(extras.getParcelable(PRODUCT_EXTRA));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_product_menu, menu);
        if("".equals(mProduct.getName())) {
            menu.findItem(R.id.action_delete_product).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_product) {
            List<String> components = createComponentList();
            mProduct.setComponents(components);
            getViewModel().attemptAddProduct(mProduct);
        } else if (id == R.id.action_delete_product) {
            getViewModel().attemptDeleteProduct(mProduct);
        }
        return super.onOptionsItemSelected(item);
    }

    private List<String> createComponentList() {
        List<String> result = new ArrayList<>();
        LinearLayout layout = getDataBinding().componentListLayout;
        int count = layout.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = layout.getChildAt(i);
            if (v instanceof EditText) {
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
