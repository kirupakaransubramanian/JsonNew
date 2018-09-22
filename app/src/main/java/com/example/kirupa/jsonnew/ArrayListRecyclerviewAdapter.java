package com.example.kirupa.jsonnew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
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

public class ArrayListRecyclerviewAdapter extends RecyclerView.Adapter<ArrayListRecyclerviewAdapter.UsersViewHolder>{
    Context context;
    ArrayList<String> dataArray1;
    ArrayList<String> dataArray2;
    ArrayList<String> dataArray3;
    ArrayList<String> dataArray4;
    ArrayList<String> dataArray5;
    ArrayList<String> dataArray6;

    public ArrayListRecyclerviewAdapter(Context context, ArrayList<String> dataArray1,
                               ArrayList<String> dataArray2,ArrayList<String> dataArray3,
                               ArrayList<String> dataArray4,ArrayList<String> dataArray5,
                               ArrayList<String> dataArray6) {
        this.dataArray1 = dataArray1;
        this.dataArray2 = dataArray2;
        this.dataArray3 = dataArray3;
        this.dataArray4 = dataArray4;
        this.dataArray5 = dataArray5;
        this.dataArray6 = dataArray6;
        this.context = context;
    }
    @Override
    public ArrayListRecyclerviewAdapter.UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.reclerviewadapter, null);
        ArrayListRecyclerviewAdapter.UsersViewHolder usersViewHolder = new ArrayListRecyclerviewAdapter.UsersViewHolder(view);
        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, final int position) {
        // set the data
        //  Image load using Bitmap
        //  new DownloadImageTask(holder.imageview).execute(userListResponseData.get(position).getImage());

        //Image Load using Picasso
        //Picasso.with(context).load(String.valueOf(userListResponseData.get(position).getImage())).into(holder.imageview);

        //Image load using Glide
        Glide.with(context)
                .load(dataArray2.get(position))
                .override(300, 200)
                .into(holder.imageview);


        holder.data1.setText(dataArray1.get(position));
        holder.data3.setText(dataArray3.get(position));
        holder.data4.setText(dataArray4.get(position));
        holder.data5.setText(dataArray5.get(position));
//        holder.data6.setText(dataArray6.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with user name
                Toast.makeText(context, dataArray1.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
       return dataArray1.size();
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
