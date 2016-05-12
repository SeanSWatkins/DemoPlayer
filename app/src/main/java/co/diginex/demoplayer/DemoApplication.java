package co.diginex.demoplayer;

import android.app.Application;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.util.HashSet;
import java.util.Set;

import okhttp3.OkHttpClient;

/**
 * Created by Sean on 12/05/2016.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Create the OkHttpClient which will actually perform the network calls
        //So far the most effecient networking library I have found
        OkHttpClient client = new OkHttpClient();

        //Creating the config for Fresco, using the OkHttpClient
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(this,client)
                .build();

        //Initialise the library which will download images in the background.
        Fresco.initialize(this,config);
    }
}
