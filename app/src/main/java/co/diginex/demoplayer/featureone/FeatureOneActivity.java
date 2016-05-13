package co.diginex.demoplayer.featureone;

import android.support.design.widget.CoordinatorLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import butterknife.BindView;
import butterknife.ButterKnife;
import co.diginex.demoplayer.R;


public class FeatureOneActivity extends AppCompatActivity implements OnImageClickListener{

    @BindView(R.id.activity_f_one_coordinator)
    CoordinatorLayout coordinatorLayoutFOne;

    @BindView(R.id.activity_f_one_recycler)
    RecyclerView recyclerFOne;

    FeatureOneRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_one);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Calling in onResume for instances where data would be updated. Not in this case, but perhaps better practice?
        initRecyclerView();
    }

    public void initRecyclerView(){
        adapter = new FeatureOneRecyclerAdapter(this,this);
        recyclerFOne.setAdapter(adapter);
        recyclerFOne.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void imageClick(int position) {
        //TODO start the Dialog using the Json Object from adapter.getItemAtPosition(position);
    }
}
