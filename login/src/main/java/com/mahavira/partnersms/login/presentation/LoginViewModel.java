package com.mahavira.partnersms.login.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;

import javax.inject.Inject;

import com.google.firebase.auth.AuthResult;
import com.mahavira.partnersms.base.core.Resource;
import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.login.domain.entity.AuthParam;
import com.mahavira.partnersms.login.domain.usecase.LoginUseCase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 13/07/18.
 */

public class LoginViewModel extends BaseViewModel {

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private final MutableLiveData<Resource<AuthResult>> mResponse = new MutableLiveData<>();

    private LoginUseCase mLoginUseCase;

    @Inject
    LoginViewModel(LoginUseCase loginUseCase) {
        mLoginUseCase = loginUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    MutableLiveData<Resource<AuthResult>> getLoginResponse() {
        return mResponse;
    }

    public void attemptLogin(AuthParam param) {
        AuthParam loginParam = new AuthParam(param.getEmail(), param.getPassword());
        mDisposable.add(mLoginUseCase.execute(loginParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> mResponse.setValue(Resource.loading(null)))
                .subscribe(result -> mResponse.setValue(Resource.success(result)),
                        throwable -> mResponse.setValue(Resource.error(null, throwable.getLocalizedMessage(), null))));
    }


}
