package project.hdp.com.dagger.ui.movies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import project.hdp.com.dagger.http.MoviesApiService;
import project.hdp.com.dagger.http.MoviesExtraInfoApiService;

@Module
public class MoviesModule {

    @Provides
    public MoviesMVP.Presenter provideMoviesPresenter(MoviesMVP.Model movieModel){
        return new MoviesPresenter(movieModel);
    }

    @Provides
    public MoviesMVP.Model provideMovieModel( Repository repository){
        return new MoviesModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideMovieRepository(MoviesApiService moviesApiService , MoviesExtraInfoApiService moviesExtraInfoApiService ){
        return new MoviesRepository(moviesApiService , moviesExtraInfoApiService);
    }

}
