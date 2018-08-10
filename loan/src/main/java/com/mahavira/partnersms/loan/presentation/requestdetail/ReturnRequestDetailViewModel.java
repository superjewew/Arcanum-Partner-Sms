package com.mahavira.partnersms.loan.presentation.requestdetail;

import android.arch.lifecycle.MutableLiveData;

import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.core.SingleLiveEvent;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.base.entity.ReturnRequest;
import com.mahavira.partnersms.loan.domain.usecase.ApproveReturnRequestUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 08/08/18.
 */

public class ReturnRequestDetailViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<Boolean>> mApproveData = new MutableLiveData<>();

    private final SingleLiveEvent<Void> mApproveClickedEvent = new SingleLiveEvent<>();

    private final CompositeDisposable mDisposables = new CompositeDisposable();

    private ApproveReturnRequestUseCase mApproveUseCase;

    @Inject
    ReturnRequestDetailViewModel(ApproveReturnRequestUseCase approveReturnRequestUseCase) {
        mApproveUseCase = approveReturnRequestUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposables.clear();
    }

    public MutableLiveData<Resource<Boolean>> getApproveData() {
        return mApproveData;
    }

    public SingleLiveEvent<Void> getApproveClickedEvent() {
        return mApproveClickedEvent;
    }

    void attemptApprove(ReturnRequest request) {
        try {
            mApproveUseCase.execute(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(__ -> doOnSubscribe())
                    .subscribe(this::onApproveSuccess, this::onApproveFailed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onApproveFailed(Throwable throwable) {
        hideLoading();
        mApproveData.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
    }

    private void onApproveSuccess() {
        hideLoading();
        mApproveData.setValue(Resource.success(true));
    }

    public void approve() {
        mApproveClickedEvent.call();
    }

}
