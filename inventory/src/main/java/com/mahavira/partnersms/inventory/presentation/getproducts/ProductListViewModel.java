package com.mahavira.partnersms.inventory.presentation.getproducts;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.base.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.usecase.GetProductsUseCase;
import com.mahavira.partnersms.inventory.domain.usecase.UpdateProductUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 18/07/18.
 *
 */

public class ProductListViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    private final MutableLiveData<Resource<List<Boardgame>>> mProductListData = new MutableLiveData<>();

    private final MutableLiveData<Resource<Boolean>> mUpdateProductData = new MutableLiveData<>();

    private final MutableLiveData<Boardgame> mProductQuantityChanged = new MutableLiveData<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private GetProductsUseCase mGetProductsUseCase;

    private UpdateProductUseCase mUpdateProductUseCase;

    @Inject
    ProductListViewModel(GetProductsUseCase getProductsUseCase, UpdateProductUseCase updateProductUseCase) {
        mGetProductsUseCase = getProductsUseCase;
        mUpdateProductUseCase = updateProductUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    MutableLiveData<Resource<List<Boardgame>>> getProductListData() {
        return mProductListData;
    }

    MutableLiveData<Boardgame> getProductQuantityChanged() {
        return mProductQuantityChanged;
    }

    void attemptGetProducts() {
        mDisposable.add(mGetProductsUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(__ -> onSubscribe())
        .subscribe(this::onSuccess, this::onFailed));
    }

    private void onSubscribe() {
        mShowLoading.set(true);
    }

    private void onSuccess(List<Boardgame> boardgames) {
        mShowLoading.set(false);
        mProductListData.setValue(Resource.success(boardgames));
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mProductListData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

    void updateProduct(Boardgame product) {
        try {
            mDisposable.add(mUpdateProductUseCase.execute(product)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(__ -> onSubscribe())
                    .subscribe(this::onUpdateSuccess, this::onUpdateFailed));
        } catch (Exception e) {
            mProductListData.setValue(Resource.error(null, e.getLocalizedMessage(), null));
            e.printStackTrace();
        }
    }

    private void onUpdateFailed(Throwable throwable) {
        mShowLoading.set(false);
        mUpdateProductData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

    private void onUpdateSuccess() {
        mShowLoading.set(false);
    }
}
