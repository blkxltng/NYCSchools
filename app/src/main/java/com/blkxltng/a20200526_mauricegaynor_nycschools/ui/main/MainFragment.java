package com.blkxltng.a20200526_mauricegaynor_nycschools.ui.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.blkxltng.a20200526_mauricegaynor_nycschools.R;
import com.blkxltng.a20200526_mauricegaynor_nycschools.databinding.FragmentMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainViewModel viewModel;
    private MainEpoxyController mainEpoxyController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Assign the ViewModel to the Fragment
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.setMainViewModel(viewModel);
        binding.executePendingBindings();
        setupObservers();

        // Get the list of schools
        if (viewModel.schoolInfoList.getValue() == null) {
            // Check if the device has an internet connection
            checkConnected();
        }

    }

    public void setupObservers() {
        viewModel.schoolInfoList.observe(getViewLifecycleOwner(), schoolInfos -> {
            // Use Epoxy to show the list of schools that were found
            mainEpoxyController = new MainEpoxyController();
            mainEpoxyController.setData(schoolInfos);
            binding.recyclerView.setController(mainEpoxyController);
        });

        viewModel.clickedSchool.observe(getViewLifecycleOwner(), info -> {
            // Used to grab the 2012 SAT scores for the selected school
            viewModel.getScores(info.getDbn());
        });

        viewModel.schoolScores.observe(getViewLifecycleOwner(), schoolScores -> {
            // Once the scores have been retrieved, show all the info in the Details Fragment
            NavHostFragment.findNavController(this).navigate(
                    MainFragmentDirections.actionMainFragmentToDetailsFragment(schoolScores.first)
                            .setScores(schoolScores.second));
        });

        // Used to handle errors that come up when running things in the ViewModel
        viewModel.errorCode.observe(getViewLifecycleOwner(), error -> {
            String message;
            switch (error) {
                case ERROR_SCHOOL:
                    message = getString(R.string.error_schools);
                case ERROR_SCORES:
                    message = getString(R.string.error_scores);
                default:
                    message = getString(R.string.error_generic);
            }
            // Show the error
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        });
    }

    // Used to check if the device is connected
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    // If the device is connected, continue with getting the list of schools. If not, then show a
    // snackbar asking the user to connect to the internet and initate the download again.
    private void checkConnected() {
        if(isNetworkConnected()) {
            viewModel.loadSchools();
        } else {
            showSnackbar();
        }
    }

    private void showSnackbar() {
        Snackbar connSnackbar = Snackbar.make(binding.getRoot(), R.string.error_no_connection, Snackbar.LENGTH_INDEFINITE);
        connSnackbar.setAction(R.string.retry, v -> {
            connSnackbar.dismiss();
            checkConnected();
        });
        connSnackbar.show();
    }
}
