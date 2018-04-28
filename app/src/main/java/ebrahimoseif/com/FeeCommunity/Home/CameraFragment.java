package ebrahimoseif.com.FeeCommunity.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ebrahimoseif.com.FeeCommunity.Profile.AccountSettingsActivity;
import ebrahimoseif.com.FeeCommunity.R;
import ebrahimoseif.com.FeeCommunity.Share.NextActivity;
import ebrahimoseif.com.FeeCommunity.Share.ShareActivity;
import ebrahimoseif.com.FeeCommunity.Utils.Permissions;

/**
 * Created by Android App team to cloud university graduation project.
 */




public class CameraFragment extends Fragment {
    private static final String TAG = "CameraFragment";

    private static final int  CAMERA_REQUEST_CODE = 5;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);


        return view;
    }

    }



