package project.hdp.com.dagger.http;

import io.reactivex.Observable;
import project.hdp.com.dagger.http.apimodel.TopMovieRated;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApiService {

    @GET("top_rated")
    Observable<TopMovieRated> getTopMovieRated(
        @Query( value = "language") String lenguage,
        @Query( value = "page") int page
    );

}
