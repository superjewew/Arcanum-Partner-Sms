package com.mahavira.partnersms.login.data;

import android.support.annotation.NonNull;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mahavira.partnersms.base.core.RxHandler;
import com.mahavira.partnersms.login.domain.repo.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;

/**
 * Created by norman on 13/07/18.
 */

public class LoginRepoImpl implements LoginRepository {

    FirebaseAuth mAuthInstance;

    @Inject
    public LoginRepoImpl(FirebaseAuth auth) {
        mAuthInstance = auth;
    }

    @Override
    public Single<AuthResult> login(final String email, final String password) {
        return signInWithEmailAndPassword(mAuthInstance, email, password).toSingle();
    }

    @NonNull
    private Maybe<AuthResult> signInWithEmailAndPassword(@NonNull final FirebaseAuth firebaseAuth,
                                                               @NonNull final String email,
                                                               @NonNull final String password) {
        return Maybe.create(new MaybeOnSubscribe<AuthResult>() {
            @Override
            public void subscribe(MaybeEmitter<AuthResult> emitter) throws Exception {
                RxHandler.assignOnTask(emitter, firebaseAuth.signInWithEmailAndPassword(email, password));
            }
        });
    }
}
