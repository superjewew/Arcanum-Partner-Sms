package di;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import core.ViewModelFactory;
import dagger.Binds;
import dagger.Module;

/**
 * The dagger dependency declaration(s) for base module
 *
 */

@Module
public abstract class BaseModule {

    @Singleton
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
