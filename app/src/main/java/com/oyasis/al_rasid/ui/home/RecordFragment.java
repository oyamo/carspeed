package com.oyasis.al_rasid.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.oyasis.al_rasid.ADDatabase;
import com.oyasis.al_rasid.CarDetailsListener;
import com.oyasis.al_rasid.R;
import com.oyasis.al_rasid.databinding.FragmentRecordBinding;
import com.oyasis.al_rasid.ui.history.Speed;
import com.oyasis.al_rasid.ui.history.SpeedDao;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends Fragment implements LocationListener {


    FragmentRecordBinding binding;
    private SensorManager sensorManager;
    private Sensor sensor;
    private TriggerEventListener triggerEventListener;
    private GoogleMap mMap;
    private LocationManager locationManager;
    Speed mSpeed;
    ADDatabase database;
    SpeedDao dao;
    public RecordFragment() {
        // Required empty public constructor
        mSpeed = new Speed();
    }


    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        database = ADDatabase.getInstance(requireActivity());
        dao = database.getSpeedDao();
        binding = FragmentRecordBinding.inflate(getLayoutInflater());
        binding.speedView.setTrembleDuration(0);
        binding.speedView.speedTo(0, 1);
        binding.speedView.setWithTremble(false);
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        getVelocity();
        binding.startBtn.setOnClickListener(v -> {
                if(binding.startBtn.getText().toString().equalsIgnoreCase("START")) {
                    CarDetails carDetails = new CarDetails();
                    carDetails.setOnAddedListener(new CarDetailsListener() {
                        @Override
                        public void OnAdded(Speed speed) {
                            mSpeed.setMovingTo(speed.getMovingTo());
                            mSpeed.setMovingFrom(speed.getMovingFrom());
                            mSpeed.setNumberPlate(speed.getNumberPlate());
                            binding.startBtn.setText(R.string.stop);
                        }
                    });
                    carDetails.show(getParentFragmentManager(), null);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setCancelable(true);
                    builder.setTitle("Success");
                    builder.setMessage(mSpeed.getNumberPlate() + "'s speed has been recorded");

                    builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                    binding.startBtn.setText(R.string.start);
                    dao.addNew(mSpeed);
                }
        });
        return binding.getRoot();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (hasPermision()) {
            getVelocity();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setCancelable(true);
            builder.setTitle("Location permission needed");
            builder.setMessage("Please allow this app to access your location");
            builder.show();
            builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            getPerm();
        }
    }

    public boolean hasPermision() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

    }

    public void getPerm() {
        String[] permisions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(requireActivity(), permisions, 200);
    }

    public void getVelocity() {
        if (ActivityCompat.checkSelfPermission( requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           getPerm();
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20, 1, this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        float speed = location.getSpeed();
        binding.speedView.speedTo(speed);
        mSpeed.setSpeed(speed);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}