package com.quicsolv.agentsally.asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.quicsolv.agentsally.httputilities.HTTPUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Saif on 7/18/17.
 */

public class GetTelegramUpdates extends AsyncTask<Void, Void, Void> {
    Context context;
    private static String url = "https://api.telegram.org/bot380381065:AAFZigWkuGOUN7FXPAiKRbGPzj6R4IbUpDw/";
    public static String chatID;
    ProgressDialog progressDialog;

    public GetTelegramUpdates(Context con) {
        context = con;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
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
            Log.d("GETTING UPDATES",JSONString);
            if (JSONString != null) {
                JSONObject parentObject = new JSONObject(JSONString);
                String success = parentObject.getString("ok");
                if(success.equals("true"))
                {
                    JSONArray resultArray = parentObject.getJSONArray("result");
                    for(int i=0;i<resultArray.length();i++)
                    {
                        String text = getLastChatIdAndText(resultArray);
                        sendMessageFromBot(text);
                    }

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("JSONEXCEPTION",e.toString());
        }
        return null;
    }

    private void sendMessageFromBot(String text) throws JSONException{
        String url =  this.url +"sendMessage?text="+text+"&chat_id="+chatID;
        HTTPUtility httpUtility = new HTTPUtility();
        String JSONString = httpUtility.makeServiceCall(url);
        if(JSONString!=null) {
            JSONObject result = new JSONObject(JSONString);
            String checkIfSuccess = result.getString("ok");
            if (checkIfSuccess.equals("true")) Log.d("SENDING BOT MESSAGE", "SUCCESS");
            else Log.d("SENDING BOT MESSAGE", "FAILED");
        }else{
            Log.d("SENDING BOT MESSAGE", "FAILED NULL RESULT");
        }
    }

    private String getLastChatIdAndText(JSONArray result) throws JSONException{
        int numberOfUpdates = result.length();
        int lastUpdate = numberOfUpdates-1;
        JSONObject lastUpdateObj = result.getJSONObject(lastUpdate);
        JSONObject messageObj = lastUpdateObj.getJSONObject("message");
        JSONObject chatObj = messageObj.getJSONObject("chat");
        String chatId = chatObj.getString("id");
        chatID = chatId;
        String text = messageObj.getString("text");
        return text;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
        progressDialog.dismiss();
    }

}
