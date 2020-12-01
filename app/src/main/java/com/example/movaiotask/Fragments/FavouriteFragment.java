package com.example.movaiotask.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movaiotask.Adapters.PhotosAdaptor;
import com.example.movaiotask.Models.Photo;
import com.example.movaiotask.R;
import com.example.movaiotask.Utils.RealmController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavouriteFragment extends Fragment {

    @BindView(R.id.fragment_favourite_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_favourite_notification)
    TextView notification;

    private PhotosAdaptor mPhotosAdaptor;
    private List<Photo> photos = new ArrayList<>();
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites,container,false);
        mUnbinder = ButterKnife.bind(this,view);

        //Recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mPhotosAdaptor = new PhotosAdaptor(getActivity(),photos);
        mRecyclerView.setAdapter(mPhotosAdaptor);

        getPhotos();

        return view;
    }

    private void getPhotos(){
        RealmController realmController = new RealmController();
        photos.addAll((Collection<? extends Photo>) realmController.getPhotos());

        if (photos.size() == 0){
            notification.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mPhotosAdaptor.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}

