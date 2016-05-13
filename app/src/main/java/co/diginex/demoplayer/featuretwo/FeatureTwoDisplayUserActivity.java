package co.diginex.demoplayer.featuretwo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.diginex.demoplayer.AppConstants;
import co.diginex.demoplayer.R;

/**
 * Created by Sean on 13/05/2016.
 */
public class FeatureTwoDisplayUserActivity extends AppCompatActivity {

    @BindView(R.id.activity_f_two_name_display)
    TextView nameDisplayText;

    @OnClick(R.id.activity_f_two_exit)
    void exitApplication() {
        setResult(AppConstants.RESULT_CLOSE_APP);
        finish();
    }

    @OnClick(R.id.activity_f_two_clear)
    void clearUser() {
        getPrefs().edit()
                .putBoolean(AppConstants.ALREADY_VISITED, false)
                .putString(AppConstants.USERNAME, "")
                .commit();
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_two_user_display);
        setTitle(R.string.feature_two_display);
        ButterKnife.bind(this);

        if (!getPrefs().getBoolean(AppConstants.ALREADY_VISITED, false)) {
            nameDisplayText.setText(getString(R.string.welcome, getPrefs().getString(AppConstants.USERNAME, "")));
            getPrefs().edit().putBoolean(AppConstants.ALREADY_VISITED, true).commit();
        } else {
            nameDisplayText.setText(getString(R.string.welcome_back, getPrefs().getString(AppConstants.USERNAME, "")));
        }
    }

    private SharedPreferences getPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }
}
