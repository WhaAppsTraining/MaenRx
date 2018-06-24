package sembarang.maenrx;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

// TODO 7 buat kelas ini supaya implement Observer<String>
public class MainActivity extends BaseActivity implements Observer<String> {

    // TODO 9 buat contoh dasar penggunaan Rx
    private void basicExample() {
        // object tidak harus string bisa apapun
        Observable.just("one", "two", "three", "four", "five")
                // dijalankan di sebuah background thread baru
                // masih ada banyak yang bisa diexplore dari kelas Schedulers
                .subscribeOn(Schedulers.newThread())
                // update di main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    // TODO 10 buat contoh dasar penggunaan Rx dengan delay
    private void basicExampleWithDelayJava7() {
        Observable.just("one", "two", "three", "four", "five")
                .zipWith(
                        Observable.interval(1000, TimeUnit.MILLISECONDS),
                        new BiFunction<String, Long, String>() {
                            @Override
                            public String apply(String s, Long aLong) throws Exception {
                                return s;
                            }
                        }
                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    // TODO 12 buat contoh dasar penggunaan Rx dengan delay di java 8
    private void basicExampleWithDelayJava8() {
        Observable.just("one", "two", "three", "four", "five")
                .zipWith(Observable.interval(1000, TimeUnit.MILLISECONDS), (s, aLong) -> s)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
        // TODO 13 review OtherJavaExamples.java
    }

    // TODO 8 override tiap method Observer<String>
    @Override
    public void onSubscribe(Disposable d) {
        textView.append("\nonSubscribe");
        Timber.d("\nonSubscribe");
        // bandingkan dengan cara biasa
        // Log.d(MainActivity.class.getSimpleName(), "\nonSubscribe");
    }

    @Override
    public void onNext(String s) {
        textView.append(String.format("\n%s", s));
        Timber.d("\n%s", s);
    }

    @Override
    public void onError(Throwable e) {
        textView.append(String.format("\nonError %s", e.getMessage()));
        Timber.e(e);
        // atau
        // Timber.e(e, "Ini error");
        // Timber.e("Ini error");
        // bandingkan dengan cara biasa
        // Log.e(MainActivity.class.getSimpleName(), e.getMessage());
        // Log.e(MainActivity.class.getSimpleName(), "Error Coy!", e);
    }

    @Override
    public void onComplete() {
        textView.append("\nonComplete");
        Timber.d("\nonComplete");
    }
}
