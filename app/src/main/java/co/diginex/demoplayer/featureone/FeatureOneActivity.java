package co.diginex.demoplayer.featureone;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.diginex.demoplayer.R;


public class FeatureOneActivity extends AppCompatActivity implements OnImageClickListener {

    FeatureOneRecyclerAdapter adapter;

    @BindView(R.id.activity_f_one_recycler)
    RecyclerView recyclerFOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_one);
        setTitle(R.string.feature_one);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Calling in onResume for instances where data would be updated. Not in this case, but perhaps better practice?
        initRecyclerView();
    }

    public void initRecyclerView() {
        adapter = new FeatureOneRecyclerAdapter(this, this);
        recyclerFOne.setAdapter(adapter);
        recyclerFOne.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void imageClick(int position) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment previousFrag = getFragmentManager().findFragmentByTag("dialog");
        if (previousFrag != null) {
            fragmentTransaction.remove(previousFrag);
        }
        fragmentTransaction.addToBackStack(null);

        DialogFragment frag = FeatureOnePlayerFragment.newInstance(adapter.getItemAtPosition(position));
        frag.show(fragmentTransaction, "dialog");
    }
}
