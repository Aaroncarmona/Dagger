package project.hdp.com.dagger.ui.movies;

import io.reactivex.Observable;
import project.hdp.com.dagger.http.apimodel.Result;

public interface Repository {

    Observable<Result> getResultFromNetwork();
    Observable<Result> getResultFromCache();
    Observable<Result> getResultData();

    Observable<String> getCountryFromNetwork();
    Observable<String> getCountryFromCache();
    Observable<String> getCountryData();

}
