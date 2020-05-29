package com.blkxltng.a20200526_mauricegaynor_nycschools.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.blkxltng.a20200526_mauricegaynor_nycschools.R;
import com.blkxltng.a20200526_mauricegaynor_nycschools.databinding.FragmentDetailsBinding;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SatScores;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SchoolInfo;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            SchoolInfo info = DetailsFragmentArgs.fromBundle(getArguments()).getInfo();
            SatScores scores = DetailsFragmentArgs.fromBundle(getArguments()).getScores();
            binding.setSchoolInfo(info);
            binding.setSatScores(scores);
            binding.executePendingBindings();

            binding.phoneNumber.setOnClickListener(v -> {
                createDialog(true, info);
            });

            binding.email.setOnClickListener(v -> {
                createDialog(false, info);
            });

            binding.website.setOnClickListener(v -> {
                //Load website
                Intent i = new Intent(Intent.ACTION_VIEW);
                if(info.website.startsWith("http://") || info.website.startsWith("https://")) {
                    i.setData(Uri.parse(info.website));
                } else {
                    i.setData(Uri.parse("http://" + info.website));
                }
                startActivity(i);
            });

        } else {
            // Error loading school info
        }

    }

    public void createDialog(Boolean isPhone, SchoolInfo info) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String message;
        Intent intent;
        if (isPhone) {
            message = "Would you like to call " + info.school_name + "?";
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + binding.phoneNumber.getText().toString()));
        } else {
            message = "Would you like to email " + info.school_name + "?";
            intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, info.school_email);
        }
        builder.setMessage(message);
        builder.setPositiveButton("yes", (dialog, id) -> {
            // User clicked OK button
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "No app was found for your request.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("cancel", (dialog, id) -> {
            // User cancelled the dialog
        });
        builder.create().show();
    }
}
