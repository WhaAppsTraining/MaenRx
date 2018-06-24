package sembarang.maenrx.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sembarang.maenrx.network.model.Repo;

/**
 * Created by hendrawd on 15/10/16.
 */
// TODO 16 buat retrofit service
public interface RetrofitService {

    String BASE_URL = "https://api.github.com/";

    // method dari service cara biasa
    @GET("users/{user}/repos")
    Call<List<Repo>> getRepoList(@Path("user") String user);

    // method dari service memakai Observable
    @GET("users/{user}/repos")
    Observable<List<Repo>> getObservableRepoList(@Path("user") String user);
}
