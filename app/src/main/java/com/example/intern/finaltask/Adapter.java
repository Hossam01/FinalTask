package com.example.intern.finaltask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.intern.finaltask.Retrofit.GitHubRepo;

import java.util.List;

public class Adapter extends BaseAdapter {
    List<GitHubRepo> arrayitems;
    LayoutInflater mLayoutInflater;
    int resource;
    ViewHolder viewHolder;
    GitHubRepo listitem;
    Context mContext;



    public Adapter(Context context, int resource, List<GitHubRepo> objects)
    {
        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        arrayitems = objects;
        mContext=context;

    }


    @Override
    public int getCount() {
        return arrayitems.size();

    }

    @Override
    public Object getItem(int position) {
        return arrayitems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View mView = view;
        if (mView == null) {
            viewHolder = new ViewHolder();
            mView = mLayoutInflater.inflate(resource, null);
            viewHolder.mTextView=mView.findViewById(R.id.text_item);
            mView.setTag(viewHolder);

        }
        else {
            viewHolder=(ViewHolder)mView.getTag();
        }
        listitem=arrayitems.get(position);
        viewHolder.mTextView.setText(listitem.getName().toString());
        return mView;
    }
    public class ViewHolder {
        public TextView mTextView;

    }
}
