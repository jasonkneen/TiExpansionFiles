package com.android.vending.expansion.downloader;

import org.appcelerator.titanium.TiApplication;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;


public final class R {

	
    public static final class attr {
    }
    public static final class drawable {
    	static {
        	initialize();
        }
        public static int notify_panel_notification_icon_bg;
    }
    public static final class id {
    	static {
        	initialize();
        }
        public static int appIcon;
        public static int description;
        public static int notificationLayout;
        public static int progress_bar;
        public static int progress_bar_frame;
        public static int progress_text;
        public static int time_remaining;
        public static int title;
    }
    public static final class layout {
    	static {
        	initialize();
        }
        public static int status_bar_ongoing_event_progress_bar;
    }
    public static final class string {
    	static {
        	initialize();
        }
        public static int kilobytes_per_second;
        /**  When a download completes, a notification is displayed, and this
        string is used to indicate that the download successfully completed.
        Note that such a download could have been initiated by a variety of
        applications, including (but not limited to) the browser, an email
        application, a content marketplace. 
         */
        public static int notification_download_complete;
        /**  When a download completes, a notification is displayed, and this
        string is used to indicate that the download failed.
        Note that such a download could have been initiated by a variety of
        applications, including (but not limited to) the browser, an email
        application, a content marketplace. 
         */
        public static int notification_download_failed;
        public static int state_completed;
        public static int state_connecting;
        public static int state_downloading;
        public static int state_failed;
        public static int state_failed_cancelled;
        public static int state_failed_fetching_url;
        public static int state_failed_sdcard_full;
        public static int state_failed_unlicensed;
        public static int state_fetching_url;
        public static int state_idle;
        public static int state_paused_by_request;
        public static int state_paused_network_setup_failure;
        public static int state_paused_network_unavailable;
        public static int state_paused_roaming;
        public static int state_paused_sdcard_unavailable;
        public static int state_paused_wifi_disabled;
        public static int state_paused_wifi_unavailable;
        public static int state_unknown;
        public static int time_remaining;
        public static int time_remaining_notification;
    }
    public static final class style {
    	static {
        	initialize();
        }
        public static int ButtonBackground;
        public static int NotificationText;
        public static int NotificationTextSecondary;
        public static int NotificationTextShadow;
        public static int NotificationTitle;
    }

	private static final String TAG = "com.android.vending.expansion.downloader.R";
    private static boolean initialized = false;
    
    static {
    	initialize();
    }
    
