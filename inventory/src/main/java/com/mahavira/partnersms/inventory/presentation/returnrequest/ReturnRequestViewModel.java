package com.mahavira.partnersms.inventory.presentation.returnrequest;

import android.arch.lifecycle.MutableLiveData;

import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.inventory.domain.entity.ReturnRequest;
import com.mahavira.partnersms.inventory.domain.usecase.GetReturnRequestsUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 07/08/18.
 */

public class ReturnRequestViewModel extends BaseViewModel {

    private MutableLiveData<Resource<List<ReturnRequest>>> mReturnRequestsData = new MutableLiveData<>();

    private MutableLiveData<ReturnRequest> mRequestClicked = new MutableLiveData<>();

    private GetReturnRequestsUseCase mGetReturnRequestUseCase;

    @Inject
    public ReturnRequestViewModel(GetReturnRequestsUseCase getReturnRequestsUseCase) {
        mGetReturnRequestUseCase = getReturnRequestsUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    public MutableLiveData<Resource<List<ReturnRequest>>> getReturnRequestsData() {
        return mReturnRequestsData;
    }

    public MutableLiveData<ReturnRequest> getRequestClicked() {
        return mRequestClicked;
    }

    void attemptGetReturnRequest() {
        mDisposable.add(mGetReturnRequestUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> onSubscribe())
                .subscribe(this::onSuccess, this::onFailed));
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mReturnRequestsData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

    private void onSuccess(List<ReturnRequest> requests) {
        mShowLoading.set(false);
        mReturnRequestsData.setValue(Resource.success(requests));
    }

    void setRequestClicked(ReturnRequest requestClicked) {
        mRequestClicked.setValue(requestClicked);
    }

    private void onSubscribe() {
        mShowLoading.set(true);
    }
}
