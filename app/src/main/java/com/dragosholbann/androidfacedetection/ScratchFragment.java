package com.dragosholbann.androidfacedetection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.anupkumarpanwar.scratchview.ScratchView;
import com.dragosholbann.androidfacedetection.DBHelper.MyDB;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CampaignlistOfferItem;
import com.dragosholbann.androidfacedetection.ModelLayer.NetworkLayer.Helpers.ApiClient;
import com.google.android.gms.common.api.Api;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ScratchFragment extends Fragment
{
    private static final String TAG = "ScratchFragment____";
    MyDB myDB;
    ScratchView scratchCard;
    TextView tv_scratch_card,tv_scratch_card_expiry,tv_scratch_card_desc;
    ImageView scratch_card_iv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scratch_fragment,container,false);
        scratchCard = view.findViewById(R.id.scratch_view_fragment);
        tv_scratch_card = view.findViewById(R.id.tv_scratch_card);
        tv_scratch_card_expiry = view.findViewById(R.id.tv_scratch_card_expiry);
        tv_scratch_card_desc = view.findViewById(R.id.tv_scratch_card_desc);
        scratch_card_iv = view.findViewById(R.id.scratch_card_iv);


        myDB = new MyDB(getActivity());
        ArrayList<CampaignlistOfferItem> campaignlistOfferItems = myDB.getNewAllOffers();
        if(campaignlistOfferItems != null ) {
            if(campaignlistOfferItems.size() != 0){
            tv_scratch_card.setText(campaignlistOfferItems.get(0).getOfferData().getOfferCode());
            tv_scratch_card_desc.setText(campaignlistOfferItems.get(0).getOfferData().getOfferDescription());
            tv_scratch_card_expiry.setText("Expiry on " + campaignlistOfferItems.get(0).getOfferData().getOfferExpiryDate());
            // Log.e(TAG, "onCreateView  -  ");

            Picasso.with(getContext()).
                    load(ApiClient.BASE_IMAGE_URL + campaignlistOfferItems.get(0).getOfferData().getOfferImg())

                    //.resize(width_custom,heightt_custom)
                    //.centerInside()
                    .into(scratch_card_iv, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {

                        }
                    });

            scratchCard.setStrokeWidth(5);

            scratchCard.setRevealListener(new ScratchView.IRevealListener() {
                @Override
                public void onRevealed(ScratchView scratchView) {
                    //Toast.makeText(getApplicationContext(), "Reveled", Toast.LENGTH_LONG).show();;
                }

                @Override
                public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {
                    if (percent >= 0.11) {
                        if (tv_scratch_card.getText() == null || tv_scratch_card.getText().toString().equals("")) {

                        } else {
                            scratchView.clear();
                            //VideoFaceDetectionActivity.claim_offer.setVisibility(View.VISIBLE);
                        }
                        // Log.e(TAG, "onRevealPercentChangedListener: " + String.valueOf(percent));
                    }
                }
            });
        }
        }

        return  view;
    }

    private void initcalls(View view) {
        initviews(view);
        initlistner();
    }


    private void initlistner() {

    }

    private void initviews(View view) {
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
      //  Log.e(TAG, "onResume  -  ");
    }

    @Override
    public void onPause() {
        super.onPause();
      //  Log.e(TAG, "onPause  -  ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scratchCard.invalidate();
        scratchCard.refreshDrawableState();
        tv_scratch_card.setText("");
        if (myDB.deleteOfferCampaignItemById())
            Log.e(TAG, "onDestroy  -  deleteOfferCampaignItemById  ADV __________ SUCCESS __________ ");
        else
            Log.e(TAG, "onDestroy  -  deleteOfferCampaignItemById  ADV ?????????? ERRORR ???????????? ");
    }
}
