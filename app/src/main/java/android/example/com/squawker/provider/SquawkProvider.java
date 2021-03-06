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
package android.example.com.squawker.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class SquawkProvider extends ContentProvider {
    private static final String TAG = "SquawkProvider";
    public static final String AUTHORITY = "android.example.com.squawker.provider.provider";
    public static final Uri CONTENT_URI =
        Uri.parse("content://" + AUTHORITY + "/" + SquawkContract.TABLE_NAME);

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (getContext() == null) {
            throw new IllegalArgumentException("Failed to query row from uri = " + uri);
        }
        Log.d(TAG, "query uri = " + uri);
        final Cursor cursor = SquawkRepository.get(getContext()).getAuthorsWithCursor(selectionArgs);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.dir/" + AUTHORITY + "." + SquawkContract.TABLE_NAME;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (getContext() == null) {
            throw new IllegalArgumentException("Failed to insert row from uri = " + uri);
        }
        SquawkContract contract = SquawkContract.fromContentValues(values);
        SquawkRepository.get(getContext()).addSquawkContract(contract);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, contract.messageId);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
        @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
        @Nullable String[] selectionArgs) {
        return 0;
    }
}