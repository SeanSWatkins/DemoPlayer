package co.diginex.demoplayer.featuretwo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.diginex.demoplayer.AppConstants;
import co.diginex.demoplayer.R;

/**
 * Created by Sean on 13/05/2016.
 */
public class FeatureTwoActivity extends AppCompatActivity {

    private static final int REQUEST = 2;

    @OnClick(R.id.activity_f_two_submit)
    void submit() {
        String name = nameEditText.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            getPrefs().edit().putString(AppConstants.USERNAME, name).commit();
            nameEditText.setText("");
            gotoDisplay();
        } else {
            showSnackbarForStringId(R.string.no_user);
        }
    }

    @BindView(R.id.activity_f_two_edittext_name)
    EditText nameEditText;

    @BindView(R.id.activity_f_two_coord)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!TextUtils.isEmpty(getPrefs().getString(AppConstants.USERNAME, null))) {
            gotoDisplay();
        }

        setContentView(R.layout.activity_feature_two);
        setTitle(R.string.feature_two);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppConstants.RESULT_CLOSE_APP) {
            setResult(AppConstants.RESULT_CLOSE_APP);
            finish();
        }else if(resultCode == Activity.RESULT_CANCELED){
            finish();
        }
    }

    private SharedPreferences getPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void gotoDisplay() {
        Intent intent = new Intent(this, FeatureTwoWelcomeUserActivity.class);
        startActivityForResult(intent, REQUEST);
    }

    private void showSnackbarForStringId(@StringRes int text) {
        Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG).show();
    }
}
