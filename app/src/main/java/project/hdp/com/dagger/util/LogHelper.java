package project.hdp.com.dagger.util;

import android.content.Context;
import android.util.Log;

public class LogHelper {

    public static void log(Context context , boolean notification , String tag , String tracking , String message ) {
        message(0 , tag , tracking , message );
    }

    public static void loge( Context context , boolean notification , String tag , String tracking , String message ) {
        message( 1 , tag , tracking , message);
    }


    private static void message( int type , String tag , String tracking , String message ){
        switch ( type ){
            case 0 :
                Log.d( tag , String.format("%s: %s" , tracking , message));
                break;
            case 1 :
                Log.e( tag , String.format("%s: %s" , tracking , message));
                break;
        }

    }
}
