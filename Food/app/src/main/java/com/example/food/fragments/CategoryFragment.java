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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.DetailActivity;
import com.example.food.R;
import com.example.food.books.CookBookPresenter;
import com.example.food.books.CookBookPresenterImpl;
import com.example.food.books.CookBookView;
import com.example.food.category.CategoryPresenter;
import com.example.food.category.CategoryPresenterImpl;
import com.example.food.category.CategoryView;
import com.example.food.category.beans.Category;
import com.example.food.category.beans.Category1;

import com.example.food.category.beans.Category2;
import com.example.food.cookbook.beans.CookBook;
import com.squareup.picasso.Picasso;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王帝 on 2017/9/27.
 */

public class CategoryFragment extends Fragment implements CategoryView ,CookBookView{

    Category category;
    CategoryPresenter categoryPresenter;
    ProgressBar pb;
    LinearLayout treeContainer;
    ListView listView;
    CookBookPresenter cookBookPresenter;
    List<CookBook>cookBookList;
    ListViewAdapter listViewAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryPresenter = new CategoryPresenterImpl(getActivity(), this);
        categoryPresenter.getCategory();

        cookBookPresenter = new CookBookPresenterImpl(this);

        cookBookList=new ArrayList<>();

        listViewAdapter=new ListViewAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initViews(view);
        pb.setVisibility(View.VISIBLE);
        return view;
    }
    //listView 的适配器
    class ListViewAdapter extends BaseAdapter{
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
            if (view==null){
                myView=LayoutInflater.from(getActivity()).inflate(R.layout.list_cookbook_item,null);
            }else {
                myView=view;
            }
            ImageView iv=(ImageView)myView.findViewById(R.id.imageView);

            TextView titleTv=(TextView)myView.findViewById(R.id.title);

            TextView introTv=(TextView)myView.findViewById(R.id.intro);

            CookBook cookBook=cookBookList.get(i);

            titleTv.setText(cookBook.getTitle());

            introTv.setText(cookBook.getImtro());

            String url=cookBook.getAlbums().get(0);

            Picasso.with(getActivity()).load(url).into(iv);

            return myView;
        }
    }
     class IconTreeItem {
         public boolean isSub;
        public int icon;
        public String text;
    }
    class MyHolder extends TreeNode.BaseNodeViewHolder<IconTreeItem> {

        public MyHolder(Context context) {
            super(context);
        }

        @Override
        public View createNodeView(TreeNode node, IconTreeItem value) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.tree_item, null, false);

            if (value.isSub){
                view.setPadding(52,20,10,20);
            }
            TextView tvValue = (TextView) view.findViewById(R.id.textview);
            ImageView iv=(ImageView)view.findViewById(R.id.img);
            tvValue.setText(value.text);
            iv.setImageResource(value.icon);
            return view;
        }
    }
    private void initViews(View view) {
        pb = (ProgressBar) view.findViewById(R.id.pb1);
        treeContainer = (LinearLayout) view.findViewById(R.id.tree_container);
        listView=(ListView)view.findViewById(R.id.listView1);
        listView.setAdapter(listViewAdapter);
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

    @Override
    public void setCategory(Category category) {
        this.category = category;
        pb.setVisibility(View.GONE);
        initTree();
    }

    private void initTree() {
        List<Category1> list1 = this.category.getResult();
        TreeNode root = TreeNode.root();
        for (Category1 c1 : list1) {
            String name = c1.getName();

            IconTreeItem nodeItem = new IconTreeItem();
            nodeItem.icon=R.drawable.jj;
            nodeItem.text=name;
            nodeItem.isSub=false;
            TreeNode parent = new TreeNode(nodeItem).setViewHolder(new MyHolder(getActivity()));


            List<Category2> list2 = c1.getList();
            for (final Category2 c2 : list2) {
                String subName = c2.getName();

                IconTreeItem nodeItem2 = new IconTreeItem();
                nodeItem2.icon=R.drawable.dd;
                nodeItem2.text=subName;
                nodeItem2.isSub=true;
                TreeNode child = new TreeNode(nodeItem2).setViewHolder(new MyHolder(getActivity()));

                child.setClickListener(new TreeNode.TreeNodeClickListener() {
                    @Override
                    public void onClick(TreeNode node, Object value) {
                        String id=c2.getId();
                        cookBookPresenter.getCookBookListById(getActivity(),id);
                        pb.setVisibility(View.INVISIBLE);
                    }
                });


                parent.addChildren(child);
            }
            root.addChild(parent);
        }
        AndroidTreeView tView = new AndroidTreeView(getActivity(), root);
        treeContainer.addView(tView.getView());
    }

    @Override
    public void setCooKBookList(List<CookBook> list) {
        this.cookBookList=list;
        listViewAdapter.notifyDataSetChanged();
        pb.setVisibility(View.GONE);
    }

    @Override
    public void setFail() {
        Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
    }
}
