package co.diginex.demoplayer.featureone;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.diginex.demoplayer.AppConstants;
import co.diginex.demoplayer.R;


/**
 * Created by Sean on 12/05/2016.
 */
public class FeatureOneRecyclerAdapter extends RecyclerView.Adapter<FeatureOneRecyclerAdapter.TrackViewHolder> {

    final private JsonArray trackListJsonArray;
    final private OnImageClickListener onImageClickListener;
    final LayoutInflater inflater;

    public FeatureOneRecyclerAdapter(OnImageClickListener onImageClickListener, final Context context) {
        trackListJsonArray = new JsonParser().parse(AppConstants.featureOneJson).getAsJsonArray();
        this.onImageClickListener = onImageClickListener;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                inflater.inflate(R.layout.feature_one_recycler_item, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TrackViewHolder holder, int position) {
        holder.bindItem(trackListJsonArray.get(position).getAsJsonObject(),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onImageClickListener.imageClick(holder.getAdapterPosition());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return trackListJsonArray.size();
    }

    public JsonObject getItemAtPosition(int position) {
        return trackListJsonArray.get(position).getAsJsonObject();
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fone_recycler_item_description)
        TextView itemDescription;

        @BindView(R.id.fone_recycler_item_heading)
        TextView itemHeading;

        @BindView(R.id.fone_recycler_item_drawee)
        SimpleDraweeView itemImage;


        public TrackViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(final JsonObject trackItem, final View.OnClickListener viewHolderImageClick) {
            //Setting the Drawee view to resize the image. If this is not done, the UI becomes janky as it tries to scale the large image to fit the small display space
            int width = 150, height = 150;

            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(trackItem.get(AppConstants.IMAGE_URL).getAsString()))
                    .setResizeOptions(new ResizeOptions(width, height))
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setOldController(itemImage.getController())
                    .setImageRequest(request)
                    .build();

            itemImage.setController(controller);
            itemHeading.setText(trackItem.get(AppConstants.NAME).getAsString());
            itemDescription.setText(trackItem.get(AppConstants.DESCRIPTION).getAsString());

            itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderImageClick.onClick(v);
                }
            });
        }
    }
}
