package co.diginex.demoplayer.featureone;

import android.app.DialogFragment;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.diginex.demoplayer.AppConstants;
import co.diginex.demoplayer.R;

/**
 * Created by Sean on 13/05/2016.
 */
@SuppressWarnings("deprecation")
public class FeatureOnePlayerFragment extends DialogFragment {

    private SoundPool soundPool;
    private int soundId = -1;

    private String trackTitle;
    private String track;
    private String trackDescription;

    @BindView(R.id.fragment_f_one_player_description)
    TextView descriptionTextView;

    @OnClick(R.id.fragment_f_one_player_dismiss)
    void dismissDialog() {
        if (soundPool != null) {
            soundPool.stop(soundId);
            soundPool.release();
        }
        dismissAllowingStateLoss();
    }

    @OnClick(R.id.fragment_f_one_player_play)
    void playTrack() {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(sampleId, 1.0f, 1.0f, 1, -1, 1);
            }
        });
        AssetManager assetManager = getActivity().getAssets();
        try {
            AssetFileDescriptor afd = assetManager.openFd(track + ".mp3");
            soundId = soundPool.load(afd, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static FeatureOnePlayerFragment newInstance(JsonObject track) {

        FeatureOnePlayerFragment featureOnePlayerFragment = new FeatureOnePlayerFragment();
        Bundle args = new Bundle();
        args.putString(AppConstants.TRACK_TITLE, track.get(AppConstants.TRACK_TITLE).getAsString());
        args.putString(AppConstants.TRACK_DESCRIPTION, track.get(AppConstants.TRACK_DESCRIPTION).getAsString());
        args.putString(AppConstants.TRACK, track.get(AppConstants.TRACK).getAsString());
        featureOnePlayerFragment.setArguments(args);

        return featureOnePlayerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        trackTitle = args.getString(AppConstants.TRACK_TITLE);
        trackDescription = args.getString(AppConstants.TRACK_DESCRIPTION);
        track = args.getString(AppConstants.TRACK);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feature_one_player, container, false);
        ButterKnife.bind(this, view);
        getDialog().setTitle(trackTitle);
        descriptionTextView.setText(trackDescription);
        return view;
    }
}
