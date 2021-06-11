package com.dragosholbann.androidfacedetection.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.dragosholbann.androidfacedetection.ApiModels.City;
import com.dragosholbann.androidfacedetection.ApiModels.Country;
import com.dragosholbann.androidfacedetection.ApiModels.Pincode;
import com.dragosholbann.androidfacedetection.ApiModels.State;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CabDetails;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Cabtabdetail;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CampaignlistItem;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CampaignlistOfferItem;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.OfferData;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.OfferLogItem;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.VideoLogItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import javax.sql.StatementEvent;

import static android.content.ContentValues.TAG;

public class MyDB{

    private SQLiteDatabase database;
    private final static String VIDEO_TABLE="Videos"; // name of table
    private final static String VIDEO_ID="_id"; // id value for employee
    private final static String VIDEO_NAME="name";  // name of employee
    private final static String VIDEO_PATH="path";  // name of employee

    //TABLW => tbl_cab_details
    private final static String Tbl_cab_details = "tbl_cab_details";
    private final static String Cab_details_id = "cab_details_id"; // <= PK
    private final static String Customer_id = "customer_id";
    private final static String Cab_owner_name = "cab_owner_name";
    private final static String Cab_owner_mobile = "cab_owner_mobile";
    private final static String Cab_registration_number = "cab_registration_number";
    private final static String Cab_registration_country = "cab_registration_country";
    private final static String Cab_registration_state = "cab_registration_state";
    private final static String Cab_registration_city = "cab_registration_city";
    private final static String Cab_registration_date = "cab_registration_date";
    private final static String Cab_owner_address = "cab_owner_address";
    private final static String Cab_insurance_valid = "cab_insurance_valid";
    private final static String Cab_owner_id_proof_name = "cab_owner_id_proof_name";
    private final static String Cab_owner_id_proof_number = "cab_owner_id_proof_number";
    private final static String Cab_associated_company = "cab_associated_company";
    private final static String Cab_driver_license_no = "cab_driver_license_no";
    private final static String Cab_vehicle_model = "cab_vehicle_model";
    private final static String Cab_vehicle_make = "cab_vehicle_make";
    private final static String Cab_owner_bank_name = "cab_owner_bank_name";
    private final static String Cab_owner_bank_ifsc = "cab_owner_bank_ifsc";
    private final static String Cab_owner_bank_account_number = "cab_owner_bank_account_number";
    private final static String Cab_owner_bank_account_name = "cab_owner_bank_account_name";
    private final static String Cab_driver_name = "cab_driver_name";
    private final static String Cab_driver_mobile_no = "cab_driver_mobile_no";
    private final static String Cab_details_added = "cab_details_added";
    private final static String Cab_details_modified = "cab_details_modified";
    private final static String Cab_vehicle_type = "cab_vehicle_type";

    //TABLW =>  tbl_cab_tab
    private final static String Tbl_cab_tab = "tbl_cab_tab";
    private final static String Cab_tab_id = "cab_tab_id"; // <= PK
    private final static String Cab_tab_email = "cab_tab_email";
    private final static String Cab_tab_password = "cab_tab_password";
    //FK => tbl_cab_details         private final static String cab_details_id = "cab_details_id";
    private final static String Cab_tab_driver_mobile = "cab_tab_driver_mobile";
    private final static String Is_verified = "is_verified";
    private final static String Cab_tab_added = "cab_tab_added";
    private final static String Cab_tab_modified = "cab_tab_modified";
    private final static String Cab_tab_driver_name = "cab_tab_driver_name";


    //TABLW => tbl_cab_login_details
    private final static String Tbl_cab_login_details = "tbl_cab_login_details";
    private final static String Cab_login_id = "cab_login_id"; // <= PK
    //FK => tbl_cab_tab         private final static String cab_tab_id = "cab_tab_id";
    //FK => tbl_cab_details         private final static String cab_details_id = "cab_details_id";
    private final static String Cab_login_date = "cab_login_date";
    private final static String Cab_login_start_time = "cab_login_start_time";
    private final static String Cab_login_end_time = "cab_login_end_time";
    private final static String Cab_login_duration = "cab_login_duration";
    private final static String Cab_login_ip_address = "cab_login_ip_address";


    //TABLW => tbl_cab_video_list
    private final static String Tbl_cab_video_list = "tbl_cab_video_list";
    private final static String Video_list_id = "video_list_id"; // <= PK
    //FK =>private final static String Customer_id = "customer_id";
    private final static String Video_name = "video_name";
    private final static String Video_url = "video_url";
    private final static String Video_duration = "video_duration";
    private final static String Video_format = "video_format";
    private final static String Video_type = "video_type";
    private final static String Video_added = "video_added";
    private final static String Video_size = "video_size";

