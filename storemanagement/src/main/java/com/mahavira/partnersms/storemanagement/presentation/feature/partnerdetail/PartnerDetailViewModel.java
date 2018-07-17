package com.mahavira.partnersms.storemanagement.presentation.feature.partnerdetail;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.domain.usecase.DeletePartnerUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 17/07/18.
 *
 */

public class PartnerDetailViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    private final MutableLiveData<Resource<Boolean>> mDeletePartnerResult = new MutableLiveData<>();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private DeletePartnerUseCase mDeletePartnerUseCase;

    @Inject
    PartnerDetailViewModel(DeletePartnerUseCase deletePartnerUseCase) {
        mDeletePartnerUseCase = deletePartnerUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    public MutableLiveData<Resource<Boolean>> getDeletePartnerResult() {
        return mDeletePartnerResult;
    }

    void attemptDelete(Partner partner) {
        mDisposable.add(mDeletePartnerUseCase.execute(partner)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(__ -> onSubscribe())
        .subscribe(this::onSuccess, this::onFailed));
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mDeletePartnerResult.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
    }

    private void onSuccess() {
        mShowLoading.set(false);
        mDeletePartnerResult.setValue(Resource.success(true));
    }

    private void onSubscribe() {
        mShowLoading.set(true);
    }
}
