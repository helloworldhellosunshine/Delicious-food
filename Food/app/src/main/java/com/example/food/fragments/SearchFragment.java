package com.example.food.fragments;

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
import android.widget.Toast;

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

public class SearchFragment extends Fragment implements CookBookView{

    String key;

    ListView listView;
    List<CookBook>cookBookList;
    MyAdapter myadapter;
    CookBookPresenter cookBookPresenter;

    public static SearchFragment newInstance(String key){
        SearchFragment searchFragment=new SearchFragment();
        Bundle b=new Bundle();
        b.putString("key",key);
        searchFragment.setArguments(b);
        return searchFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cookBookPresenter=new CookBookPresenterImpl(this);
        cookBookList=new ArrayList<>();
        Bundle b=this.getArguments();
        key=b.getString("key");
        cookBookPresenter.getCookBookListByKey(getActivity(),key);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        listView=(ListView) view.findViewById(R.id.listVV);
        myadapter =new MyAdapter();
        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CookBook cookBook=cookBookList.get(i);
                Intent intent=new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("cookbook",cookBook);
                startActivity(intent);
            }
        });
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cookBookList.size();
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

            CookBook cookBook=cookBookList.get(i);
            String url=cookBook.getAlbums().get(0);
            String title=cookBook.getTitle();
            String intro=cookBook.getImtro();

            tv1.setText(title);
            tv2.setText(intro);

            Picasso.with(getActivity()).load(url).resize(120,120).centerCrop().into(iv);


            return myView;
        }
    }

    @Override
    public void setCooKBookList(List<CookBook> list) {
        this.cookBookList=list;
        myadapter.notifyDataSetChanged();
    }

    @Override
    public void setFail() {
        Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
    }
}
