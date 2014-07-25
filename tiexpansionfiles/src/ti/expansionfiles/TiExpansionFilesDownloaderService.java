/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ti.expansionfiles;

import org.appcelerator.titanium.TiApplication;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

/**
 * This class demonstrates the minimal client implementation of the
 * DownloaderService from the Downloader library.
 */
public class TiExpansionFilesDownloaderService extends DownloaderService {
	
	private static String TAG = "DownloaderService";
	
    /**
     * This public key comes from your Android Market publisher account, and it
     * used by the LVL to validate responses from Market on your behalf.
     */
    @Override
    public String getPublicKey() {
    	String base64PublicKey = TiApplication.getInstance().getAppProperties().getString("ti.android.licensing.key", "");
    	Log.d(TAG, "public key: " + base64PublicKey);
    	return base64PublicKey;
    }

    /**
     * This is used by the preference obfuscater to make sure that your
     * obfuscated preferences are different than the ones used by other
     * applications.
     */
    @Override
    public byte[] getSALT() {
    	String saltStr = TiApplication.getInstance().getAppProperties().getString("ti.android.licensing.salt", "");
    	Log.d(TAG, "salt: " + saltStr);
    	byte[] salt = null;
    	try {
    		JSONArray array = new JSONArray(saltStr);
    		salt = new byte[array.length()];
    		for (int i = 0; i < array.length(); i++) {
    			salt[i] = (byte)array.getInt(i);
    		}
    	}
    	catch(JSONException e){
    		Log.e(TAG, "Error: didn't find a valid ti.android.licensing.salt property in tiapp.xml");
    		e.printStackTrace();
    	}
    	return salt;
    }

    /**
     * Fill this in with the class name for your alarm receiver. We do this
     * because receivers must be unique across all of Android (it's a good idea
     * to make sure that your receiver is in your unique package)
     */
    @Override
    public String getAlarmReceiverClassName() {
        return TiExpansionFilesAlarmReceiver.class.getName();
    }

}
