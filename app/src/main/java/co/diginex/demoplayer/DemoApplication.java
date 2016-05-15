package co.diginex.demoplayer;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import okhttp3.OkHttpClient;

/**
 * Created by Sean on 12/05/2016.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Create the OkHttpClient which will actually perform the network calls
        //So far the most efficient networking library I have found, downloads the images faster and more reliably than the default Client
        OkHttpClient client = new OkHttpClient();

        //Creating the config for Fresco, using the OkHttpClient
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(this,client)
                //Downsampling makes the UI more snappy
                .setDownsampleEnabled(true)
                .build();

        //Initialise the library which will download images in the background.
        Fresco.initialize(this,config);
    }
}