    /**
     *
     * @param context
     */
    public MyDB(Context context){
         MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public boolean deleteCabTabDetails() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_cab_details );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }

    public long createRecordsCabTabDetails(List<CabDetails> cabDetails){
        deleteCabTabDetails();
        ContentValues values = new ContentValues();
        values.put( Cab_details_id,cabDetails.get(0).getCabDetailsId());
        values.put( Customer_id,                 cabDetails.get(0).getCustomerId());
        values.put( Cab_owner_name,              cabDetails.get(0).getCabOwnerName());
        values.put( Cab_owner_mobile,              cabDetails.get(0).getCabOwnerMobile());
        values.put( Cab_owner_id_proof_name,                 cabDetails.get(0).getCabOwnerIdProofName());
        values.put( Cab_owner_id_proof_number,              cabDetails.get(0).getCabOwnerIdProofNumber());
        values.put( Cab_owner_address,              cabDetails.get(0).getCabOwnerAddress());
        values.put( Cab_driver_name,                 cabDetails.get(0).getCabDriverName());
        values.put( Cab_driver_mobile_no,              cabDetails.get(0).getCabDriverMobileNo());
        values.put( Cab_driver_license_no,              cabDetails.get(0).getCabDriverLicenseNo());
        values.put( Cab_vehicle_type,                 cabDetails.get(0).getCabVehicleType());
        values.put( Cab_registration_number,              cabDetails.get(0).getCabRegistrationNumber());
        values.put( Cab_registration_country,              cabDetails.get(0).getCabRegistrationCountry());
        values.put( Cab_registration_state,                 cabDetails.get(0).getCabRegistrationState());
        values.put( Cab_registration_city,              cabDetails.get(0).getCabRegistrationCity());
        values.put( Cab_registration_date,              cabDetails.get(0).getCabRegistrationDate());
        values.put( Cab_insurance_valid,                 cabDetails.get(0).getCabInsuranceValid());
        values.put( Cab_associated_company,              cabDetails.get(0).getCabAssociatedCompany());
        values.put( Cab_vehicle_model,              cabDetails.get(0).getCabVehicleModel());
        values.put( Cab_vehicle_make,                 cabDetails.get(0).getCabVehicleMake());
        values.put( Cab_details_added,              cabDetails.get(0).getCabDetailsAdded());
        values.put( Cab_details_modified,                 cabDetails.get(0).getCabDetailsModified());
        values.put( Cab_owner_bank_name,              cabDetails.get(0).getCabOwnerBankAccountName());
        values.put( Cab_owner_bank_ifsc,              cabDetails.get(0).getCabOwnerBankIfsc());
        values.put( Cab_owner_bank_account_number,                 cabDetails.get(0).getCabOwnerBankAccountName());
        values.put( Cab_owner_bank_account_name,              cabDetails.get(0).getCabOwnerBankAccountName());
        long l= 0;
        try {
                 l =  database.insert(Tbl_cab_details, null, values);
        }
        catch (Exception e) {
            Log.e(TAG,"Create_Records_Cab_Tab_Details SQLException = "+e);
            return l;
        }
            return l;
        }

    public Integer  getCabId() {
        int a = 0;

        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_cab_tab;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));

                a = (cursor.getInt(0));

            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting cityList "+ex.toString());
            a = 0;
            return  a;
        }
        return a;
    }

    public boolean deleteCabTab() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_cab_tab );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }

    public long createRecordsCabTab(List<Cabtabdetail> cabtabdetails){
        deleteCabTab();
        database.beginTransaction();
        ContentValues values = new ContentValues();
        values.put( Cab_tab_id,cabtabdetails.get(0).getCabTabId());
        values.put( Cab_details_id,cabtabdetails.get(0).getCabDetailsId());
        values.put( Cab_tab_email,cabtabdetails.get(0).getCabTabEmail());
        values.put( Cab_tab_driver_name,         cabtabdetails.get(0).getCabTabDriverName());
        values.put( Cab_tab_driver_mobile,    cabtabdetails.get(0).getCabTabDriverMobile());
        values.put( Is_verified,             cabtabdetails.get(0).getIsVerified());
        values.put( Cab_tab_added,           cabtabdetails.get(0).getCabTabAdded());
        values.put( Cab_tab_modified, cabtabdetails.get(0).getCabTabModified());

        long l= 0;
        try {
            l =  database.insert(Tbl_cab_tab, null, values);
            database.setTransactionSuccessful();
            database.endTransaction();
        }
        catch (SQLiteException e)
        {
            return 1;
            //Log.e(TAG,"Create_Records_Cab_Tab SQLException ___________ = "+e);

        }
        finally {
            return 1;
        }

    }


    public long createRecordsVideoList(int video_list_id, int customer_id, String video_name,
                                    String video_url, String video_duration, String video_format, String video_type,String video_added,String video_size)
    {
        database.beginTransaction();
        ContentValues values = new ContentValues();
        values.put( Video_list_id,              video_list_id);
        values.put( Customer_id,          customer_id);
        values.put( Video_name,           video_name);
        values.put( Video_url,         video_url);
        values.put( Video_duration,    video_duration);
        values.put( Video_format,             video_format);
        values.put( Video_type,           video_type);
        values.put( Video_added,        video_added);
        values.put( Video_size,        video_size);

        long l= 0;
        try {

            l =  database.insert(Tbl_cab_video_list, null, values);
            database.setTransactionSuccessful();
            database.endTransaction();
        }
        catch (SQLiteException e)
        {

            Log.e(TAG,"Create_Records_Cab_Tab SQLException ___________ = "+e);
            return 1;
        }
        finally {
            return 1;
        }


    }

    //TABLW => tbl_campaign_list
    private final static String Tbl_campaign_list = "tbl_campaign_list";
    //private final static String Video_list_id = "video_list_id"; // <= PK
    private final static String Campaign_id = "campaign_id";
    //FK =>private final static String Customer_id = "customer_id";
    private final static String Audience_id = "audience_id";
    private final static String Budget_id = "budget_id";
    private final static String Campaign_name = "campaign_name";
    private final static String Campaign_type = "campaign_type";
    private final static String Campaign_is_prime = "campaign_is_prime";
    private final static String Campaign_added = "campaign_added";
    private final static String Campaign_ip_address = "campaign_ip_address";
    private final static String Status = "status";
    private final static String Approval = "approval";
    private final static String Campaign_modified = "campaign_modified";
    private final static String Budget_start_date = "budget_start_date";
    private final static String Budget_end_date = "budget_end_date";
    private final static String Budget_type = "budget_type";
    private final static String Budget_amount_lifetime = "budget_amount_lifetime";
    private final static String Budget_amount_daily = "budget_amount_daily";
    private final static String Count = "count";
    private final static String Date_Diff_Start_End = "date_diff_start_end";
    private final static String Daily_budget_amount_lifetime = "daily_budget_amount_lifetime";
    private final static String Video_duration_in_seconds = "video_duration_in_seconds";
    private final static String Tbl_Country = "tbl_country_list";
    private final static String Tbl_City = "tbl_city_list";
    private final static String Tbl_State = "tbl_state_list";
    private final static String Tbl_Pincode = "tbl_pincode_list";




    public boolean createCampaignList(ArrayList<CampaignlistItem>  campaignlistItemsFiller) {
        try {
            database.beginTransaction();
            if (campaignlistItemsFiller != null) {
                for (int i = 0; i < campaignlistItemsFiller.size(); i++) {
                    // Log.d("count", String.valueOf(i));
                    if (campaignlistItemsFiller.get(i) != null) {
                        ContentValues values = new ContentValues();
                        values.put(Campaign_id, campaignlistItemsFiller.get(i).getCampaignId());
                        values.put(Customer_id, campaignlistItemsFiller.get(i).getCustomerId());
                        values.put(Video_list_id, campaignlistItemsFiller.get(i).getVideoListId());
                        values.put(Audience_id, campaignlistItemsFiller.get(i).getAudienceId());
                        values.put(Budget_id, campaignlistItemsFiller.get(i).getBudgetId());
                        values.put(Budget_type, campaignlistItemsFiller.get(i).getBudgetType());
                        values.put(Budget_start_date, campaignlistItemsFiller.get(i).getBudgetStartDate());
                        values.put(Budget_end_date, campaignlistItemsFiller.get(i).getBudgetEndDate());
                        values.put(Date_Diff_Start_End, campaignlistItemsFiller.get(i).getDatediffStartdateandEnddate());
                        values.put(Daily_budget_amount_lifetime, campaignlistItemsFiller.get(i).getDailyBudgetAmountLifetime());
                        values.put(Campaign_name, campaignlistItemsFiller.get(i).getCampaignName());
                        values.put(Campaign_type, campaignlistItemsFiller.get(i).getCampaignType());
                        values.put(Campaign_is_prime, campaignlistItemsFiller.get(i).getCampaignIsPrime());
                        values.put(Campaign_added, campaignlistItemsFiller.get(i).getCampaignAdded().toString());
                        values.put(Campaign_ip_address, campaignlistItemsFiller.get(i).getCampaignIpAddress());
                        values.put(Status, campaignlistItemsFiller.get(i).getStatus());
                        values.put(Approval, campaignlistItemsFiller.get(i).getApproval());
                        values.put(Campaign_modified, campaignlistItemsFiller.get(i).getCampaignModified());
                        values.put(Budget_amount_daily, campaignlistItemsFiller.get(i).getBudgetAmountDaily());
                        values.put(Budget_amount_lifetime, campaignlistItemsFiller.get(i).getBudgetAmountLifetime());
                        values.put(Video_duration, campaignlistItemsFiller.get(i).getCampaignId());
                        values.put(Video_duration_in_seconds, campaignlistItemsFiller.get(i).getVideoDurationInSeconds());
                        values.put(Count, campaignlistItemsFiller.get(i).getCount());

                        database.insert(Tbl_campaign_list, null, values);

                    }
                }
            }
            database.setTransactionSuccessful();
            database.endTransaction();

            Log.e(TAG, "createCampaignList SUCCESS ");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "createCampaignList ERROR " + e);
            return false;
        }
    }




    //TABLW => tbl_campaign_list
    private final static String Tbl_campaign_list_offer = "tbl_campaign_list_offer";
    //private final static String Video_list_id = "video_list_id"; // <= PK
    //private final static String Campaign_id = "campaign_id";
    private final static String Customer_id_offer = "customer_id_offer";
    //FK =>private final static String Customer_id = "customer_id";
    private final static String Offer_id = "offer_id";
    //private final static String Audience_id = "audience_id";
    //private final static String Budget_id = "budget_id";
    //private final static String Campaign_name = "campaign_name";
    //private final static String Campaign_type = "campaign_type";
    //private final static String Campaign_is_prime = "campaign_is_prime";
    //private final static String Campaign_added = "campaign_added";
    //private final static String Campaign_ip_address = "campaign_ip_address";
    //private final static String Status = "status";
    //private final static String Approval = "approval";
    //private final static String Campaign_modified = "campaign_modified";
    //private final static String Budget_start_date = "budget_start_date";
    //private final static String Budget_end_date = "budget_end_date";
    //private final static String Budget_type = "budget_type";
    //private final static String Budget_amount_lifetime = "budget_amount_lifetime";
    //private final static String Budget_amount_daily = "budget_amount_daily";
    //private final static String Count = "count";
    //private final static String Date_Diff_Start_End = "date_diff_start_end";
    //private final static String Daily_budget_amount_lifetime = "daily_budget_amount_lifetime";
    private final static String Offer_title = "offer_title";
    private final static String Offer_description = "Offer_description";
    private final static String Offer_code = "Offer_code";
    private final static String Offer_added = "Offer_added";
    private final static String Offer_duration = "Offer_duration";
    private final static String Offer_img = "offer_img";
    private final static String Offer_expiry_date = "offer_expiry_date";
    private final static String Offer_duration_in_seconds = "offer_duration_in_seconds";
    //private final static String Count = "count";
    private final static String GetOfferlogCount = "getOfferlogCount";



    public boolean createCampaignListOffer(ArrayList<CampaignlistOfferItem>  campaignlistItemsFiller) {
        try {
            database.beginTransaction();
            if (campaignlistItemsFiller != null) {
                for (int i = 0; i < campaignlistItemsFiller.size(); i++) {
                    // Log.d("count", String.valueOf(i));
                    if (campaignlistItemsFiller.get(i) != null) {
                        ContentValues values = new ContentValues();
                        values.put(Campaign_id, campaignlistItemsFiller.get(i).getCampaignId());
                        values.put(Customer_id, campaignlistItemsFiller.get(i).getCustomerId());
                        values.put(Customer_id_offer, campaignlistItemsFiller.get(i).getOfferData().getCustomerIdOffer());
                        values.put(Offer_id, campaignlistItemsFiller.get(i).getOfferId());
                        values.put(Audience_id, campaignlistItemsFiller.get(i).getAudienceId());
                        values.put(Budget_id, campaignlistItemsFiller.get(i).getBudgetId());
                        values.put(Budget_type, campaignlistItemsFiller.get(i).getBudgetType());
                        values.put(Budget_start_date, campaignlistItemsFiller.get(i).getBudgetStartDate());
                        values.put(Budget_end_date, campaignlistItemsFiller.get(i).getBudgetEndDate());
                        values.put(Date_Diff_Start_End, campaignlistItemsFiller.get(i).getDatediffStartdateandEnddate());
                        values.put(Daily_budget_amount_lifetime, campaignlistItemsFiller.get(i).getDailyBudgetAmountLifetime());
                        values.put(Campaign_name, campaignlistItemsFiller.get(i).getCampaignName());
                        values.put(Campaign_type, campaignlistItemsFiller.get(i).getCampaignType());
                        values.put(Campaign_is_prime, campaignlistItemsFiller.get(i).getCampaignIsPrime());
                        values.put(Campaign_added, campaignlistItemsFiller.get(i).getCampaignAdded().toString());
                        values.put(Campaign_ip_address, campaignlistItemsFiller.get(i).getCampaignIpAddress());
                        values.put(Status, campaignlistItemsFiller.get(i).getStatus());
                        values.put(Approval, campaignlistItemsFiller.get(i).getApproval());
                        values.put(Campaign_modified, campaignlistItemsFiller.get(i).getCampaignModified());
                        values.put(Budget_amount_daily, campaignlistItemsFiller.get(i).getBudgetAmountDaily());
                        values.put(Budget_amount_lifetime, campaignlistItemsFiller.get(i).getBudgetAmountLifetime());
                        values.put(Offer_title, campaignlistItemsFiller.get(i).getOfferData().getOfferTitle());
                        values.put(Offer_description, campaignlistItemsFiller.get(i).getOfferData().getOfferDescription());
                        values.put(Offer_code, campaignlistItemsFiller.get(i).getOfferData().getOfferCode());
                        values.put(Offer_added, campaignlistItemsFiller.get(i).getOfferData().getOfferAdded());
                        values.put(Offer_duration, campaignlistItemsFiller.get(i).getOfferData().getOfferDuration());
                        values.put(Offer_duration_in_seconds, campaignlistItemsFiller.get(i).getOfferDurationInSeconds());
                        values.put(Offer_img, campaignlistItemsFiller.get(i).getOfferData().getOfferImg());
                        values.put(Offer_expiry_date, campaignlistItemsFiller.get(i).getOfferData().getOfferExpiryDate());
                        values.put(Count, campaignlistItemsFiller.get(i).getCount());
                        values.put(GetOfferlogCount, campaignlistItemsFiller.get(i).getGetOfferlogCount());


                        database.insert(Tbl_campaign_list_offer, null, values);

                    }
                }
            }
            database.setTransactionSuccessful();
            database.endTransaction();

            Log.e(TAG, "createCampaignListOFFER SUCCESS ");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "createCampaignListOFFER ERROR " + e);
            return false;
        }
    }

