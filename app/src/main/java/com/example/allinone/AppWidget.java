package com.example.allinone;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

// Implementation of App Widget functionality
public class AppWidget extends AppWidgetProvider {
    @Override
    // Create an Intent to launch fragment
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int appwidgetId:appWidgetIds){
            Intent i = new Intent(context,MainActivity.class);
            // Attaching a Pending Intent to trigger it when application is not alive
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,0);
            // Get the layout for the App Widget and attach an on-click listener to the button
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
            remoteViews.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appwidgetId,remoteViews);
        }
    }
}
