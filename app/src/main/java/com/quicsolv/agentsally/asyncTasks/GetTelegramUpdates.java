package com.quicsolv.agentsally.asyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.quicsolv.agentsally.httpUtilities.HTTPUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Saif on 7/18/17.
 */

public class GetTelegramUpdates extends AsyncTask<Void, Void, Void> {
    Context context;
    private static String url = "https://api.telegram.org/bot380381065:AAFZigWkuGOUN7FXPAiKRbGPzj6R4IbUpDw/";
    public static String chatID;

    GetTelegramUpdates(Context con) {
        context = con;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Getting messages, hold on");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String contactURL = url.concat("getUpdates");
            HTTPUtility httpUtility = new HTTPUtility();
            String JSONString = httpUtility.makeServiceCall(contactURL);
            if (JSONString != null) {
                JSONObject parentObject = new JSONObject(JSONString);
                String success = parentObject.getString("ok");
                if(success.equals("true"))
                {
                    JSONArray resultArray = parentObject.getJSONArray("result");

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
