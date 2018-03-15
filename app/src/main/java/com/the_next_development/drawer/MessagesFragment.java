package com.the_next_development.drawer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import static android.content.ContentValues.TAG;


public class MessagesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Messages");
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Create channel to show notifications.
        String channelId  = getString(R.string.default_notification_channel_id);
        String channelName = getString(R.string.default_notification_channel_name);
        NotificationManager notificationManager =
                getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_LOW));
    }

    // If a notification message is tapped, any data accompanying the notification
    // message is available in the intent extras. In this sample the launcher
    // intent is fired when the notification is tapped, so any accompanying data would
    // be handled here. If you want a different intent fired, set the click_action
    // field of the notification message to the desired intent. The launcher intent
    // is used when no click_action is specified.
    //
    // Handle possible data accompanying notification message.
    // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
        for (String key : getIntent().getExtras().keySet()) {
            Object value = getIntent().getExtras().get(key);
            Log.d(TAG, "Key: " + key + " Value: " + value);
        }
    }
    // [END handle_data_extras]

    Button subscribeButton = findViewById(R.id.subscribeButton);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // [START subscribe_topics]
            FirebaseMessaging.getInstance().subscribeToTopic("news");
            // [END subscribe_topics]

            // Log and toast
            String msg = getString(R.string.msg_subscribed);
            Log.d(TAG, msg);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    });

    Button logTokenButton = findViewById(R.id.logTokenButton);
        logTokenButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Get token
            String token = FirebaseInstanceId.getInstance().getToken();

            // Log and toast
            String msg = getString(R.string.msg_token_fmt, token);
            Log.d(TAG, msg);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    });
}


}