package com.mahavira.partnersms.inventory.presentation.addproduct;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.core.SingleLiveEvent;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.base.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.usecase.AddProductUseCase;

import com.mahavira.partnersms.inventory.domain.usecase.DeleteProductUseCase;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 17/07/18.
 *
 */

public class AddProductViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    private final MutableLiveData<Resource<Boolean>> mAddProductResult = new MutableLiveData<>();

    private final  MutableLiveData<Resource<Boolean>> mRemoveProductResult = new MutableLiveData<>();

    private final SingleLiveEvent<Void> mAddProductClickedEvent = new SingleLiveEvent<>();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private AddProductUseCase mAddProductUseCase;

    private DeleteProductUseCase mDeleteProductUseCase;

    @Inject
    AddProductViewModel(AddProductUseCase addProductUseCase, DeleteProductUseCase deleteProductUseCase) {
        mAddProductUseCase = addProductUseCase;
        mDeleteProductUseCase = deleteProductUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    MutableLiveData<Resource<Boolean>> getAddProductResult() {
        return mAddProductResult;
    }

    SingleLiveEvent<Void> getAddProductClickedEvent() {
        return mAddProductClickedEvent;
    }

    MutableLiveData<Resource<Boolean>> getRemoveProductResult() {
        return mRemoveProductResult;
    }

    void attemptAddProduct(Boardgame product) {
        mDisposable.add(mAddProductUseCase.execute(product)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(__ -> onSubscribe())
        .subscribe(this::onSuccess, this::onFailed));
    }

    void attemptDeleteProduct(final Boardgame product) {
        mDisposable.add(mDeleteProductUseCase.execute(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> onSubscribe())
                .doFinally(this::hideLoading)
                .subscribe(this::onDeleteSuccess, this::onDeleteFailed));
    }

    private void onDeleteFailed(final Throwable throwable) {
        mRemoveProductResult.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
    }

    private void onDeleteSuccess() {
        mRemoveProductResult.setValue(Resource.success(true));
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mAddProductResult.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
    }

    private void onSuccess() {
        mShowLoading.set(false);
        mAddProductResult.setValue(Resource.success(true));
    }

    private void onSubscribe() {
        mShowLoading.set(true);
    }

    public void addComponentField() {
        mAddProductClickedEvent.call();
    }
}
