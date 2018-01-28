package com.example.root.contactcollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rjpp on 1/28/2018.
 */

public class ContactAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Info> mDataSource;


    public ContactAdapter(Context context, ArrayList<Info> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_contact, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.contact_name);

        TextView email = (TextView) rowView.findViewById(R.id.contact_email);

        TextView phone = (TextView) rowView.findViewById(R.id.contact_phone);

        TextView website = (TextView) rowView.findViewById(R.id.contact_website);

        Info info = (Info) getItem(position);

        name.setText(info.getName());
        email.setText("Email: " + info.getEmail());
        phone.setText("Phone: " + info.getNumber());
        website.setText("Website: " + info.getWebsite());



        return rowView;
    }
}
