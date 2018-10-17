package project.hdp.com.dagger.root;

import android.app.Application;
import android.util.Log;

import project.hdp.com.dagger.http.MovieExtraInfoApiModule;
import project.hdp.com.dagger.http.MovieTitleApiModule;
import project.hdp.com.dagger.ui.movies.MoviesModule;
import project.hdp.com.dagger.util.LogHelper;

public class App extends Application {
    private ApplicationComponent component;
    private final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler( this::handleUncaughtException );

        component = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .moviesModule(new MoviesModule())
            .movieTitleApiModule( new MovieTitleApiModule())
            .movieExtraInfoApiModule(new MovieExtraInfoApiModule())
            .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }

    public void handleUncaughtException( Thread t , Throwable e ){
        String stackTrace = Log.getStackTraceString(e);
        String message = e.getMessage();

        LogHelper.loge(getBaseContext(),false , TAG , "handleUncaught" ,
                String.format("%s :: %s" , stackTrace , message));
    }
}
