package project.hdp.com.dagger.http;

import io.reactivex.Observable;
import project.hdp.com.dagger.http.apimodel.Opendb;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesExtraInfoApiService {

    @GET("/")
    Observable<Opendb> getExtraInfoMovie(
        @Query( value = "t") String title
    );
}
