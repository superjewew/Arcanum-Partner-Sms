package com.mahavira.partnersms.inventory.presentation.getproducts;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.inventory.domain.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.usecase.GetProductsUseCase;

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

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private GetProductsUseCase mGetProductsUseCase;

    @Inject
    ProductListViewModel(GetProductsUseCase getProductsUseCase) {
        mGetProductsUseCase = getProductsUseCase;
    }

    MutableLiveData<Resource<List<Boardgame>>> getProductListData() {
        return mProductListData;
    }

    void attemptGetProducts() {
        mDisposable.add(mGetProductsUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(__ -> onSubscribe())
        .subscribe(this::onSuccess, this::onFailed));
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mProductListData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

    private void onSuccess(List<Boardgame> boardgames) {
        mShowLoading.set(false);
        mProductListData.setValue(Resource.success(boardgames));
    }

    private void onSubscribe() {
        mShowLoading.set(true);
    }
}