/*
    public long createCampaignList(int campaign_id, int customer_id, int video_list_id, int audience_id, int budget_id,
                                   String budget_type,  String budget_start_date,  String budget_end_date,
                                   String date_diff_Start_End,              int  daily_budget_amount_lifetime,
                                   String campaign_name,    String campaign_type,       String campaign_is_prime,
                                   String campaign_added,   String campaign_ip_address, String status,
                                   String approval,         String campaign_modified,   int budget_amount_daily,
                                   int budget_amount_lifetime, String video_duration,int video_duration_in_seconds,
                                    int count
                                   )
    {
        ContentValues values = new ContentValues();

        values.put( Campaign_id,          campaign_id);
        values.put( Customer_id,           customer_id);
        values.put( Video_list_id,              video_list_id);
        values.put( Audience_id,         audience_id);
        values.put( Budget_id,    budget_id);
        values.put( Budget_type,        budget_type);
        values.put( Budget_start_date,        budget_start_date);
        values.put( Budget_end_date,        budget_end_date);
        values.put( Date_Diff_Start_End,        date_diff_Start_End);
        values.put( Daily_budget_amount_lifetime,             daily_budget_amount_lifetime);
        values.put( Campaign_name,             campaign_name);
        values.put( Campaign_type,           campaign_type);
        values.put( Campaign_is_prime,        campaign_is_prime);
        values.put( Campaign_added,    campaign_added);
        values.put( Campaign_ip_address,             campaign_ip_address);
        values.put( Status,           status);
        values.put( Approval,        approval);
        values.put( Campaign_modified,        campaign_modified);
        values.put( Budget_amount_daily,       budget_amount_daily);
        values.put( Budget_amount_lifetime,        budget_amount_lifetime);
        values.put( Video_duration,        video_duration);
        values.put( Video_duration_in_seconds,        video_duration_in_seconds);
        values.put( Count,        count);

        long l= 0;
        try {

            l =  database.insert(Tbl_campaign_list, null, values);
        }
        catch (SQLiteException e)
        {

            Log.e(TAG,"Create_Records_Cab_Tab SQLException ___________ = "+e);
            return 1;
        }
        finally {
            return 1;
        }


    }*/


    //TABLW => tbl_campaign_list
    private final static String Tbl_campaign_list_filler = "tbl_campaign_list_filler";


    public void createCampaignListFiller(ArrayList<CampaignlistItem>  campaignlistItemsFiller) {
        database.beginTransaction();
        if(campaignlistItemsFiller!= null) {
            for (int i = 0; i < campaignlistItemsFiller.size(); i++) {
                //    Log.d("count", String.valueOf(i));
                if (campaignlistItemsFiller.get(i) != null) {


                    ContentValues values = new ContentValues();
                    values.put(Campaign_id, campaignlistItemsFiller.get(i).getCampaignId());
                    values.put(Customer_id, campaignlistItemsFiller.get(i).getCustomerId());
                    values.put(Video_list_id, campaignlistItemsFiller.get(i).getVideoListId());
                    values.put(Audience_id, campaignlistItemsFiller.get(i).getAudienceId());
                    values.put(Budget_id, campaignlistItemsFiller.get(i).getBudgetId());
                    values.put(Budget_type, campaignlistItemsFiller.get(i).getBudgetType());
                    values.put(Budget_start_date, campaignlistItemsFiller.get(i).getBudgetStartDate());
                    values.put(Budget_end_date, campaignlistItemsFiller.get(i).getBudgetEndDate());
                    values.put(Date_Diff_Start_End, campaignlistItemsFiller.get(i).getDatediffStartdateandEnddate());
                    values.put(Daily_budget_amount_lifetime, campaignlistItemsFiller.get(i).getDailyBudgetAmountLifetime());
                    values.put(Campaign_name, campaignlistItemsFiller.get(i).getCampaignName());
                    values.put(Campaign_type, campaignlistItemsFiller.get(i).getCampaignType());
                    values.put(Campaign_is_prime, campaignlistItemsFiller.get(i).getCampaignIsPrime());
                    values.put(Campaign_added, campaignlistItemsFiller.get(i).getCampaignAdded().toString());
                    values.put(Campaign_ip_address, campaignlistItemsFiller.get(i).getCampaignIpAddress());
                    values.put(Status, campaignlistItemsFiller.get(i).getStatus());
                    values.put(Approval, campaignlistItemsFiller.get(i).getApproval());
                    values.put(Campaign_modified, campaignlistItemsFiller.get(i).getCampaignModified());
                    values.put(Budget_amount_daily, campaignlistItemsFiller.get(i).getBudgetAmountDaily());
                    values.put(Budget_amount_lifetime, campaignlistItemsFiller.get(i).getBudgetAmountLifetime());
                    values.put(Video_duration, campaignlistItemsFiller.get(i).getCampaignId());
                    values.put(Video_duration_in_seconds, campaignlistItemsFiller.get(i).getVideoDurationInSeconds());
                    values.put(Count, campaignlistItemsFiller.get(i).getCount());
                    database.insert(Tbl_campaign_list_filler, null, values);

                }

            }

        }

        database.setTransactionSuccessful();
        database.endTransaction();

    }


