package project.hdp.com.dagger.root;

import javax.inject.Singleton;

import dagger.Component;
import project.hdp.com.dagger.http.MovieExtraInfoApiModule;
import project.hdp.com.dagger.http.MovieTitleApiModule;
import project.hdp.com.dagger.ui.MainActivity;
import project.hdp.com.dagger.ui.movies.MoviesModule;

@Singleton
@Component(modules = {
    ApplicationModule.class,
    MovieTitleApiModule.class,
    MovieExtraInfoApiModule.class,
    MoviesModule.class
})
public interface ApplicationComponent {
    void inject(MainActivity target);
}
