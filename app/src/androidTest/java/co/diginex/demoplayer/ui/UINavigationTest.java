package co.diginex.demoplayer.ui;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.diginex.demoplayer.AppConstants;
import co.diginex.demoplayer.HomeActivity;
import co.diginex.demoplayer.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by Sean on 13/05/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UINavigationTest {

    JsonArray tracks;

    @Before
    public void setupTrackJson() {
        tracks = new JsonParser().parse(AppConstants.FEATURE_ONE_JSON).getAsJsonArray();
    }

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(
            HomeActivity.class);

    @Test
    public void testFeatureTwoSelection() {
        //Start Feature Two
        onView(withId(R.id.activity_home_feature_two_select))
                .perform(click());

        //Add name - SimfyTest to the edit test and click submit
        onView(withId(R.id.activity_f_two_edittext_name))
                .perform(typeText("SimfyTest"), closeSoftKeyboard());


        onView(withId(R.id.activity_f_two_submit))
                .perform(click());

        //Check the text to see if it has the welcome text
        onView(withId(R.id.activity_f_two_name_display))
                .check(matches(withText("Welcome SimfyTest")));

        //Click back to return to the home activity
        pressBack();

        //Click back to feature two and make sure it goes straight to the display text
        onView(withId(R.id.activity_home_feature_two_select))
                .perform(click());
        onView(withId(R.id.activity_f_two_name_display))
                .check(matches(withText("Welcome SimfyTest, thanks for returning, we hope you enjoy your stay!")));

        //Close the application
        onView(withId(R.id.activity_f_two_exit))
                .perform(click());

        //Start Activity again
        mActivityRule.launchActivity(new Intent());

        //Start Feature Two
        onView(withId(R.id.activity_home_feature_two_select))
                .perform(click());

        //Should go straight to the welcome back screen
        onView(withId(R.id.activity_f_two_name_display))
                .check(matches(withText("Welcome SimfyTest, thanks for returning, we hope you enjoy your stay!")));

        //Return to 'login'
        onView(withId(R.id.activity_f_two_clear))
                .perform(click());

        //Check the edit text is empty again
        onView(withId(R.id.activity_f_two_edittext_name))
                .check(matches(withText("")));

        //Check a retype shows the first welcome message
        onView(withId(R.id.activity_f_two_edittext_name))
                .perform(typeText("SimfyTest"), closeSoftKeyboard());
        onView(withId(R.id.activity_f_two_submit))
                .perform(click());
        onView(withId(R.id.activity_f_two_name_display))
                .check(matches(withText("Welcome SimfyTest")));
        //Clear before exiting
        onView(withId(R.id.activity_f_two_clear))
                .perform(click());

        pressBack();

    }

    @Test
    public void testFeatureOneSelection() {

        //Start Feature One
        onView(withId(R.id.activity_home_feature_one_select))
                .perform(click());

        for (int i = 0; i < tracks.size(); i++) {

            onView(withId(R.id.activity_f_one_recycler))
                    .perform(RecyclerViewActions
                            .actionOnItemAtPosition(i, RecyclerItemClickAction
                                    .clickItemImageViewWithId(R.id.fone_recycler_item_drawee)));


            onView(withId(R.id.fragment_f_one_player_description))
                    .check(matches(withText(tracks.get(i)
                            .getAsJsonObject()
                            .get(AppConstants.TRACK_DESCRIPTION)
                            .getAsString())));
            onView(withId(R.id.fragment_f_one_player_play)).perform(click());
            onView(withId(R.id.fragment_f_one_player_dismiss)).perform(click());


            if (i != tracks.size() - 1) {
                onView(withId(R.id.activity_f_one_recycler))
                        .perform(RecyclerViewActions.scrollToPosition(i + 1));
            }
        }

        pressBack();

    }


}
