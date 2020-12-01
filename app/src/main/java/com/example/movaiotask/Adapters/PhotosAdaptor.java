package com.example.movaiotask.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.movaiotask.Activities.FullScreenPhotoActivity;
import com.example.movaiotask.Models.Photo;
import com.example.movaiotask.R;
import com.example.movaiotask.Utils.SquareImage;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotosAdaptor extends RecyclerView.Adapter<PhotosAdaptor.ViewHolder> {
    private Context mContext;
    private List<Photo> mPhotos;

    public PhotosAdaptor(Context context,List<Photo> photoList) {
        this.mContext = context;
        this.mPhotos = photoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo = mPhotos.get(position);
        holder.username.setText(photo.getUser().getUsername());

        GlideApp.with(mContext)
                .load(photo.getUrl().getRegular())
                .placeholder(R.drawable.ic_menu_camera)
                .override(600,600)
                .into(holder.photo);

        GlideApp.with(mContext)
                .load(photo.getUser().getProfileImage().getSmall())
                .into(holder.userAvatar);

    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_photo_user_avatar)
        CircleImageView userAvatar;
        @BindView(R.id.item_photo_username)
        TextView username;
        @BindView(R.id.item_photo)
        SquareImage photo;
        @BindView(R.id.item_photo_layout)
        ConstraintLayout mConstraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.item_photo_layout)
        public void setLayout(){
            int position = getAdapterPosition();
            String photoid = mPhotos.get(position).getId();
            Intent intent = new Intent(mContext, FullScreenPhotoActivity.class);
            intent.putExtra("photoId",photoid);
            mContext.startActivity(intent);
        }
    }
}

