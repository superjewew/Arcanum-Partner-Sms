package com.mahavira.partnersms.storemanagement.presentation.feature.partnerlist;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.domain.usecase.GetPartnersUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 16/07/18.
 *
 */

public class PartnerListViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    private final MutableLiveData<List<Partner>> mPartnersData = new MutableLiveData<>();

    private final MutableLiveData<Partner> mPartnerClicked = new MutableLiveData<>();

    private final MutableLiveData<String> mErrorMessage = new MutableLiveData<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private GetPartnersUseCase mGetPartnersUseCase;

    @Inject
    public PartnerListViewModel(GetPartnersUseCase getPartnersUseCase) {
        mGetPartnersUseCase = getPartnersUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    MutableLiveData<Partner> getPartnerClicked() {
        return mPartnerClicked;
    }

    MutableLiveData<List<Partner>> getPartnersData() {
        return mPartnersData;
    }

    MutableLiveData<String> getErrorMessage() {
        return mErrorMessage;
    }

    void attemptGetPartners() {
        mDisposable.add(mGetPartnersUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> onSubscribe())
                .subscribe(this::onSuccess, this::onFailed));
    }

    private void onSubscribe() {
        mShowLoading.set(true);
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mErrorMessage.setValue(throwable.getLocalizedMessage());
    }

    private void onSuccess(List<Partner> partners) {
        mShowLoading.set(false);
        mPartnersData.setValue(partners);
    }

}
