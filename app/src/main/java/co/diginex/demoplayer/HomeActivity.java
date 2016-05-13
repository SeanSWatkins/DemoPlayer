package co.diginex.demoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import co.diginex.demoplayer.featureone.FeatureOneActivity;
import co.diginex.demoplayer.featuretwo.FeatureTwoActivity;

/**
 * Created by Sean on 13/05/2016.
 */
public class HomeActivity extends AppCompatActivity {

    private static final int REQUEST = 1;

    @OnClick(R.id.activity_home_feature_one_select)
    void selectFeatureOne() {
        Intent intent = new Intent(this, FeatureOneActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.activity_home_feature_two_select)
    void selectFeatureTwo() {
        Intent intent = new Intent(this, FeatureTwoActivity.class);
        startActivityForResult(intent, REQUEST);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.feature_select);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == AppConstants.RESULT_CLOSE_APP) {
            //Close the app from feature two
            finish();
        }
    }
}
