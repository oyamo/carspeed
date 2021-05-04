package com.oyasis.al_rasid.ui.home;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.oyasis.al_rasid.ADDatabase;
import com.oyasis.al_rasid.CarDetailsListener;
import com.oyasis.al_rasid.databinding.FragmentCarDetailsBinding;
import com.oyasis.al_rasid.ui.history.Speed;
import com.oyasis.al_rasid.ui.history.SpeedDao;


public class CarDetails extends DialogFragment {

    private FragmentCarDetailsBinding binding;
    private Speed speed;
    ADDatabase database;
    SpeedDao dao;
    CarDetailsListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCarDetailsBinding.inflate(inflater, container, false);
        speed = new Speed();

        database = ADDatabase.getInstance(requireContext());
        dao = database.getSpeedDao();

        binding.doneBtn.setOnClickListener(v -> {
            speed.setMovingFrom(binding.carFrom.getText().toString());
            speed.setMovingTo(binding.carDestination.getText().toString());
            speed.setNumberPlate(binding.registrationNo.getText().toString());
            listener.OnAdded(speed);
            dismiss();
        });

        binding.carPicture.setOnClickListener(v -> {
            dispatchTakePictureIntent();
        });
        binding.closebtn.setOnClickListener(v ->{
            dismiss();
        });

        return binding.getRoot();

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            binding.carPicture.setImageBitmap(imageBitmap);
            binding.carPicture.setScaleType(ImageView.ScaleType.FIT_XY);
            binding.carPicture.setBackgroundColor(Color.parseColor("#00000000"));
        }
    }

    void setOnAddedListener(CarDetailsListener listener){
        this.listener = listener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}