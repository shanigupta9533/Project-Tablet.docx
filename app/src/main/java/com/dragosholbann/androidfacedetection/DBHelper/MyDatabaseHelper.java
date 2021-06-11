package com.dragosholbann.androidfacedetection.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Madads.db";

    private static final int DATABASE_VERSION = 4;

    // Database creation sql statement
    private static final String DATABASE_CREATE =
            "create table Videos(_id integer primary key,name text not null,path text not null);";

    private static final String CREATE_CAB_LOGIN_DETAILS =
            "create table  tbl_cab_login_details( cab_login_id	INTEGER primary key, cab_tab_id	INTEGER, cab_details_id	INTEGER," +
                    "cab_login_date	TEXT,       cab_login_start_time	TEXT,  cab_login_end_time	TEXT," +
                    "cab_login_duration	TEXT,   cab_login_ip_address	TEXT," +
                    "FOREIGN KEY(cab_tab_id) REFERENCES tbl_cab_tab(cab_tab_id)," +
                    "FOREIGN KEY(cab_details_id) REFERENCES tbl_cab_details(cab_details_id)" +
                    ")";

    private static final String CREATE_CAB_TAB =
            "create table  tbl_cab_tab(cab_tab_id	INTEGER primary key,cab_details_id	INTEGER , cab_tab_email	TEXT," +
                    "cab_tab_driver_name	TEXT ,     cab_tab_driver_mobile	TEXT,   is_verified	TEXT," +
                    "cab_tab_added	TEXT,   cab_tab_modified	TEXT," +
                    "FOREIGN KEY(cab_details_id) REFERENCES tbl_cab_details(cab_details_id)" +
                    ")";

    private static final String CREATE_VIDEO_LIST =
            "create table  tbl_cab_video_list(video_list_id	INTEGER primary key,customer_id	INTEGER, video_name	TEXT," +
                    "video_url	TEXT ,     video_duration	TEXT,   video_format	TEXT," +
                    "video_type	TEXT,   video_added	TEXT, video_size	TEXT)";

    private static final String CREATE_COUNTRY_LIST =
            "create table  tbl_country_list(country_id INTEGER primary key, country_name	TEXT," +
                    "country_phone_code	TEXT , country_used	TEXT,   country_added	TEXT)";

    private static final String CREATE_STATE_LIST =
            "create table  tbl_state_list(state_id INTEGER primary key, country_id	INTEGER," +
                    "state_name	TEXT , state_used	TEXT,   state_added	TEXT)";

    private static final String CREATE_PINCODE_LIST =
            "create table  tbl_pincode_list(pincode_id INTEGER primary key, city_id INTEGER,state_id INTEGER,country_id INTEGER, pincode INTEGER," +
                    "post_office	TEXT ,district TEXT, pincode_used	TEXT,   pincode_added	TEXT)";

    private static final String CREATE_CITY_LIST =
            "create table  tbl_city_list(city_id INTEGER primary key, state_id INTEGER," +
                    "country_id TEXT, city_name	TEXT ,non_prime_amount TEXT, prime_amount TEXT, city_used	TEXT,   city_added	TEXT)";

        /*," +
                    "FOREIGN KEY(customer_id) REFERENCES tbl_cab_details(cab_details_id))";*/


    private static final String CREATE_CAB_DETAILS =
            "create table tbl_cab_details(cab_details_id INTEGER primary key," +
                    "customer_id INTEGER, " +
                    "cab_owner_name	TEXT, " +
                    "cab_owner_mobile	TEXT," +
                    "cab_owner_id_proof_name	    TEXT,           " +
                    "cab_owner_id_proof_number	TEXT," +
                    "cab_owner_address	TEXT," +
                    "cab_driver_name	    TEXT," +
                    "cab_driver_mobile_no	TEXT,       " +
                    "cab_driver_license_no	    TEXT,           " +
                    "cab_vehicle_type	    TEXT," +
                    "cab_registration_number TEXT,      " +
                    "cab_registration_country	TEXT,       " +
                    "cab_registration_state	TEXT," +
                    "cab_registration_city  TEXT,       " +
                    "cab_registration_date	    TEXT,       " +
                    "cab_insurance_valid    TEXT,       " +
                    "cab_associated_company	TEXT,       " +
                    "cab_vehicle_model	TEXT," +
                    "cab_vehicle_make       TEXT,       " +
                    "cab_details_added	        TEXT,       " +
                    "cab_details_modified	TEXT, " +
                    "cab_owner_bank_name	    TEXT,           " +
                    "cab_owner_bank_ifsc	    TEXT,           " +
                    "cab_owner_bank_account_number	    TEXT,           " +
                    "cab_owner_bank_account_name	    TEXT);";

    private static final String CREATE_CAMPAIGN_LIST =
            "create table  tbl_campaign_list( campaign_id	INTEGER, customer_id	INTEGER, video_list_id	INTEGER , audience_id	INTEGER," +
                    "budget_id	INTEGER , budget_type TEXT, budget_start_date TEXT,budget_end_date TEXT,  " +
                    "date_diff_start_end TEXT, daily_budget_amount_lifetime INTEGER, campaign_name	TEXT,   campaign_type	TEXT," +
                    "campaign_is_prime	TEXT,   campaign_added	TEXT, campaign_ip_address	TEXT , " +
                    "    status	TEXT,   approval	TEXT,   campaign_modified	TEXT, budget_amount_daily INTEGER, budget_amount_lifetime INTEGER," +
                    "   video_duration TEXT, video_duration_in_seconds INTEGER, count INTEGER)";

    private static final String CREATE_CAMPAIGN_LIST_FILLER =
            "create table  tbl_campaign_list_filler( campaign_id	INTEGER, customer_id	INTEGER, video_list_id	INTEGER , audience_id	INTEGER," +
                    "budget_id	INTEGER , budget_type TEXT, budget_start_date TEXT,budget_end_date TEXT,  " +
                    "date_diff_start_end TEXT, daily_budget_amount_lifetime INTEGER, campaign_name	TEXT,   campaign_type	TEXT," +
                    "campaign_is_prime	TEXT,   campaign_added	TEXT, campaign_ip_address	TEXT , " +
                    "    status	TEXT,   approval	TEXT,   campaign_modified	TEXT, budget_amount_daily INTEGER, budget_amount_lifetime INTEGER," +
                    "   video_duration TEXT, video_duration_in_seconds INTEGER, count INTEGER)";

    private static final String CREATE_CAMPAIGN_LIST_OFFER =
            "create table  tbl_campaign_list_offer( campaign_id	INTEGER, customer_id	INTEGER, customer_id_offer	INTEGER , offer_id	INTEGER ,audience_id	INTEGER," +
                    "budget_id	INTEGER , budget_type TEXT, budget_start_date TEXT,budget_end_date TEXT,  " +
                    "date_diff_start_end TEXT, daily_budget_amount_lifetime INTEGER, campaign_name	TEXT,   campaign_type	TEXT," +
                    "campaign_is_prime	TEXT,   campaign_added	TEXT, campaign_ip_address	TEXT , " +
                    "    status	TEXT,   approval	TEXT,   campaign_modified	TEXT, budget_amount_daily INTEGER, budget_amount_lifetime INTEGER," +
                    "    offer_title	TEXT,   offer_description	TEXT,   offer_code	TEXT, offer_added TEXT," +
                    "   offer_duration TEXT, offer_duration_in_seconds INTEGER, offer_img	TEXT,   offer_expiry_date	TEXT," +
                    "count INTEGER, getOfferlogCount INTEGER)";


    private static final String CREATE_VIDEO_LOG =
            "create table  tbl_video_log(video_list_id	INTEGER, campaign_id INTEGER, audience_id	INTEGER , " +
                    "cab_tab_id	INTEGER, video_country	TEXT, video_state TEXT, video_city	TEXT,   " +
                    "video_pincode	TEXT, video_gender	TEXT,   video_age_group	TEXT, video_review	TEXT , " +
                    "video_played_datetime	TEXT,   video_played_ip_address	TEXT,   offer_consumed_id	INTEGER, video_lat TEXT, video_lng TEXT, " +
                    "video_amount_charged TEXT, cab_details_id INTEGER, duration_played INTEGER)";


    private static final String CREATE_OFFER_LOG =
            "create table  tbl_offer_log( offer_id INTEGER, campaign_id INTEGER, audience_id	INTEGER , " +
                    "cab_tab_id	INTEGER, cab_details_id INTEGER, duration_played INTEGER, offer_consumed_id	INTEGER," +
                    "offer_country	TEXT, offer_state TEXT, offer_city	TEXT,offer_pincode	TEXT, offer_lat TEXT, offer_lng TEXT," +
                    " offer_gender	TEXT,   offer_age_group	TEXT, offer_amount_charged TEXT," +
                    "offer_review	TEXT , offer_played_ip_address	TEXT)";

    /* video_list_id    ==>>    video_list_name
    * campaign_id	    ==>>    campaign_name
    * audience_id	    ==>>    audience_name
    *
    *
    *
    *
    *
    * */
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_CAB_DETAILS);
        database.execSQL(CREATE_CAB_TAB);
        database.execSQL(CREATE_VIDEO_LIST);
        database.execSQL(CREATE_CAMPAIGN_LIST);
        database.execSQL(CREATE_CAMPAIGN_LIST_FILLER);
        database.execSQL(CREATE_VIDEO_LOG);
        database.execSQL(CREATE_COUNTRY_LIST);
        database.execSQL(CREATE_STATE_LIST);
        database.execSQL(CREATE_PINCODE_LIST);
        database.execSQL(CREATE_CITY_LIST);
        database.execSQL(CREATE_CAMPAIGN_LIST_OFFER);
        database.execSQL(CREATE_OFFER_LOG);
        // database.execSQL(CREATE_CAB_LOGIN_DETAILS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.e(MyDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS tbl_cab_details");
        database.execSQL("DROP TABLE IF EXISTS tbl_cab_tab");
        database.execSQL("DROP TABLE IF EXISTS tbl_cab_video_list");
        database.execSQL("DROP TABLE IF EXISTS tbl_campaign_list");
        database.execSQL("DROP TABLE IF EXISTS tbl_campaign_list_filler");
        database.execSQL("DROP TABLE IF EXISTS tbl_video_log");
        database.execSQL("DROP TABLE IF EXISTS tbl_country_list");
        database.execSQL("DROP TABLE IF EXISTS tbl_state_list");
        database.execSQL("DROP TABLE IF EXISTS tbl_pincode_list");
        database.execSQL("DROP TABLE IF EXISTS tbl_city_list");
        database.execSQL("DROP TABLE IF EXISTS tbl_campaign_list_offer");
        database.execSQL("DROP TABLE IF EXISTS tbl_offer_log");

        try {
            onCreate(database);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

}
