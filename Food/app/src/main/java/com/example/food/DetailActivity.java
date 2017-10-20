package com.example.food;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.cookbook.beans.CookBook;
import com.example.food.cookbook.beans.Step;
import com.example.food.util.DensityUtil;
import com.squareup.picasso.Picasso;
import com.wefika.flowlayout.FlowLayout;

import java.util.List;

/**
 * Created by 王帝 on 2017/10/2.
 */

public class DetailActivity extends AppCompatActivity {

    ImageView headImageView;

    TextView titleTv;

    TextView introTv;

    CookBook cookBook;

    List<Step> stepList;

    FlowLayout flowLayout;

    String[] mainList;
    String[] fuList;

    ListView mainListView, fuLListView, setpListView;

    MainListViewAdapter mainListViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        CookBook cookBook = (CookBook) intent.getSerializableExtra("cookbook");
        this.cookBook = cookBook;
        initViews();
    }

    private void initViews() {

        headImageView = (ImageView) findViewById(R.id.imageivew);
        String url = cookBook.getAlbums().get(0);
        Picasso.with(this).load(url).into(headImageView);
        titleTv = (TextView) findViewById(R.id.title1);
        titleTv.setText(cookBook.getTitle());

        introTv = (TextView) findViewById(R.id.intro1);
        introTv.setText(cookBook.getImtro());

        flowLayout = (FlowLayout) findViewById(R.id.flowlayout);

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.leftMargin = DensityUtil.dip2px(this, 2);
        params.rightMargin = DensityUtil.dip2px(this, 2);
        params.bottomMargin = DensityUtil.dip2px(this, 4);

        String tags = cookBook.getTags();
        String[] array = tags.split(";");

        for (String t : array) {
            TextView tv = new TextView(this);
            tv.setText(t);
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundColor(Color.BLACK);
            flowLayout.addView(tv, params);
        }
        //主食材：
        mainListView = (ListView) findViewById(R.id.mainListview);
        mainList = cookBook.getIngredients().split(";");
        mainListViewAdapter = new MainListViewAdapter();

        int count = mainListViewAdapter.getCount();
        int h = DensityUtil.dip2px(this, 50) * count;
        mainListView.getLayoutParams().height = h;

        mainListView.setAdapter(mainListViewAdapter);

        fuLListView = (ListView) findViewById(R.id.fuListview);
        fuList = cookBook.getBurden().split(";");
        FuListViewAdapter fulistviewAdapter = new FuListViewAdapter();

        int count2 = fulistviewAdapter.getCount();
        int h2 = DensityUtil.dip2px(this, 38) * count2;
        fuLListView.getLayoutParams().height = h2;
        fuLListView.setAdapter(fulistviewAdapter);

        //step

        setpListView = (ListView) findViewById(R.id.step1);

        stepList = cookBook.getSteps();

        StepListViewAdapter stepListViewadapter=new StepListViewAdapter();

        int count3 = stepListViewadapter.getCount();
        int h3 = DensityUtil.dip2px(this, 250) * count3;
        setpListView.getLayoutParams().height = h3;

        setpListView.setAdapter(stepListViewadapter);

    }

    class StepListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return stepList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myView;
            if (view == null) {
                myView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listview_step, null);

            } else {
                myView = view;
            }
            TextView numberTv = (TextView) myView.findViewById(R.id.number);
            numberTv.setText("" + (i + 1));
            ImageView iv = (ImageView) myView.findViewById(R.id.image_z);
            TextView tv = (TextView) myView.findViewById(R.id.textview_step);
            Step step = stepList.get(i);
            tv.setText(step.getStep());
            Picasso.with(getApplicationContext()).load(step.getImg()).into(iv);
            return myView;
        }
    }


    class FuListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return fuList.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myView;
            if (view == null) {
                myView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listview_cookbook_detsil_item, null);
            } else {
                myView = view;
            }
            TextView left = (TextView) myView.findViewById(R.id.left);
            TextView right = (TextView) myView.findViewById(R.id.right);

            String item = fuList[i];
            left.setText(item.split(",")[0]);
            right.setText(item.split(",")[1]);


            return myView;
        }
    }


    class MainListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mainList.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myView;
            if (view == null) {
                myView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listview_cookbook_detsil_item, null);
            } else {
                myView = view;
            }
            TextView left = (TextView) myView.findViewById(R.id.left);
            TextView right = (TextView) myView.findViewById(R.id.right);

            String item = mainList[i];
            left.setText(item.split(",")[0]);
            right.setText(item.split(",")[1]);
            return myView;
        }
    }

}
