package sembarang.maenrx;

import android.app.Application;

import timber.log.Timber;

/**
 * @author hendrawd on 24/06/18
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // TODO 5 Buat MainApplication dan plant Timber
        // TODO 6 Tambahkan MainApplication ini ke manifest
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
