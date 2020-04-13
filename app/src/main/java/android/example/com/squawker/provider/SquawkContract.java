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

import android.content.ContentValues;
import android.content.SharedPreferences;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SquawkContract {

    public static final String TABLE_NAME = "squawkcontract";
    public static final String[] DEFAULT_PROJECTION = {
        "messageId", "author", "authorKey", "message", "date"
    };

    @PrimaryKey
    public long messageId;

    @ColumnInfo
    public String author;

    @ColumnInfo
    public String authorKey;

    @ColumnInfo
    public String message;

    @ColumnInfo
    public String date;

    public SquawkContract(
        long messageId, String author, String authorKey, String message, String date) {
        this.messageId = messageId;
        this.author = author;
        this.authorKey = authorKey;
        this.message = message;
        this.date = date;
    }

    public static SquawkContract fromContentValues(ContentValues values) {
        long messageId = values.getAsLong("messageId");
        String author = values.getAsString("author");
        String authorKey = values.getAsString("authorKey");
        String message = values.getAsString("message");
        String date = values.getAsString("date");
        return new SquawkContract(messageId, author, authorKey, message, date);
    }


    // Topic keys as matching what is found in the database
    public static final String ASSER_KEY = "key_asser";
    public static final String CEZANNE_KEY = "key_cezanne";
    public static final String JLIN_KEY = "key_jlin";
    public static final String LYLA_KEY = "key_lyla";
    public static final String NIKITA_KEY = "key_nikita";
    public static final String TEST_ACCOUNT_KEY = "key_test";


    private static final String[] INSTRUCTOR_KEYS = {
            ASSER_KEY, CEZANNE_KEY, JLIN_KEY, LYLA_KEY, NIKITA_KEY
    };

    /**
     * Creates a SQLite SELECTION parameter that filters just the rows for the authors you are
     * currently following.
     */
    public static String createSelectionForCurrentFollowers(SharedPreferences preferences) {

        StringBuilder stringBuilder = new StringBuilder();
        //Automatically add the test account
        stringBuilder.append("authorKey").append(" IN  ('").append(TEST_ACCOUNT_KEY).append("'");

        for (String key : INSTRUCTOR_KEYS) {
            if (preferences.getBoolean(key, false)) {
                stringBuilder.append(",");
                stringBuilder.append("'").append(key).append("'");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}