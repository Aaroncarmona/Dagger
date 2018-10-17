package project.hdp.com.dagger.ui.movies;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import project.hdp.com.dagger.http.apimodel.Result;

public class MoviesModel implements MoviesMVP.Model {

    private Repository repository;

    public MoviesModel( Repository repository ){
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {
        return Observable.zip(
            repository.getResultData(),
            repository.getCountryData(), ( result, country) ->
                new ViewModel( result.getTitle() , country , result.getBackdropPath() )
        );
    }


}
