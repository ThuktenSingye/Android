package com.example.internetconnectivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar refreshProgress; // Add this
    private TextView no_internet;
    private ImageView no_wifi;

    private BroadcastReceiver connectivityReceiver = new BroadcastReceiver() { // listen to the change in network connectivity
        @Override
        public void onReceive(Context context, Intent intent) { // trigger when there is changes in network status or match with intent filter
            // Check connectivity status and update UI
           refreshConnectivityStatus();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        no_internet = findViewById(R.id.no_internet);
        no_wifi = findViewById(R.id.no_wifi);

//        initialize swipe refresh layout
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        refreshProgress = findViewById(R.id.refresh_progress);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Show the refresh animation
                swipeRefreshLayout.setRefreshing(true);

                refreshConnectivityStatus();
            }
        });
        refreshConnectivityStatus();
    }
    private void refreshConnectivityStatus() {
        if (isConnected()) {
            // Internet is available
            // Update UI accordingly
            no_internet.setVisibility(View.GONE);
            no_wifi.setVisibility(View.GONE);
        } else {
            // No internet connectivity
            // Update UI accordingly
            no_internet.setVisibility(View.VISIBLE);
            no_wifi.setVisibility(View.VISIBLE);
        }
        // Finish the refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onResume() { // called when activity is brought into foreground
        super.onResume();
        // Register the BroadcastReceiver to listen for connectivity changes
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        // act as filter that define which broadcast event the broadcast receiver should listen to
        registerReceiver(connectivityReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the BroadcastReceiver when the activity is paused
        unregisterReceiver(connectivityReceiver);
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

}