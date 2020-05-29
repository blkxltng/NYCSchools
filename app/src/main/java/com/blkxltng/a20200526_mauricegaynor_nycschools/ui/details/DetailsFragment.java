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
            // Grab the school info and scores that were sent from the previous fragment and assign
            // them to this fragment
            SchoolInfo info = DetailsFragmentArgs.fromBundle(getArguments()).getInfo();
            SatScores scores = DetailsFragmentArgs.fromBundle(getArguments()).getScores();
            binding.setSchoolInfo(info);
            binding.setSatScores(scores);
            binding.executePendingBindings();

            // Shows a dialog asking if hte user would like to call
            binding.phoneNumberValue.setOnClickListener(v -> {
                createDialog(true, info);
            });

            // Shows a dialog asking if the user would like to email
            binding.emailValue.setOnClickListener(v -> {
                createDialog(false, info);
            });

            // Creates a web intent when the user clicks on the website
            binding.websiteValue.setOnClickListener(v -> {
                //Load website
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if(info.getWebsite().startsWith("http://") || info.getWebsite().startsWith("https://")) {
                    intent.setData(Uri.parse(info.getWebsite()));
                } else {
                    intent.setData(Uri.parse("http://" + info.getWebsite()));
                }
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), R.string.error_no_app_found, Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // Error loading school info
            Toast.makeText(getContext(), R.string.error_generic, Toast.LENGTH_LONG).show();
        }

    }

    // Creates a dialog that asks the user if they would like to call or email the school depending
    // on which they click.
    public void createDialog(Boolean isPhone, SchoolInfo info) {
        if(getActivity() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            String message;
            Intent intent;
            if (isPhone) {
                // Create a dial intent
                message = getString(R.string.make_a_call, info.getSchool_name());
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + info.getPhone_number()));
            } else {
                // Create an email intent
                message = getString(R.string.send_an_email, info.getSchool_name());
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, info.getSchool_email());
            }
            builder.setMessage(message);
            builder.setPositiveButton(R.string.yes, (dialog, id) -> {
                // User clicked OK button
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), R.string.error_no_app_found, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
                // User cancelled the dialog
            });
            builder.create().show();
        }
    }
}
