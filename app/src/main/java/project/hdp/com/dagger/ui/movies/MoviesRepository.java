package project.hdp.com.dagger.ui.movies;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import project.hdp.com.dagger.http.MoviesApiService;
import project.hdp.com.dagger.http.MoviesExtraInfoApiService;
import project.hdp.com.dagger.http.apimodel.Result;
import project.hdp.com.dagger.http.apimodel.TopMovieRated;

public class MoviesRepository implements Repository{

    private MoviesApiService moviesApiService;
    private MoviesExtraInfoApiService moviesExtraInfoApiService;

    private List<String> countries;
    private List<Result> results;
    private long lastTimestamp;

    private static final long CACHE_LIFETIME = 20 * 1000 ; //CACHE QUE DURARA 20 MILISEGUNDOS


    public MoviesRepository(MoviesApiService moviesApiService , MoviesExtraInfoApiService moviesExtraInfoApiService){
        this.moviesApiService = moviesApiService;
        this.moviesExtraInfoApiService = moviesExtraInfoApiService;

        this.lastTimestamp = System.currentTimeMillis();
        this.countries = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    private boolean isUpdate(){
        //
        return ( System.currentTimeMillis() - lastTimestamp ) < CACHE_LIFETIME;
    }

    @Override
    public Observable<Result> getResultFromNetwork() {
        Observable<TopMovieRated> topMovieRatedObservable =
            moviesApiService.getTopMovieRated("en-US" , 1);

        return topMovieRatedObservable
            .concatMap( (TopMovieRated topMovieRated) ->
                Observable.fromIterable(topMovieRated.getResults())
            ).doOnNext( result ->
                results.add(result)
            );
    }

    @Override
    public Observable<Result> getResultFromCache() {
        if ( isUpdate() ) return Observable.fromIterable(results);
        else {
            lastTimestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultFromCache().switchIfEmpty(getResultFromNetwork());
    }

    @Override
    public Observable<String> getCountryFromNetwork() {
        return getResultFromNetwork().concatMap( result ->
            moviesExtraInfoApiService.getExtraInfoMovie(result.getTitle())
        ).concatMap( opendb -> {
            if ( opendb == null || opendb.getCountry() == null )
                return Observable.just("Desconocido");
            else
                return Observable.just(opendb.getCountry());
        }).doOnNext( string -> {
            countries.add(string);
        });
    }

    @Override
    public Observable<String> getCountryFromCache() {
        if ( isUpdate() ) return Observable.fromIterable(countries);
        else {
            lastTimestamp = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountryFromCache().switchIfEmpty(getCountryFromNetwork());
    }
}
