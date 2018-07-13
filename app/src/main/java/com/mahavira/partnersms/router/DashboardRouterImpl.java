package com.mahavira.partnersms.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partnersms.dashboard.presentation.DashboardActivity;
import com.mahavira.partnersms.dashboard.presentation.DashboardRouter;

/**
 * Created by norman on 13/07/18.
 *
 */

public class DashboardRouterImpl implements DashboardRouter {
    @Override
    public void goToDashboard(Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        context.startActivity(intent);
    }
}
