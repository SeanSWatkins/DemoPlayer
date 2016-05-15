package co.diginex.demoplayer.data;

import android.test.AndroidTestCase;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import co.diginex.demoplayer.AppConstants;

/**
 * Created by Sean on 14/05/2016.
 */
public class JsonValidation extends AndroidTestCase {

    public void testJsonIsValid(){
        JsonParser parser = new JsonParser();

        JsonArray trackContents = new JsonArray();

        boolean parseable = true;
        try {
            trackContents = parser.parse(AppConstants.FEATURE_ONE_JSON).getAsJsonArray();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            parseable = false;
        }

        assertTrue(parseable);

        for (int i = 0; i < trackContents.size(); i++) {
            JsonObject track = trackContents.get(i).getAsJsonObject();

            assertTrue(track.has(AppConstants.TRACK));
            assertTrue(track.has(AppConstants.TRACK_DESCRIPTION));
            assertTrue(track.has(AppConstants.TRACK_IMAGE_URL));
            assertTrue(track.has(AppConstants.TRACK_TITLE));

        }

    }
}
