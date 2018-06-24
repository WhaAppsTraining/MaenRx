package sembarang.maenrx;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sembarang.maenrx.network.RetrofitFactory;
import sembarang.maenrx.network.RetrofitService;
import sembarang.maenrx.network.model.Repo;

/**
 * @author hendrawd on 24/06/18
 */
public class ContohCallBackHellActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = RetrofitFactory.newInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<Repo>> call = retrofitService.getRepoList("hendrawd");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repo>> call,
                                   @NonNull Response<List<Repo>> response) {
                Call<List<Repo>> callAgain = retrofitService.getRepoList("hendrawd");
                callAgain.enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Repo>> call,
                                           @NonNull Response<List<Repo>> response) {
                        Call<List<Repo>> callAgain = retrofitService.getRepoList("hendrawd");
                        callAgain.enqueue(new Callback<List<Repo>>() {
                            @Override
                            public void onResponse(@NonNull Call<List<Repo>> call,
                                                   @NonNull Response<List<Repo>> response) {
                                Call<List<Repo>> callAgain = retrofitService.getRepoList("hendrawd");
                                callAgain.enqueue(new Callback<List<Repo>>() {
                                    @Override
                                    public void onResponse(@NonNull Call<List<Repo>> call,
                                                           @NonNull Response<List<Repo>> response) {
                                        Call<List<Repo>> callAgain = retrofitService.getRepoList("hendrawd");
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<List<Repo>> call, @NonNull Throwable t) {
                                        textView.append("\nonFailure");
                                    }
                                });
                            }

                            @Override
                            public void onFailure(@NonNull Call<List<Repo>> call, @NonNull Throwable t) {
                                textView.append("\nonFailure");
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Repo>> call, @NonNull Throwable t) {
                        textView.append("\nonFailure");
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Repo>> call, @NonNull Throwable t) {
                textView.append("\nonFailure");
            }
        });
    }
}