/*
    public long createCampaignListFiller(int campaign_id, int customer_id, int video_list_id, int audience_id, int budget_id,
                                   String budget_type,  String budget_start_date,  String budget_end_date,
                                   String date_diff_Start_End,              int  daily_budget_amount_lifetime,
                                   String campaign_name,    String campaign_type,       String campaign_is_prime,
                                   String campaign_added,   String campaign_ip_address, String status,
                                   String approval,         String campaign_modified,   int budget_amount_daily,
                                   int budget_amount_lifetime, String video_duration,int video_duration_in_seconds,
                                   int count
    )
    {
        ContentValues values = new ContentValues();

        values.put( Campaign_id,          campaign_id);
        values.put( Customer_id,           customer_id);
        values.put( Video_list_id,              video_list_id);
        values.put( Audience_id,         audience_id);
        values.put( Budget_id,    budget_id);
        values.put( Budget_type,        budget_type);
        values.put( Budget_start_date,        budget_start_date);
        values.put( Budget_end_date,        budget_end_date);
        values.put( Date_Diff_Start_End,        date_diff_Start_End);
        values.put( Daily_budget_amount_lifetime,             daily_budget_amount_lifetime);
        values.put( Campaign_name,             campaign_name);
        values.put( Campaign_type,           campaign_type);
        values.put( Campaign_is_prime,        campaign_is_prime);
        values.put( Campaign_added,    campaign_added);
        values.put( Campaign_ip_address,             campaign_ip_address);
        values.put( Status,           status);
        values.put( Approval,        approval);
        values.put( Campaign_modified,        campaign_modified);
        values.put( Budget_amount_daily,       budget_amount_daily);
        values.put( Budget_amount_lifetime,        budget_amount_lifetime);
        values.put( Video_duration,        video_duration);
        values.put( Video_duration_in_seconds,        video_duration_in_seconds);
        values.put( Count,        count);

        long l= 0;
        try {

            l =  database.insert(Tbl_campaign_list_filler, null, values);
        }
        catch (SQLiteException e)
        {

            Log.e(TAG,"create Campaign List Filler SQLException ___________ = "+e);
            return 1;
        }
        finally {
            return 1;
        }


    }*/



    //TABLW => tbl_video_log
    private final static String Tbl_video_log = "tbl_video_log";
    private final static String Video_log_id = "video_log_id";
    //private final static String Video_list_id = "video_list_id"; //
    private final static String Video_list_name = "video_list_name";
    //private final static String Campaign_id = "campaign_id";
    //private final static String Campaign_name = "campaign_name";
    //private final static String Audience_id = "audience_id";
    private final static String Audience_name = "audience_name";
    //private final static String Cab_tab_id = "cab_tab_id";
    private final static String Cab_tab_name = "cab_tab_name";
    private final static String Offer_consumed_id = "offer_consumed_id";
    private final static String Offer_consumed_name = "offer_consumed_name";
    private final static String Video_country = "video_country";
    private final static String Video_state = "video_state";
    private final static String Video_city = "video_city";
    private final static String Video_pincode = "video_pincode";
    private final static String Video_lat = "video_lat";
    private final static String Video_lng = "video_lng";
    private final static String Video_gender = "video_gender";
    private final static String Video_age_group = "video_age_group";
    private final static String Video_amount_charged = "video_amount_charged";
    private final static String Video_review = "video_review";
    private final static String Video_played_ip_address = "video_played_ip_address";
    private final static String Video_played_datetime = "video_played_datetime";
    //private final static String Cab_details_id = "cab_details_id";
    private final static String Duration_played = "duration_played";


