package com.example.kirupa.jsonnew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UsersViewHolder>{
    Context context;
    List<ResponsePogo.CountriesBean> responsePogo;


    public RecyclerViewAdapter(Context context, List<ResponsePogo.CountriesBean> responsePogo) {
        this.responsePogo = responsePogo;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.reclerviewadapter, null);
        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        // set the data
        //  Image load using Bitmap
        //  new DownloadImageTask(holder.imageview).execute(userListResponseData.get(position).getImage());

        //Image Load using Picasso
        //Picasso.with(context).load(String.valueOf(userListResponseData.get(position).getImage())).into(holder.imageview);

        //Image load using Glide
        Glide.with(context)
                .load(responsePogo.get(position).getFlag())
                .override(300, 200)
                .into(holder.imageview);


        holder.data1.setText(responsePogo.get(position).getCountryname());
        holder.data2.setText(responsePogo.get(position).getCapital());
        holder.data3.setText(responsePogo.get(position).getLanguage());
        holder.data4.setText(responsePogo.get(position).getCurrency().getCode());
        holder.data5.setText(responsePogo.get(position).getCurrency().getCurrencyname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with user name
                Toast.makeText(context, responsePogo.get(position).getCapital(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return responsePogo.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        public ImageView imageview;
        public TextView data1;
        public TextView data2;
        public TextView data3;
        public TextView data4;
        public TextView data5;


        public UsersViewHolder(View itemView) {
            super(itemView);

            imageview = (ImageView) itemView.findViewById(R.id.ivImage);
            data1 = (TextView) itemView.findViewById(R.id.data1);
            data2 = (TextView) itemView.findViewById(R.id.data2);
            data3 = (TextView) itemView.findViewById(R.id.data3);
            data4 = (TextView) itemView.findViewById(R.id.data4);
            data5 = (TextView) itemView.findViewById(R.id.data5);

        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
