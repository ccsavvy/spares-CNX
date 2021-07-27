package ccsavvy.christian.catfact.data;

import java.util.concurrent.TimeUnit;

import ccsavvy.christian.catfact.model.NinjaCatFacts;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private ApiService apiService;

    public ApiManager() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build();
            okhttp3.Response response = chain.proceed(request);
            return response;
        });
        OkHttpClient client = httpClient.build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("https://catfact.ninja")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        this.apiService = mRetrofit.create(ApiService.class);
    }

    public Single<NinjaCatFacts> getFact() {
        return apiService.getFact();
    }
}