//    public Boolean createVideoLogList(int video_list_id, String video_list_name, int campaign_id, String campaign_name,
//                                   int audience_id,  String audience_name,  int cab_tab_id,
//                                   String cab_tab_name,              int  offer_consumed_id,
//                                   String offer_consumed_name,    String video_country,       String video_state,
//                                   String video_city,   String video_pincode, String video_lat,
//                                   String video_lng,         String video_gender,   String video_age_group,
//                                   int video_amount_charged, String video_review,String video_played_ip_address,
//                                   String video_played_datetime
//    )
//    {
//        Boolean flag = false;
//
//
//
//
//        ContentValues values = new ContentValues();
//        values.put( Video_list_id,          video_list_id);
//        values.put( Video_list_name,          video_list_name);
//        values.put( Campaign_id,          campaign_id);
//        values.put( Campaign_name,          campaign_name);
//        values.put( Audience_id,          audience_id);
//        values.put( Audience_name,          audience_name);
//        values.put( Cab_tab_id,          cab_tab_id);
//        values.put( Cab_tab_name,          cab_tab_name);
//        values.put( Offer_consumed_id,          offer_consumed_id);
//        values.put( Offer_consumed_name,          offer_consumed_name);
//        values.put( Video_country,          video_country);
//        values.put( Video_state,          video_state);
//        values.put( Video_city,          video_city);
//        values.put( Video_pincode,          video_pincode);
//        values.put( Video_lat,          video_lat);
//        values.put( Video_lng,          video_lng);
//        values.put( Video_gender,          video_gender);
//        values.put( Video_age_group,          video_age_group);
//        values.put( Video_amount_charged,          video_amount_charged);
//        values.put( Video_review,          video_review);
//        values.put( Video_played_ip_address,          video_played_ip_address);
//        values.put( Video_played_datetime,          video_played_datetime);
//
//
//
//        try {
//
//             database.insert(Tbl_video_log, null, values);
//            flag = true;
//            return flag;
//        }
//        catch (SQLiteException e)
//        {
//
//            flag = false;
//            return flag;
//        }
//        finally {
//
//            return flag;
//        }
//
//
//    }


    //TABLW => tbl_offer_log
    private final static String Tbl_offer_log = "tbl_offer_log";
    private final static String Offer_log_id = "offer_log_id";
    //private final static String Video_list_id = "video_list_id"; //
    //private final static String Campaign_id = "campaign_id";
    //private final static String Campaign_name = "campaign_name";
    //private final static String Audience_id = "audience_id";
    private final static String Offer_name = "audience_name";
    //private final static String Cab_tab_id = "cab_tab_id";
    private final static String Offer_country = "offer_country";
    private final static String Offer_state = "offer_state";
    private final static String Offer_city = "offer_city";
    private final static String Offer_pincode = "offer_pincode";
    private final static String Offer_lat = "offer_lat";
    private final static String Offer_lng = "offer_lng";
    private final static String Offer_gender = "offer_gender";
    private final static String Offer_age_group = "offer_age_group";
    private final static String Offer_amount_charged = "offer_amount_charged";
    private final static String Offer_review = "offer_review";
    private final static String Offer_played_ip_address = "offer_played_ip_address";
    private final static String Offer_played_datetime = "offer_played_datetime";
    //private final static String Cab_details_id = "cab_details_id";


    public void insertOfferLogList(int offer_id, int campaign_id ,int audience_id, int cab_tab_id, int cab_details_id,
                                   int duration_played, int offer_consumed_id	,String offer_country	,String offer_state ,String offer_city,
                              String offer_pincode, String  offer_lat , String offer_lng, String offer_gender	, String  offer_age_group,
                                   String offer_amount_charged, String offer_review , String  offer_played_ip_address ) {
        database.beginTransaction();
                // Log.d("count", String.valueOf(i));
                if (offer_id != 0) {
                    ContentValues values = new ContentValues();
                    values.put("offer_id", offer_id);
                    values.put("campaign_id", campaign_id);
                    values.put("audience_id", audience_id);
                    values.put("cab_tab_id", cab_tab_id);
                    values.put("cab_details_id", cab_details_id);
                    values.put("duration_played", duration_played);
                    values.put("offer_consumed_id", offer_consumed_id);
                    values.put("offer_country", offer_country);
                    values.put("offer_state", offer_state);
                    values.put("offer_city", offer_city);
                    values.put("offer_pincode", offer_pincode);
                    values.put("offer_lat", offer_lat);
                    values.put("offer_lng", offer_lng);
                    values.put("offer_gender", offer_gender);
                    values.put("offer_age_group", offer_age_group);
                    values.put("offer_amount_charged", offer_amount_charged);
                    values.put("offer_review", offer_review);
                    values.put("offer_played_ip_address", offer_played_ip_address);


                    database.insert(Tbl_offer_log, null, values);
                }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public ArrayList<OfferLogItem>  getOfferLogObject() {
        ArrayList<OfferLogItem> offerLogItemList = new ArrayList<>();

        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_offer_log;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));
                OfferLogItem videoLogItem = new OfferLogItem();
                videoLogItem.setOfferId(String.valueOf(cursor.getInt(0)));
                videoLogItem.setCampaignId(String.valueOf(cursor.getInt(1)));
                videoLogItem.setAudienceId(String.valueOf(cursor.getInt(2)));
                videoLogItem.setCabTabId(String.valueOf(cursor.getInt(3)));
                videoLogItem.setCabDetailsId(String.valueOf(cursor.getInt(4)));
                videoLogItem.setDurationPlayed(String.valueOf(cursor.getInt(5)));
                videoLogItem.setOfferConsumedId(String.valueOf(cursor.getString(6)));
                videoLogItem.setOfferCountry(cursor.getString(7));
                videoLogItem.setOfferState(cursor.getString(8));
                videoLogItem.setOfferCity(cursor.getString(9));
                videoLogItem.setOfferPincode(cursor.getString(10));
                videoLogItem.setOfferLat(cursor.getString(11));
                videoLogItem.setOfferLng(cursor.getString(12));
                videoLogItem.setOfferGender(cursor.getString(13));
                videoLogItem.setOfferAgeGroup(cursor.getString(14));
                videoLogItem.setOfferAmountCharged(cursor.getString(15));
                videoLogItem.setOfferPlayedIpAddress(cursor.getString(17));
                videoLogItem.setOfferReview(cursor.getString(16));



                offerLogItemList.add(videoLogItem);
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** getting OfferLogItem "+ex.toString());
            offerLogItemList = null;
            return  offerLogItemList;
        }
        return offerLogItemList;
    }

    public void insertVideoLogList(int video_list_id, int campaign_id ,int audience_id, int cab_tab_id, String video_country	,String video_state ,String video_city,
                                   String video_pincode,String video_gender	, String  video_age_group, String video_review ,
                                   String video_played_datetime, String  video_played_ip_address	,  int offer_consumed_id	,
                                   String  video_lat , String video_lng, String video_amount_charged,
                                   int cab_details_id,int duration_played) {
        database.beginTransaction();
        // Log.d("count", String.valueOf(i));
        if (video_list_id != 0) {
            ContentValues values = new ContentValues();
            values.put("video_list_id", video_list_id);
            values.put("campaign_id", campaign_id);
            values.put("audience_id", audience_id);
            values.put("cab_tab_id", cab_tab_id);
            values.put("video_country", video_country);
            values.put("video_state", video_state);
            values.put("video_city", video_city);
            values.put("video_pincode", video_pincode);
            values.put("video_gender", video_gender);
            values.put("video_age_group", video_age_group);
            values.put("video_review", video_review);
            values.put("video_played_datetime", video_played_datetime);
            values.put("video_played_ip_address", video_played_ip_address);
            values.put("offer_consumed_id", offer_consumed_id);
            values.put("video_lat", video_lat);
            values.put("video_lng", video_lng);
            values.put("video_amount_charged", video_amount_charged);
            values.put("cab_details_id", cab_details_id);
            values.put("duration_played", duration_played);
            database.insert(Tbl_video_log, null, values);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public long createRecordsCabLoginDetails(int cab_tab_id, int cab_details_id, String cab_tab_email,
                                    String cab_driver_name, String cab_driver_mobile_no, String is_verified,
                                    String cab_tab_added, String cab_tab_modified
    ){
        ContentValues values = new ContentValues();
        values.put( Cab_tab_id,              cab_tab_id);
        values.put( Cab_details_id,          cab_details_id);
        values.put( Cab_tab_email,           cab_tab_email);
        values.put( Cab_driver_name,         cab_driver_name);
        values.put( Cab_driver_mobile_no,    cab_driver_mobile_no);
        values.put( Is_verified,             is_verified);
        values.put( Cab_tab_added,           cab_tab_added);
        values.put( Cab_tab_modified,        cab_tab_modified);

        long l= 0;
        try {

            l =  database.insert(Tbl_cab_login_details, null, values);
        }
        catch (Exception e)
        {
            return 1;
            //Log.e(TAG,"Create_Records_Cab_Tab SQLException = "+e);
        }

        finally {
            return 1;
        }
    }


    public ArrayList<Integer> getAllVideos() {
        ArrayList<Integer> videoName = new ArrayList<>();
        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_cab_video_list;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));

                videoName.add(cursor.getInt(cursor.getColumnIndex("video_list_id")));
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting videoListDB "+ex.toString());
            videoName = null;
            return  videoName;
        }
        return videoName;
    }

    public ArrayList<Integer> getVideoSizeById(String video_list_id) {
        ArrayList<Integer> videoName = new ArrayList<>();
        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_cab_video_list+" where video_list_id like"+video_list_id;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));

                videoName.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("video_size"))));
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting videoListDB "+ex.toString());
            videoName = null;
            return  videoName;
        }
        return videoName;
    }


    public ArrayList<String> getCabVideosListNames() {
        ArrayList<String> videoName = new ArrayList<>();
        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_cab_video_list;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));

                videoName.add(cursor.getString(cursor.getColumnIndex("video_url")));
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting videoListDB "+ex.toString());
            videoName = null;
            return  videoName;
        }
        return videoName;
    }


    public int getCabDetailsId() {
        int cabDetailsId = 0;
        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_cab_details;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));

                cabDetailsId = cursor.getInt(cursor.getColumnIndex("cab_details_id"));
                break;
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting getCabDetailsId "+ex.toString());
            cabDetailsId = 0;
            return  cabDetailsId;
        }
        return cabDetailsId;
    }





    public ArrayList<CampaignlistItem>  getAllVideosNames() {
        ArrayList<CampaignlistItem> videoName = new ArrayList<>();

        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_campaign_list;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));
                CampaignlistItem campaignlistItem = new CampaignlistItem();
                campaignlistItem.setCampaignId(cursor.getString(cursor.getColumnIndex("campaign_id")));
                campaignlistItem.setCustomerId(cursor.getString(cursor.getColumnIndex("customer_id")));
                campaignlistItem.setVideoListId(cursor.getString(cursor.getColumnIndex("video_list_id")));
                campaignlistItem.setAudienceId(cursor.getString(cursor.getColumnIndex("audience_id")));
                campaignlistItem.setBudgetId(cursor.getString(cursor.getColumnIndex("budget_id")));
                videoName.add(campaignlistItem);
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting videoListDB "+ex.toString());
            videoName = null;
            return  videoName;
        }
        return videoName;
    }


    public String getVideoUrlFromVideoListId(int videoListId) {

        String url = null;
        try {

            String query = "Select * from "+Tbl_cab_video_list+" WHERE video_list_id='"+videoListId+"'";
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));

                url = cursor.getString(cursor.getColumnIndex("video_url"));

            }
            cursor.close();

        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting videoListDB "+ex.toString());
            return null;

        }


        return url;
    }


    public ArrayList<CampaignlistItem> getNewAllVideosNames(String type) {

        LinkedHashMap<String,String> videoId = new LinkedHashMap<>();
        LinkedHashMap<String,String> videoIdSize = new LinkedHashMap<>();
        //SQLiteDatabase sqLiteDatabase = null;

        try {
            String query = "Select * from "+Tbl_cab_video_list+" WHERE video_type='"+type+"'";
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));
            String url = cursor.getString(cursor.getColumnIndex("video_url"));
                url = url.replace("http://madads.in/adminn/assets/uploaded-video/","");
                videoId.put(cursor.getString(cursor.getColumnIndex("video_list_id")),
                        url);

                videoIdSize.put(cursor.getString(cursor.getColumnIndex("video_list_id")),
                        cursor.getString(cursor.getColumnIndex("video_size")));

            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting videoListDB "+ex.toString());

        }

        //=======================================================================
       // ArrayList<String> videoName = new ArrayList<>();
        ArrayList<CampaignlistItem> campaignlistItems = new ArrayList<>();
        int count = 0;
        //SQLiteDatabase sqLiteDatabase = null;
        try {

            String query;

                query = "Select * from "+Tbl_campaign_list +" WHERE campaign_type='"+type+"'";

            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));
                int video_id = cursor.getInt(cursor.getColumnIndex("video_list_id"));
                String v = String.valueOf(video_id);
                Log.e(TAG,v+"     ========================   vvvvvvvvvvv   ____________  ");

                for(String s: videoId.keySet())
                {
                    if(v.equals(s))
                    {
                        CampaignlistItem campaign = new CampaignlistItem();
                        campaign.setCampaignId(cursor.getString(cursor.getColumnIndex("campaign_id")));
                        campaign.setCustomerId(cursor.getString(cursor.getColumnIndex("customer_id")));
                        campaign.setVideoListId(cursor.getString(cursor.getColumnIndex("video_list_id")));
                        campaign.setAudienceId(cursor.getString(cursor.getColumnIndex("audience_id")));
                        campaign.setBudgetId(cursor.getString(cursor.getColumnIndex("budget_id")));
                        campaign.setBudgetType(cursor.getString(cursor.getColumnIndex("budget_type")));
                        campaign.setBudgetStartDate(cursor.getString(cursor.getColumnIndex("budget_start_date")));
                        campaign.setBudgetEndDate(cursor.getString(cursor.getColumnIndex("budget_end_date")));
                        campaign.setDatediffStartdateandEnddate(cursor.getString(cursor.getColumnIndex("date_diff_start_end")));
                        campaign.setDailyBudgetAmountLifetime(cursor.getInt(cursor.getColumnIndex("daily_budget_amount_lifetime")));
                        campaign.setCampaignName(cursor.getString(cursor.getColumnIndex("campaign_name")));
                        campaign.setCampaignType(cursor.getString(cursor.getColumnIndex("campaign_type")));
                        campaign.setCampaignIsPrime(cursor.getString(cursor.getColumnIndex("campaign_is_prime")));
                        campaign.setCampaignAdded(cursor.getString(cursor.getColumnIndex("campaign_added")));
                        campaign.setCampaignIpAddress(cursor.getString(cursor.getColumnIndex("campaign_ip_address")));
                        campaign.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        campaign.setApproval(cursor.getString(cursor.getColumnIndex("approval")));
                        campaign.setCampaignModified(cursor.getString(cursor.getColumnIndex("campaign_modified")));
                        campaign.setBudgetAmountDaily(cursor.getString(cursor.getColumnIndex("budget_amount_daily")));
                        campaign.setBudgetAmountLifetime(cursor.getInt(cursor.getColumnIndex("budget_amount_lifetime")));
                        campaign.setVideoDuration(cursor.getString(cursor.getColumnIndex("video_duration")));
                        campaign.setVideoDurationInSeconds(cursor.getInt(cursor.getColumnIndex("video_duration_in_seconds")));
                        campaign.setCount(cursor.getInt(cursor.getColumnIndex("count")));
                        campaign.setPriority(0);
                        campaign.setTempCount(0);
                        campaign.setVideoName(videoId.get(s));
                        campaign.setVideoSize(videoIdSize.get(s));

                       // Log.e(TAG,type+"     ========================   videoId   ____________  "+videoId.get(s));
                      //  videoName.add(videoId.get(s));
                        campaignlistItems.add(campaign);
                        Log.e(TAG,campaign.getVideoListId()+"     ========================   videoId   ____________  "+campaign.getVideoName());
                        break;
                    }


                }
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting videoListDB "+ex.toString());
            campaignlistItems = null;
            return  campaignlistItems;
        }
        return campaignlistItems;
    }

    public ArrayList<CampaignlistOfferItem> getNewAllOffers() {

        //=======================================================================
        // ArrayList<String> videoName = new ArrayList<>();
        ArrayList<CampaignlistOfferItem> campaignlistItems = new ArrayList<>();
        int count = 0;
        //SQLiteDatabase sqLiteDatabase = null;
        try {

            String query;
                query = "Select * from "+Tbl_campaign_list_offer  ;

            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));

                        CampaignlistOfferItem campaign = new CampaignlistOfferItem();
                        OfferData offerData = new OfferData();
                        campaign.setCampaignId(cursor.getString(cursor.getColumnIndex("campaign_id")));
                        campaign.setCustomerId(cursor.getString(cursor.getColumnIndex("customer_id")));

                        offerData.setCustomerIdOffer(cursor.getString(cursor.getColumnIndex("customer_id_offer")));
                        offerData.setOfferTitle(cursor.getString(cursor.getColumnIndex("offer_title")));
                        offerData.setOfferDescription(cursor.getString(cursor.getColumnIndex("offer_description")));
                        offerData.setOfferCode(cursor.getString(cursor.getColumnIndex("offer_code")));
                        offerData.setOfferAdded(cursor.getString(cursor.getColumnIndex("offer_added")));
                        offerData.setOfferDuration(cursor.getString(cursor.getColumnIndex("offer_duration")));
                        offerData.setOfferImg(cursor.getString(cursor.getColumnIndex("offer_img")));
                        offerData.setOfferExpiryDate(cursor.getString(cursor.getColumnIndex("offer_expiry_date")));

                        campaign.setOfferId(cursor.getString(cursor.getColumnIndex("offer_id")));
                        campaign.setAudienceId(cursor.getString(cursor.getColumnIndex("audience_id")));
                        campaign.setBudgetId(cursor.getString(cursor.getColumnIndex("budget_id")));
                        campaign.setBudgetType(cursor.getString(cursor.getColumnIndex("budget_type")));
                        campaign.setBudgetStartDate(cursor.getString(cursor.getColumnIndex("budget_start_date")));
                        campaign.setBudgetEndDate(cursor.getString(cursor.getColumnIndex("budget_end_date")));
                        campaign.setDatediffStartdateandEnddate(cursor.getString(cursor.getColumnIndex("date_diff_start_end")));
                        campaign.setDailyBudgetAmountLifetime(cursor.getInt(cursor.getColumnIndex("daily_budget_amount_lifetime")));
                        campaign.setCampaignName(cursor.getString(cursor.getColumnIndex("campaign_name")));
                        campaign.setCampaignType(cursor.getString(cursor.getColumnIndex("campaign_type")));
                        campaign.setCampaignIsPrime(cursor.getString(cursor.getColumnIndex("campaign_is_prime")));
                        campaign.setCampaignAdded(cursor.getString(cursor.getColumnIndex("campaign_added")));
                        campaign.setCampaignIpAddress(cursor.getString(cursor.getColumnIndex("campaign_ip_address")));
                        campaign.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                        campaign.setApproval(cursor.getString(cursor.getColumnIndex("approval")));
                        campaign.setCampaignModified(cursor.getString(cursor.getColumnIndex("campaign_modified")));
                        campaign.setBudgetAmountDaily(cursor.getString(cursor.getColumnIndex("budget_amount_daily")));
                        campaign.setBudgetAmountLifetime(cursor.getInt(cursor.getColumnIndex("budget_amount_lifetime")));
                        campaign.setOfferDurationInSeconds(cursor.getString(cursor.getColumnIndex("offer_duration_in_seconds")));


                        campaign.setCount(cursor.getInt(cursor.getColumnIndex("count")));
                        campaign.setGetOfferlogCount(cursor.getInt(cursor.getColumnIndex("getOfferlogCount")));

                        campaign.setOfferData(offerData);

                        //  Log.e(TAG,type+"     ========================   videoId   ____________  "+s);
                        // Log.e(TAG,type+"     ========================   videoId   ____________  "+videoId.get(s));
                        //  videoName.add(videoId.get(s));
                        campaignlistItems.add(campaign);


            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting CampaignListOfferDB "+ex.toString());
            campaignlistItems = null;
            return  campaignlistItems;
        }
        return campaignlistItems;
    }


    public boolean deleteVideoList(String videoListId) {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_cab_video_list + " WHERE video_list_id NOT IN (" + videoListId + ")");
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }


    public boolean deleteOfferCampaignItemById() {

        try
        {
        database.execSQL("delete from tbl_campaign_list_offer where rowid IN (Select rowid from tbl_campaign_list_offer limit 1);");
        /*Cursor cursor = database.query("SELECT * FROM tbl_campaign_list", null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            String rowId = String.valueOf(cursor.getInt(cursor.getColumnIndex("id")));
            database.delete("DELETE FROM tbl_campaign_list", "id=?",  new String[]{rowId});
        }
        database.close();
        cursor.close();*/
        return  true;
        }catch (SQLiteException e)
        {
            Log.e("SQLiteException","_____ deleteOfferCampaignItemById _______???????????? "+e);
            return false;
        }

    }

    public boolean deleteCampaignItemById() {

        try
        {
        database.execSQL("delete from tbl_campaign_list where rowid IN (Select rowid from tbl_campaign_list limit 1);");
        /*Cursor cursor = database.query("SELECT * FROM tbl_campaign_list", null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            String rowId = String.valueOf(cursor.getInt(cursor.getColumnIndex("id")));
            database.delete("DELETE FROM tbl_campaign_list", "id=?",  new String[]{rowId});
        }
        database.close();
        cursor.close();*/
        return  true;
        }catch (SQLiteException e)
        {
            Log.e("SQLiteException","_____ deleteCampaignItemById _______???????????? "+e);
            return false;
        }

    }


    public boolean deleteCampaignFillerItemById() {

        try
        {
            database.execSQL("delete from tbl_campaign_list_filler where rowid IN (Select rowid from tbl_campaign_list_filler limit 1);");
        /*Cursor cursor = database.query("SELECT * FROM tbl_campaign_list", null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            String rowId = String.valueOf(cursor.getInt(cursor.getColumnIndex("id")));
            database.delete("DELETE FROM tbl_campaign_list", "id=?",  new String[]{rowId});
        }
        database.close();
        cursor.close();*/
            return  true;
        }catch (SQLiteException e)
        {
            Log.e("SQLiteException","_____ deleteCampaignFillerItemById _______???????????? "+e);
            return false;
        }

    }


    public boolean deleteCampaignItemFillerById(int campaignId) {

        try
        {
            database.execSQL("delete from tbl_campaign_list_filler where campaign_id='"+campaignId+"'");


            return  true;
        }catch (SQLiteException e)
        {
            Log.e("SQLiteException","_____ deleteCampaignItemById _______???????????? "+e);

            return false;
        }


    }


    public boolean deleteCampaignList() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_campaign_list );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }

    public boolean deleteCampaignListFiller() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_campaign_list_filler );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }


    public boolean deleteCampaignListOffer() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_campaign_list_offer );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }

    public Cursor selectRecords() {
        String[] cols = new String[] {Video_list_id,Customer_id,Video_name,Video_url,
                Video_duration,Video_format,Video_type,Video_added};
        Cursor mCursor = database.query(true, Tbl_cab_video_list,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }


    public void insertCountry(List<Country> country) {
        database.beginTransaction();
        if(country!= null) {
            for (int i = 0; i < country.size(); i++) {
                // Log.d("count", String.valueOf(i));
                if (country.get(i) != null) {
                    ContentValues values = new ContentValues();
                    values.put("country_id", country.get(i).getCountryId());
                    values.put("country_name", country.get(i).getCountryName());
                    values.put("country_phone_code", country.get(i).getCountryPhoneCode());
                    values.put("country_used", country.get(i).getCountryUsed());
                    values.put("country_added", country.get(i).getCountryAdded());
                    database.insert(Tbl_Country, null, values);

                }
            }
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public boolean deleteCountry() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_Country );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }

    public ArrayList<Country>  getAllCountry() {
        ArrayList<Country> countryList = new ArrayList<>();

        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_Country;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));
                Country country = new Country();
                country.setCountryId(cursor.getString(0));
                country.setCountryName(cursor.getString(1));
                country.setCountryPhoneCode(cursor.getString(2));
                country.setCountryUsed(cursor.getString(3));
                country.setCountryAdded(cursor.getString(4));
                countryList.add(country);
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting countryList "+ex.toString());
            countryList = null;
            return  countryList;
        }
        return countryList;
    }


    public ArrayList<VideoLogItem>  getVideoLogObject() {
        ArrayList<VideoLogItem> videoLogItemList = new ArrayList<>();

        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_video_log;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));
                VideoLogItem videoLogItem = new VideoLogItem();
                videoLogItem.setVideoListId(String.valueOf(cursor.getInt(0)));
                videoLogItem.setCampaignId(String.valueOf(cursor.getInt(1)));
                videoLogItem.setAudienceId(String.valueOf(cursor.getInt(2)));
                videoLogItem.setCabTabId(String.valueOf(cursor.getInt(3)));
                videoLogItem.setVideoCountry(cursor.getString(4));
                videoLogItem.setVideoState(cursor.getString(5));
                videoLogItem.setVideoCity(cursor.getString(6));
                videoLogItem.setVideoPincode(cursor.getString(7));
                videoLogItem.setVideoGender(cursor.getString(8));
                videoLogItem.setVideoAgeGroup(cursor.getString(9));
                videoLogItem.setVideoReview(cursor.getString(10));
                videoLogItem.setVideoPlayedDatetime(cursor.getString(11));
                videoLogItem.setVideoPlayedIpAddress(cursor.getString(12));
                videoLogItem.setOfferConsumedId(String.valueOf(cursor.getString(13)));
                videoLogItem.setVideoLat(cursor.getString(14));
                videoLogItem.setVideoLng(cursor.getString(15));
                videoLogItem.setVideoAmountCharged(cursor.getString(16));
                videoLogItem.setCabDetailsId(String.valueOf(cursor.getInt(17)));
                videoLogItem.setDurationPlayed(String.valueOf(cursor.getInt(18)));

                videoLogItemList.add(videoLogItem);
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** getting VideoLogItem "+ex.toString());
            videoLogItemList = null;
            return  videoLogItemList;
        }
        return videoLogItemList;
    }




    public void insertState(List<State> state) {
        database.beginTransaction();
        if(state!= null) {
            for (int i = 0; i < state.size(); i++) {
                // Log.d("count", String.valueOf(i));
                if (state.get(i) != null) {
                    ContentValues values = new ContentValues();
                    values.put("state_id", state.get(i).getStateId());
                    values.put("country_id", state.get(i).getCountryId());
                    values.put("state_name", state.get(i).getStateName());
                    values.put("state_used", state.get(i).getStateUsed());
                    values.put("state_added", state.get(i).getStateAdded());
                    database.insert(Tbl_State, null, values);

                }
            }
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }
    public boolean deleteState() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_State );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }

    public ArrayList<State>  getAllState() {
        ArrayList<State> stateList = new ArrayList<>();

        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_State;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));
                State state = new State();
                state.setStateId(cursor.getString(0));
                state.setCountryId(cursor.getString(1));
                state.setStateName(cursor.getString(2));
                state.setStateUsed(cursor.getString(3));
                state.setStateAdded(cursor.getString(4));
                stateList.add(state);
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting stateList "+ex.toString());
            stateList = null;
            return  stateList;
        }
        return stateList;
    }

    public void insertCity(List<City> city) {
        database.beginTransaction();
        if(city!= null) {
            for (int i = 0; i < city.size(); i++) {
                // Log.d("count", String.valueOf(i));
                if (city.get(i) != null) {
                    ContentValues values = new ContentValues();
                    values.put("city_id", city.get(i).getCityId());
                    values.put("state_id", city.get(i).getStateId());
                    values.put("country_id", city.get(i).getCountryId());
                    values.put("city_name", city.get(i).getCityName());
                    values.put("non_prime_amount", city.get(i).getNonPrimeAmount());
                    values.put("prime_amount", city.get(i).getPrimeAmount());
                    values.put("city_used", city.get(i).getCityUsed());
                    values.put("city_added", city.get(i).getCityAdded());
                    database.insert(Tbl_City, null, values);

                }
            }
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }
    public boolean deleteCity() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_City );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }


    public boolean deleteVideoLogData() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_video_log );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }

    public boolean deleteOfferLogData() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_offer_log );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }


    public ArrayList<City>  getAllCity(String city1) {
        ArrayList<City> cityList = new ArrayList<>();

        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_City + " where city_id = " + city1;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));
                City city = new City();
                city.setCityId(cursor.getString(0));
                city.setStateId(cursor.getString(1));
                city.setCountryId(cursor.getString(2));
                city.setCityName(cursor.getString(3));
                city.setNonPrimeAmount(cursor.getString(4));
                city.setPrimeAmount(cursor.getString(5));
                city.setCityUsed(cursor.getString(6));
                city.setCityAdded(cursor.getString(7));
                cityList.add(city);
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting cityList "+ex.toString());
            cityList = null;
            return  cityList;
        }
        return cityList;
    }

    public void insertPincode(List<Pincode> pincode) {
        database.beginTransaction();
        if(pincode!= null) {
            for (int i = 0; i < pincode.size(); i++) {
                // Log.d("count", String.valueOf(i));
                if (pincode.get(i) != null) {
                    ContentValues values = new ContentValues();
                    values.put("pincode_id", pincode.get(i).getPincodeId());
                    values.put("city_id", pincode.get(i).getCityId());
                    values.put("state_id", pincode.get(i).getStateId());
                    values.put("country_id", pincode.get(i).getCountryId());
                    values.put("pincode", pincode.get(i).getPincode());
                    values.put("post_office", pincode.get(i).getPostOffice());
                    values.put("district", pincode.get(i).getDistrict());
                    values.put("pincode_used", pincode.get(i).getPincodeUsed());
                    values.put("pincode_added", pincode.get(i).getPincodeAdded());
                    database.insert(Tbl_Pincode, null, values);
                }
            }
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }
    public boolean deletePincode() {
        //SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            database.execSQL("DELETE FROM " + Tbl_Pincode );
            return true;
        }catch (SQLiteException e)
        {
            return false;
        }

    }

    public ArrayList<Pincode>  getAllPincode(String pin) {
        ArrayList<Pincode> pincodeList = new ArrayList<>();

        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select * from "+Tbl_Pincode +" where pincode like "+ pin;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {
                // Log.e(TAG,"getAllVideos ________________________ videoListDB = "+cursor.getString(cursor.getColumnIndex("video_name")));
                Pincode pincode = new Pincode();
                pincode.setPincodeId(cursor.getString(0));
                pincode.setCityId(cursor.getString(1));
                pincode.setStateId(cursor.getString(2));
                pincode.setCountryId(cursor.getString(3));
                pincode.setPincode(cursor.getString(4));
                pincode.setPostOffice(cursor.getString(5));
                pincode.setDistrict(cursor.getString(3));
                pincode.setPincodeUsed(cursor.getString(4));
                pincode.setPincodeAdded(cursor.getString(5));
                pincodeList.add(pincode);
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting pincodeList "+ex.toString());
            pincodeList = null;
            return  pincodeList;
        }
        return pincodeList;
    }

    public String  getAllPincode() {
        String count="";

        //SQLiteDatabase sqLiteDatabase = null;
        try {
            String query = "Select count (*) from "+Tbl_Pincode ;
            Cursor cursor = database.rawQuery(query, null);
            while (cursor.moveToNext()) {

                count = cursor.getString(0);
            }
            cursor.close();
        }catch(Exception ex){
            Log.e(TAG,"Error in **************************** geting pincodeList "+ex.toString());

            return  count;
        }
        return count;
    }
}
