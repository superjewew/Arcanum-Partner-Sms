package com.mahavira.partnersms.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partnersms.login.LoginRouter;
import com.mahavira.partnersms.login.presentation.LoginActivity;

/**
 * Created by norman on 13/07/18.
 *
 */

public class LoginRouterImpl implements LoginRouter {
    @Override
    public void goToLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
