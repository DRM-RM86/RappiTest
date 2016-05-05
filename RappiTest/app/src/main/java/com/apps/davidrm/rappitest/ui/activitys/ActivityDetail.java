package com.apps.davidrm.rappitest.ui.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityEntry;
import com.apps.davidrm.rappitest.ui.bases.BaseUIActivity;
import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;
import com.squareup.picasso.Picasso;

/**
 * Created by DRM on 03/05/16.
 */
public class ActivityDetail extends BaseUIActivity {

    private ExitActivityTransition exitTransition;
    ImageView image_item,img_detail;
    EntityEntry entityEntry;
    public static String KEYARG="key";
    TextView tx_summary,tx_title,tx_rigth,tx_category;
    Toolbar mToolbar;
    Animation anim1;
    Animation anim2;
    LinearLayout content_summary,content_app_data;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle=getIntent().getExtras();
        entityEntry= (EntityEntry) bundle.getSerializable(KEYARG);


        exitTransition = ActivityTransition.with(getIntent()).to(findViewById(R.id.image_item)).start(savedInstanceState);
        image_item=(ImageView)findViewById(R.id.image_item);
        img_detail=(ImageView)findViewById(R.id.img_detail);
        tx_summary=(TextView)findViewById(R.id.tx_summary);
        tx_title=(TextView)findViewById(R.id.tx_title);
        tx_rigth=(TextView)findViewById(R.id.tx_rigth);
        tx_category=(TextView)findViewById(R.id.tx_category);
        content_summary=(LinearLayout)findViewById(R.id.content_summary);
        content_app_data=(LinearLayout)findViewById(R.id.content_app_data);


        Picasso.with(this).load(entityEntry.getImages().get(2)).into(image_item);
        Picasso.with(this).load(entityEntry.getImages().get(2)).into(img_detail);
        tx_summary.setText(entityEntry.getEntitysummary());
        tx_title.setText(entityEntry.getEntityName());
        tx_rigth.setText(entityEntry.getEntityrights());
        tx_category.setText(entityEntry.getEntitycategory());

        anim1= AnimationUtils.loadAnimation(this,R.anim.anim_enter_up);
        anim2= AnimationUtils.loadAnimation(this,R.anim.anim_enter_down);
        content_summary.startAnimation(anim1);
        content_app_data.startAnimation(anim2);

        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(entityEntry.getEntityName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitTransition.exit(this);
    }
}