    static void initialize(){
    	if (initialized) {
    		return;
    	}
    	initialized = true;
    	Log.d(TAG, "static initializer");
		TiApplication tiApp = TiApplication.getInstance();
		Context context = tiApp.getCurrentActivity();
		String defPackage = context.getPackageName();
        Resources resources = context.getResources();
        
        drawable.notify_panel_notification_icon_bg = resources.getIdentifier("notify_panel_notification_icon_bg", "drawable", defPackage);
        
        id.appIcon = resources.getIdentifier("appIcon", "id", defPackage);
        id.description = resources.getIdentifier("description", "id", defPackage);
        id.notificationLayout = resources.getIdentifier("notificationLayout", "id", defPackage);
        id.progress_bar = resources.getIdentifier("progress_bar", "id", defPackage);
        id.progress_bar_frame = resources.getIdentifier("progress_bar_frame", "id", defPackage);
        id.progress_text = resources.getIdentifier("progress_text", "id", defPackage);
        id.time_remaining = resources.getIdentifier("time_remaining", "id", defPackage);
        id.title = resources.getIdentifier("title", "id", defPackage);
        
        layout.status_bar_ongoing_event_progress_bar = resources.getIdentifier("status_bar_ongoing_event_progress_bar", "layout", defPackage);
        
        string.kilobytes_per_second = resources.getIdentifier("kilobytes_per_second", "string", defPackage);
        string.notification_download_complete = resources.getIdentifier("notification_download_complete", "string", defPackage);
        string.notification_download_failed = resources.getIdentifier("notification_download_failed", "string", defPackage);
        string.state_completed = resources.getIdentifier("state_completed", "string", defPackage);
        string.state_connecting = resources.getIdentifier("state_connecting", "string", defPackage);
        string.state_downloading = resources.getIdentifier("state_downloading", "string", defPackage);
        string.state_failed = resources.getIdentifier("state_failed", "string", defPackage);
        string.state_failed_cancelled = resources.getIdentifier("state_failed_cancelled", "string", defPackage);
        string.state_failed_fetching_url = resources.getIdentifier("state_failed_fetching_url", "string", defPackage);
        string.state_failed_sdcard_full = resources.getIdentifier("state_failed_sdcard_full", "string", defPackage);
        string.state_failed_unlicensed = resources.getIdentifier("state_failed_unlicensed", "string", defPackage);
        string.state_fetching_url = resources.getIdentifier("state_fetching_url", "string", defPackage);
        string.state_idle = resources.getIdentifier("state_idle", "string", defPackage);
        string.state_paused_by_request = resources.getIdentifier("state_paused_by_request", "string", defPackage);
        string.state_paused_network_setup_failure = resources.getIdentifier("state_paused_network_setup_failure", "string", defPackage);
        string.state_paused_network_unavailable = resources.getIdentifier("state_paused_network_unavailable", "string", defPackage);
        string.state_paused_roaming = resources.getIdentifier("state_paused_roaming", "string", defPackage);
        string.state_paused_sdcard_unavailable = resources.getIdentifier("state_paused_sdcard_unavailable", "string", defPackage);
        string.state_paused_wifi_disabled = resources.getIdentifier("state_paused_wifi_disabled", "string", defPackage);
        string.state_paused_wifi_unavailable = resources.getIdentifier("state_paused_wifi_unavailable", "string", defPackage);
        string.state_unknown = resources.getIdentifier("state_unknown", "string", defPackage);
        string.time_remaining = resources.getIdentifier("time_remaining", "string", defPackage);
        string.time_remaining_notification = resources.getIdentifier("time_remaining_notification", "string", defPackage);
        
        style.ButtonBackground = resources.getIdentifier("ButtonBackground", "style", defPackage);
        style.NotificationText = resources.getIdentifier("NotificationText", "style", defPackage);
        style.NotificationTextSecondary = resources.getIdentifier("NotificationTextSecondary", "style", defPackage);
        style.NotificationTextShadow = resources.getIdentifier("NotificationTextShadow", "style", defPackage);
        style.NotificationTitle = resources.getIdentifier("NotificationTitle", "style", defPackage);
        
        /*
        Log.d(TAG, "notify_panel_notification_icon_bg id: " + drawable.notify_panel_notification_icon_bg);
        
        Log.d(TAG, "appIcon id: " + id.appIcon);
        Log.d(TAG, "description id: " + id.description);
        Log.d(TAG, "notificationLayout id: " + id.notificationLayout);
        Log.d(TAG, "progress_bar id: " + id.progress_bar);
        Log.d(TAG, "progress_bar_frame id: " + id.progress_bar_frame);
        Log.d(TAG, "progress_text id: " + id.progress_text);
        Log.d(TAG, "time_remaining id: " + id.time_remaining);
        Log.d(TAG, "title id: " + id.title);
        
        Log.d(TAG, "status_bar_ongoing_event_progress_bar id: " + layout.status_bar_ongoing_event_progress_bar);
        
        Log.d(TAG, "state_completed id: " + string.state_completed);
        Log.d(TAG, "state_connecting id: " + string.state_connecting);
        Log.d(TAG, "state_downloading id: " + string.state_downloading);
        Log.d(TAG, "state_failed id: " + string.state_failed);
        Log.d(TAG, "state_failed_cancelled id: " + string.state_failed_cancelled);
        Log.d(TAG, "state_failed_fetching_url id: " + string.state_failed_fetching_url);
        Log.d(TAG, "state_failed_sdcard_full id: " + string.state_failed_sdcard_full);
        Log.d(TAG, "state_failed_unlicensed id: " + string.state_failed_unlicensed);
        Log.d(TAG, "state_idle id: " + string.state_idle);
        Log.d(TAG, "state_paused_by_request id: " + string.state_paused_by_request);
        Log.d(TAG, "state_paused_network_setup_failure id: " + string.state_paused_network_setup_failure);
        Log.d(TAG, "state_paused_network_unavailable id: " + string.state_paused_network_unavailable);
        Log.d(TAG, "state_paused_roaming id: " + string.state_paused_roaming);
        Log.d(TAG, "state_paused_sdcard_unavailable id: " + string.state_paused_sdcard_unavailable);
        Log.d(TAG, "state_paused_wifi_disabled id: " + string.state_paused_wifi_disabled);
        Log.d(TAG, "state_paused_wifi_unavailable id: " + string.state_paused_wifi_unavailable);
        Log.d(TAG, "state_unknown id: " + string.state_unknown);
        Log.d(TAG, "time_remaining id: " + string.time_remaining);
        Log.d(TAG, "time_remaining_notification id: " + string.time_remaining_notification);
        
        
        Log.d(TAG, "ButtonBackground id: " + style.ButtonBackground);
        Log.d(TAG, "NotificationText id: " + style.NotificationText);
        Log.d(TAG, "NotificationTextSecondary id: " + style.NotificationTextSecondary);
        Log.d(TAG, "NotificationTextShadow id: " + style.NotificationTextShadow);
        Log.d(TAG, "NotificationTitle id: " + style.NotificationTitle);
        */
	}
}

