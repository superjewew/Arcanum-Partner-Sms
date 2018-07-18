package com.mahavira.partnersms.storemanagement.presentation.feature.lending;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.inventory.domain.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.usecase.GetProductsUseCase;
import com.mahavira.partnersms.inventory.domain.usecase.UpdateMultipleProductUseCase;
import com.mahavira.partnersms.storemanagement.domain.entitiy.ProductSelected;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 18/07/18.
 *
 */

public class LendingViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    private final MutableLiveData<Resource<List<ProductSelected>>> mProductData = new MutableLiveData<>();

    private final MutableLiveData<Boolean> mUpdateSuccessData = new MutableLiveData<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private GetProductsUseCase mGetProductsUseCase;

    private UpdateMultipleProductUseCase mUpdateMultipleProductUseCase;

    @Inject
    LendingViewModel(GetProductsUseCase getProductsUseCase, UpdateMultipleProductUseCase updateMultipleProductUseCase) {
        mGetProductsUseCase = getProductsUseCase;
        mUpdateMultipleProductUseCase = updateMultipleProductUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    public MutableLiveData<Resource<List<ProductSelected>>> getProductData() {
        return mProductData;
    }

    void attemptGetProducts() {
        mDisposable.add(mGetProductsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> onSubscribe())
                .subscribe(this::onSuccess, this::onFailed));
    }

    void attemptLentProducts(List<ProductSelected> products) {
        List<Boardgame> data = convertSelectedToProduct(products);
        try {
            mDisposable.add(mUpdateMultipleProductUseCase.execute(data)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(__ -> onSubscribe())
                    .subscribe(this::onUpdateSuccess, this::onFailed));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onUpdateSuccess() {
        mShowLoading.set(false);
        mUpdateSuccessData.setValue(true);
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mProductData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

    private void onSuccess(List<Boardgame> boardgames) {
        mShowLoading.set(false);
        mProductData.setValue(Resource.success(convertToProductSelected(boardgames)));
    }

    private void onSubscribe() {
        mShowLoading.set(true);
    }

    private List<ProductSelected> convertToProductSelected(List<Boardgame> data) {
        List<ProductSelected> res = new ArrayList<>();
        for (Boardgame product : data) {
            ProductSelected ps = new ProductSelected(product, false);
            res.add(ps);
        }
        return res;
    }

    private List<Boardgame> convertSelectedToProduct(List<ProductSelected> data) {
        List<Boardgame> res = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ProductSelected selected = data.get(i);
            if(selected.isSelected()) {
                Boardgame product = selected.getProduct();
                product.reduceQuantity();
                res.add(product);
            }
        }
        return res;
    }
}
