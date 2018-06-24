package sembarang.maenrx.network;

import com.google.gson.GsonBuilder;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sembarang.maenrx.BuildConfig;

/**
 * Created by hendrawd on 07/02/17.
 */
// TODO 17 buat RetrofitFactory
public class RetrofitFactory {

    public static Retrofit newInstance() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();
        // ini adalah adapter yang digunakan oleh retrofit untuk membuat Observable
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                // add rxadapter ke retrofit
                .addCallAdapterFactory(rxAdapter)
                .build();
    }
}
