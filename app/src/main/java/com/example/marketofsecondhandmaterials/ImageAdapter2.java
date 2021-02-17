package com.example.marketofsecondhandmaterials;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter2 extends RecyclerView.Adapter<ImageAdapter2.ImageViewHolder> {
    private Context mContext;
    private List<Booking>muploads;
    private onItemclickListener mListener;

    public ImageAdapter2(Context context, List<Booking>uploads){
        mContext=context;
        muploads=uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(mContext).inflate(R.layout.list,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Booking uploadCurrent=muploads.get(position);
        holder.Name.setText(uploadCurrent.getName());
        holder.Code.setText(uploadCurrent.getCode());
        holder.Phone.setText(uploadCurrent.getPhone());
        holder.Location.setText(uploadCurrent.getLocation());
        //Glide.with(mContext).load(uploadCurrent.getImageUrl()).placeholder(R.mipmap.defaultimg)
               // .centerInside().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView Name;
        public TextView Code;
        public TextView   Phone;
        public TextView  Location;
        public ImageViewHolder(View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.a);
            Code=itemView.findViewById(R.id.b);
            Location=itemView.findViewById(R.id.c);
            Phone=itemView.findViewById(R.id.d);
           // imageView=itemView.findViewById(R.id.post_image);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener!=null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }

            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("select Action");
            MenuItem dowhatever= contextMenu.add(Menu.NONE,1,1,"Do whatever");
            MenuItem delete= contextMenu.add(Menu.NONE,2,2,"Delete");
            dowhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if(mListener!=null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    switch (menuItem.getItemId()){
                        case 1:
                            mListener.onWhatEverClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }

            }
            return false;
        }
    }
    public interface onItemclickListener{
        void onItemClick(int position);
        void onWhatEverClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(onItemclickListener listener){
        mListener=listener;
    }
}
