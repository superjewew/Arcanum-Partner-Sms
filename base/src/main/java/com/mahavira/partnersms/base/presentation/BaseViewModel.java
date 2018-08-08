package com.mahavira.partnersms.base.presentation;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected final ObservableBoolean mShowLoading = new ObservableBoolean();

    protected final CompositeDisposable mDisposable = new CompositeDisposable();

}
