/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package android.example.com.squawker.fcm;

import android.util.Log;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;

/**
 * Listens for changes in the InstanceID
 */
public class SquawkFirebaseInstanceIdService extends FirebaseMessagingService {

    private static String TAG = "SquawkFirebaseInstanceIdService";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d(TAG, "new token:" + s);
        sendRegistrationToServer(s);
    }

    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // This method is blank, but if you were to build a server that stores users token
        // information, this is where you'd send the token to the server.
    }
}
