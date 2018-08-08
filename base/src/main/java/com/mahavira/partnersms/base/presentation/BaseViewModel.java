package com.mahavira.partnersms.base.presentation;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    public final CompositeDisposable mDisposable = new CompositeDisposable();

}
