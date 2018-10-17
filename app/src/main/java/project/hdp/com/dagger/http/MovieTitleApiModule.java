package project.hdp.com.dagger.http;


import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieTitleApiModule {
    public final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public final String API_KEY = "241f602ad6373895253b13d5cd7b8e6e";

    @Provides
    public OkHttpClient provideClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor( ( chain ) -> {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("api_key" , API_KEY).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            })
            .build();
    }

    @Provides
    public Retrofit provideRetrofit(String url , OkHttpClient client){
        return new Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    }

    @Provides
    public MoviesApiService provideApiService(){
        return provideRetrofit( BASE_URL , provideClient()).create(MoviesApiService.class);
    }
}
