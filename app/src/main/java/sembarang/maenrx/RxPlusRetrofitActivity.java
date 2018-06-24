package sembarang.maenrx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sembarang.maenrx.network.RetrofitFactory;
import sembarang.maenrx.network.RetrofitService;
import sembarang.maenrx.network.model.Repo;
import timber.log.Timber;

/**
 * @author hendrawd on 24/06/18
 */
// TODO 20 tambah Activity untuk mengetest retrofit call dengan observable
public class RxPlusRetrofitActivity extends BaseActivity {

    private static final String GITHUB_USERNAME = "rachmanforniandi";
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = RetrofitFactory.newInstance();
        retrofitService = retrofit.create(RetrofitService.class);
        // standardWay();
        rxWay();
    }

    // TODO 21 buat method request retrofit dengan cara biasa,
    // panggil method ini dari onCreate dan pahami bagaimana jalannya aplikasi
    private void standardWay() {
        // mendapatkan object call dari retrofit service
        // List<Repo> adalah tipe yang otomatis didapatkan
        // (konversi otomatis json ke java object oleh GSON)
        Call<List<Repo>> call = retrofitService.getRepoList(GITHUB_USERNAME);
        // menjalankan request di background
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repo>> call,
                                   @NonNull Response<List<Repo>> response) {
                // response sukses
                List<Repo> repoList = response.body();
                if (repoList != null) {
                    for (Repo repo : repoList) {
                        // menambahkan semua nama repo ke TextView
                        textView.append(String.format("\n%s", repo.name));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Repo>> call, @NonNull Throwable t) {
                // response gagal
                textView.append("\nonFailure");
            }
        });
    }

    // TODO 22 buat method request retrofit dengan cara Reactive,
    // panggil method ini dari onCreate dan pahami bagaimana jalannya aplikasi
    private void rxWay() {
        retrofitService.getObservableRepoList(GITHUB_USERNAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        textView.append("\nonSubscribe");
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        for (Repo repo : repos) {
                            textView.append(String.format("\n%s", repo.name));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        textView.append("\nonError");
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {
                        textView.append("\nonComplete");
                    }
                });
    }
}
