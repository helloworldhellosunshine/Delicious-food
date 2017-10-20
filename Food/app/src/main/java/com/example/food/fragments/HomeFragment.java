package com.example.food.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.food.DetailActivity;
import com.example.food.R;
import com.example.food.books.CookBookPresenter;
import com.example.food.books.CookBookPresenterImpl;
import com.example.food.books.CookBookView;
import com.example.food.cookbook.beans.CookBook;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王帝 on 2017/9/27.
 */

public class HomeFragment extends Fragment implements CookBookView {

    ConvenientBanner convenientBanner;
    ListView listview;
    CookBookPresenter cookBookPresenter;
    List<CookBook> list;
    MyAdapter myAdapter;

    static List<Integer>localImages=new ArrayList<>();

    static {
        localImages.add(R.drawable.food4);
        localImages.add(R.drawable.food3);
        localImages.add(R.drawable.food2);
        localImages.add(R.drawable.food1);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list=new ArrayList<>();
        cookBookPresenter=new CookBookPresenterImpl(this);
        cookBookPresenter.getCookBookListByKey(getActivity(),"家常菜");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home,container,false);

        initViews(view);
        initBanner();
        return view;

    }

    void initViews(View view){
        listview=(ListView)view.findViewById(R.id.listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CookBook cookBook=list.get(i);
                Intent intent=new Intent(getActivity(), DetailActivity.class);

                intent.putExtra("cookbook",cookBook);

                startActivity(intent);
            }
        });



        myAdapter=new MyAdapter();
        listview.setAdapter(myAdapter);
        convenientBanner=(ConvenientBanner)view.findViewById(R.id.convenientBanner);
    }

    @Override
    public void setCooKBookList(List<CookBook> list) {
        this.list=list;
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void setFail() {

    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
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
            if(view==null){
                myView=getActivity().getLayoutInflater().inflate(R.layout.list_cookbook_item,null);
            }else {
                myView=view;
            }

            ImageView iv=(ImageView)myView.findViewById(R.id.imageView);
            TextView tv1=(TextView)myView.findViewById(R.id.title);
            TextView tv2=(TextView)myView.findViewById(R.id.intro);

            CookBook cookBook=list.get(i);
            String url=cookBook.getAlbums().get(0);
            String title=cookBook.getTitle();
            String intro=cookBook.getImtro();

            tv1.setText(title);
            tv2.setText(intro);

            Picasso.with(getActivity()).load(url).resize(120,120).centerCrop().into(iv);


            return myView;
        }
    }

     void initBanner() {
         convenientBanner.setPages(
                 new CBViewHolderCreator<LocalImageHolderView>() {
                     @Override
                     public LocalImageHolderView createHolder() {
                         return new LocalImageHolderView();
                     }
                 }, localImages)
                 //设置指示器的方向
                 .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);

    }

    class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
}
