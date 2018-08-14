package com.mahavira.partnersms.loan.presentation.requestdetail;

import android.arch.lifecycle.MutableLiveData;

import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.core.SingleLiveEvent;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.base.entity.ReturnRequest;
import com.mahavira.partnersms.loan.domain.usecase.ApproveReturnRequestUseCase;
import com.mahavira.partnersms.loan.domain.usecase.RejectReturnRequestUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 08/08/18.
 */

public class ReturnRequestDetailViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<Boolean>> mApproveData = new MutableLiveData<>();

    private final MutableLiveData<Resource<Boolean>> mRejectData = new MutableLiveData<>();

    private final SingleLiveEvent<Void> mApproveClickedEvent = new SingleLiveEvent<>();

    private final SingleLiveEvent<Void> mRejectClickedEvent = new SingleLiveEvent<>();

    private final CompositeDisposable mDisposables = new CompositeDisposable();

    private ApproveReturnRequestUseCase mApproveUseCase;

    private RejectReturnRequestUseCase mRejectUseCase;

    @Inject
    ReturnRequestDetailViewModel(ApproveReturnRequestUseCase approveReturnRequestUseCase,
                                 RejectReturnRequestUseCase rejectReturnRequestUseCase) {
        mApproveUseCase = approveReturnRequestUseCase;
        mRejectUseCase = rejectReturnRequestUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposables.clear();
    }

    public MutableLiveData<Resource<Boolean>> getApproveData() {
        return mApproveData;
    }

    public MutableLiveData<Resource<Boolean>> getRejectData() {
        return mRejectData;
    }

    public SingleLiveEvent<Void> getApproveClickedEvent() {
        return mApproveClickedEvent;
    }

    public SingleLiveEvent<Void> getRejectClickedEvent() {
        return mRejectClickedEvent;
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

    void attemptReject(ReturnRequest mRequest) {
        try {
            mRejectUseCase.execute(mRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(__ -> doOnSubscribe())
                    .subscribe(this::onRejectSuccess, this::onRejectFailed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onRejectSuccess() {
        mRejectData.setValue(Resource.success(true));
    }

    private void onRejectFailed(Throwable throwable) {
        mRejectData.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
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

    public void reject() {
        mRejectClickedEvent.call();
    }
}
