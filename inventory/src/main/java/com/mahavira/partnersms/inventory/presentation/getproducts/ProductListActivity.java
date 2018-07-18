package com.mahavira.partnersms.inventory.presentation.getproducts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.inventory.BR;
import com.mahavira.partnersms.inventory.R;
import com.mahavira.partnersms.inventory.databinding.ActivityProductListBinding;
import com.mahavira.partnersms.inventory.presentation.InventoryRouter;

import javax.inject.Inject;

public class ProductListActivity extends BaseActivity<ActivityProductListBinding, ProductListViewModel> {

    @Inject
    InventoryRouter mRouter;

    private ProductListAdapter mAdapter;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupAdapter();
        setupRecyclerView(mAdapter);

        getViewModel().getProductListData().observe(this, productList -> {
            if(productList != null) {
                switch (productList.status) {
                    case SUCCESS:
                        mAdapter.replaceData(productList.data);
                        break;
                    case ERROR:
                        Toast.makeText(this, "Failed to retrieve boardgame list, " + productList.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().getProductQuantityChanged().observe(this, boardgame -> getViewModel().updateProduct(boardgame));

    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().attemptGetProducts();
    }

    private void setupRecyclerView(ProductListAdapter adapter) {
        getDataBinding().productListRv.setLayoutManager(new LinearLayoutManager(this));
        getDataBinding().productListRv.setAdapter(adapter);
    }

    private void setupAdapter() {
        mAdapter = new ProductListAdapter(this, getViewModel());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add_product) {
            mRouter.goToAddProduct(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
