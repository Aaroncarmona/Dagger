package project.hdp.com.dagger;

import android.app.Application;
import android.util.Log;

public class VApp extends Application {

    private static final String TAG = VApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler( this::handleUncaughtException );
    }

    public void handleUncaughtException( Thread t , Throwable e ){
        String stackTrace = Log.getStackTraceString(e);
        String message = e.getMessage();

        Log.e(TAG ,
            String.format("[%s]:\n[%s]\n[%s]" , "ERROR" ,
                stackTrace , message
        ));


    }
}
