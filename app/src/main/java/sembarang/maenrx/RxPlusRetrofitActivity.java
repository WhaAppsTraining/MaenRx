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
public class RxPlusRetrofitActivity extends BaseActivity {

    private static final String GITHUB_USERNAME = "hendrawd";
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = RetrofitFactory.newInstance();
        retrofitService = retrofit.create(RetrofitService.class);
        // standardWay();
        rxWay();
    }

    private void standardWay() {
        Call<List<Repo>> call = retrofitService.getRepoList(GITHUB_USERNAME);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repo>> call,
                                   @NonNull Response<List<Repo>> response) {
                List<Repo> repoList = response.body();
                if (repoList != null) {
                    for (Repo repo : repoList) {
                        textView.append(String.format("\n%s", repo.name));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Repo>> call, @NonNull Throwable t) {
                textView.append("\nonFailure");
            }
        });
    }

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
