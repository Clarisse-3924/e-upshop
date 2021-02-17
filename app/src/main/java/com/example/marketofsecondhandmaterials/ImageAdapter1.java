package com.example.marketofsecondhandmaterials;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter1 extends RecyclerView.Adapter<ImageAdapter1.ImageViewHolder> {
    private Context mContext;
    private List<upload>muploads;
    private onItemclickListener mListener;
    Dialog myDialog;
    Activity mActivity;

    public ImageAdapter1(Context context, List<upload>uploads){
        mContext=context;
        muploads=uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(mContext).inflate(R.layout.sellinglists,parent,false);
        ImageViewHolder vholder= new ImageViewHolder(v);
        //DIALOG
        myDialog =new Dialog(mContext);

        vholder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView(R.layout.dialog_info);
                Button btnid=myDialog.findViewById(R.id.btnid);
                Button btnid1=myDialog.findViewById(R.id.btnid1);

                btnid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(mContext, ordering.class);
                        mContext.startActivity(i);
                    }
                });
                btnid1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(mContext, contact.class);
                        mContext.startActivity(i);
                    }
                });
                TextView nameid = myDialog.findViewById(R.id.nameid);
                TextView phoneid = myDialog.findViewById(R.id.phoneid);
                TextView descriptionid= myDialog.findViewById(R.id.decriptionid);
                nameid.setText(muploads.get(vholder.getAdapterPosition()).getPname());
                phoneid.setText(muploads.get(vholder.getAdapterPosition()).getPrice());
                descriptionid.setText(muploads.get(vholder.getAdapterPosition()).getDescription());

                Toast.makeText(mContext,"clicked"+String.valueOf(vholder.getAdapterPosition()),Toast.LENGTH_SHORT);
                myDialog.show();
            }
        });

        return vholder;

    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        upload uploadCurrent=muploads.get(position);
        holder.Product.setText(uploadCurrent.getPname());
        holder.Description1.setText(uploadCurrent.getDescription());
        holder.Price.setText(uploadCurrent.getPrice());
        Glide.with(mContext).load(uploadCurrent.getImageUrl()).placeholder(R.mipmap.defaultimg)
                .centerInside().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }
    public void filterList(ArrayList<upload> filteredlist) {
        muploads = filteredlist;
        notifyDataSetChanged();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        private LinearLayout linear;
        public TextView Product;
        public TextView Description1;
        public TextView   Price;
        public ImageView  imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            linear=itemView.findViewById(R.id.linear);
            Product=itemView.findViewById(R.id.a);
            Description1=itemView.findViewById(R.id.b);
            Price=itemView.findViewById(R.id.c);
            imageView=itemView.findViewById(R.id.post_image);

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
            MenuItem delete= contextMenu.add(Menu.NONE,2,2,"Order now !!!");
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
