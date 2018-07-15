package com.mahavira.partnersms.storemanagement.presentation.feature.addpartner;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.domain.usecase.AddPartnerUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 14/07/18.
 *
 */

public class AddPartnerViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    private final MutableLiveData<Resource<Boolean>> mAddPartnerResult = new MutableLiveData<>();

    private final MutableLiveData<String> mErrorMessage = new MutableLiveData<>();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private AddPartnerUseCase mAddPartnerUseCase;

    @Inject
    AddPartnerViewModel(AddPartnerUseCase addPartnerUseCase) {
        mAddPartnerUseCase = addPartnerUseCase;
    }

    MutableLiveData<Resource<Boolean>> getAddPartnerResult() {
        return mAddPartnerResult;
    }

    MutableLiveData<String> getErrorMessage() {
        return mErrorMessage;
    }

    public void attemptAddPartner(Partner partner) {
        mDisposable.add(mAddPartnerUseCase.execute(partner)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> onSubscribe())
                .subscribe(this::onSuccess, this::onFailed));
    }

    private void onSubscribe() {
        mShowLoading.set(true);
    }

    private void onFailed(Throwable throwable) {
        mErrorMessage.setValue(throwable.getLocalizedMessage());
    }

    private void onSuccess() {
        mAddPartnerResult.setValue(Resource.success(true));
    }
}
