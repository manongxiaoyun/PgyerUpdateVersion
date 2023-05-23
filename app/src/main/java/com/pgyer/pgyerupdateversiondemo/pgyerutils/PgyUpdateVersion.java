package com.pgyer.pgyerupdateversiondemo.pgyerutils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe
 * @author: Caoxy
 * @date: 2023/5/22
 */
public class PgyUpdateVersion {
    public static final String CHECK_SOFTWARE = "https://www.pgyer.com/apiv2/app/check";

    /**
     * 版本检查
     * @param apikey 蒲公英平台获取
     * @param appKey 蒲公英平台获取
     * @param channelKey 蒲公英平台获取
     * @param versionName 版本号 build.gradle 中的 versionName
     * @param callBack 请求返回数据接口回调
     */
    public static void checkVersionUpdate(String apikey, String appKey, String channelKey,String versionName,final PgyCheckoutCallBack callBack){
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);;
        String url = getVersionUrl(apikey,appKey,channelKey,versionName);
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(url, "GET", new HttpRequestCallback() {
            @Override
            public void onSuccess(String response) {
                disposeChackVersionBack(response, callBack);
            }

            @Override
            public void onFail(Exception e) {
                if (callBack != null) {
                    Log.d("Pgyer log:", "request is fail and callback is not null");
                    callBack.onFail("request is fail and callback is not null");
                }
                Log.d("Pgyer log:", "show delog fail");
            }
        });
        httpRequestAsyncTask.executeOnExecutor(newFixedThreadPool);
    }

    /**
     * 组装获取版本号URL
     * @return
     */
    public static String getVersionUrl(String apikey, String appKey, String channelKey,String versionName){
        StringBuilder builder = new StringBuilder("?_api_key=");
        builder.append(apikey);
        builder.append("&");
        builder.append("appKey=");
        builder.append(appKey);
        builder.append("&buildVersion=");
        builder.append(versionName);
        if (!TextUtils.isEmpty(channelKey)) {
            Log.d("Pgyer log:", "current channel key is " + channelKey);
            builder.append("&channelKey=");
            builder.append(channelKey);
        } else {
            Log.d("Pgyer log:", "current channel key is null");
        }
        return CHECK_SOFTWARE + builder;
    }

    /**
     * 版本检查接口数据回调处理
     *
     * @param backStr
     * @param newCallBack
     */
    @SuppressLint("WrongConstant")
    private static void disposeChackVersionBack(String backStr, final PgyCheckoutCallBack newCallBack) {
        try {
            Log.d("Pgyer log:", "backStr:"+backStr);
            JSONObject jsonObject = new JSONObject(backStr);
            if (jsonObject.getInt("code") != 0) {
                if (newCallBack != null) {
                    Log.d("Pgyer log:", "request is fail and callback is not null");
                    newCallBack.onNonentityVersionExist(jsonObject.getString("message"));
                }
                Log.d("Pgyer log:", "checkSoftwareUpdate request Faile message= " + jsonObject.getString("message"));
                return;
            }
            String data = jsonObject.getString("data");
            JSONObject jsonData = new JSONObject(data);
            boolean isNeedForceUpdate = jsonData.getBoolean("needForceUpdate");
            boolean buildHaveNewVersion = jsonData.getBoolean("buildHaveNewVersion");
            String downloadURL = jsonData.getString("downloadURL");
            String buildUpdateDescription = jsonData.getString("buildUpdateDescription");
            String buildVersionNo = jsonData.getString("buildVersionNo");
            String buildShortcutUrl = "";
            if(jsonData.has("buildShortcutUrl")){
                buildShortcutUrl = jsonData.getString("buildShortcutUrl");
            }
            int buildBuildVersion = jsonData.getInt("buildBuildVersion");
            String forceUpdateVersion = jsonData.getString("forceUpdateVersion");
            String forceUpdateVersionNo = jsonData.getString("forceUpdateVersionNo");
            String buildVersion = jsonData.getString("buildVersion");
            String buildDescription = jsonData.getString("buildDescription");
            String buildName = jsonData.getString("buildName");
            String buildIcon = jsonData.getString("buildIcon");
            String buildFileSize = jsonData.getString("buildFileSize");

            if (newCallBack != null) {// 实现了接口 CheckoutCallBack 在接口中获取到版本信息自行判断实现逻辑
                Log.d("pgyer_log:", "request is success and callback is not null");
                PgyCheckSoftModel checkSoftModel = new PgyCheckSoftModel();
                checkSoftModel.setNeedForceUpdate(isNeedForceUpdate);
                checkSoftModel.setBuildHaveNewVersion(buildHaveNewVersion);
                checkSoftModel.setDownloadURL(downloadURL);
                checkSoftModel.setBuildUpdateDescription(buildUpdateDescription);
                checkSoftModel.setBuildVersionNo(buildVersionNo);
                checkSoftModel.setBuildShortcutUrl(buildShortcutUrl);
                checkSoftModel.setBuildBuildVersion(buildBuildVersion);
                checkSoftModel.setForceUpdateVersion(forceUpdateVersion);
                checkSoftModel.setForceUpdateVersionNo(forceUpdateVersionNo);
                checkSoftModel.setBuildVersion(buildVersion);
                checkSoftModel.setBuildDescription(buildDescription);
                checkSoftModel.setBuildName(buildName);
                checkSoftModel.setBuildIcon(buildIcon);
                checkSoftModel.setBuildFileSize(buildFileSize);
                Log.d("pgyer_log:", "buildHaveNewVersion:"+buildHaveNewVersion);
                if(buildHaveNewVersion){
                    newCallBack.onNewVersionExist(checkSoftModel);
                } else {
                    newCallBack.onNonentityVersionExist("No new version");
                }
            }
        } catch (JSONException e) {
            Log.e("Pgyer log:", "JSONException e=" + e.getMessage());
            if (newCallBack != null) {
                Log.d("Pgyer log:", "request is fail and callback is not null");
                newCallBack.onFail(e.getMessage());
            }
        }
    }

    /**
     * 网络请求 （如果app中已有网络请求工具，可以自行实习网络请求）
     */
    public static class HttpRequestAsyncTask extends AsyncTask<Void, Void, HashMap<String, String>> {
        private static final String TAG = "PGY_PACHttpRequest";
        public static final String POST = "POST";
        public static final String GET = "GET";

        private String address;
        private String params;
        private String requestMethod;
        private HttpRequestCallback listener;

        public HttpRequestAsyncTask(String url, String requestMethod,HttpRequestCallback listener) {
            this.address = url;
            this.params = params;
            this.requestMethod = requestMethod;
            this.listener = listener;
        }

        @Override
        protected HashMap<String, String> doInBackground(Void... voids) {
            HashMap<String, String> result = new HashMap<>();
            HttpURLConnection connection = null;
            try {
                URL url = new URL(address);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(1500);
                connection.setReadTimeout(1500);
                connection.setRequestMethod(requestMethod);
                if(requestMethod.equals(POST)){
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Accept", "app lication/json");
                    OutputStream os = connection.getOutputStream();
                    os.write(params.getBytes());
                    os.close();

                } else {
                    connection.connect();
                }

                int responseCode = connection.getResponseCode();
                Log.d(TAG, "PACHttpRequestAsyncTask Succeed");
                result.put("status", String.valueOf(responseCode));
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                String jsonString = convertStreamToString(inputStream);
                inputStream.close();
                result.put("response", jsonString);

            } catch (Exception e) {
                e.printStackTrace();
                if (listener != null) {
                    Log.e(TAG, "Exception e =" + e.getMessage());
                    listener.onFail(e);
                }
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(HashMap<String, String> result) {
            super.onPostExecute(result);
            Log.d(TAG, "Request finish");
            String status = result.get("status");
            Log.d(TAG, "Request finish status=" + status);
            if (status == null) {
                Log.d(TAG, "current status is null");
                return;
            }
            if ("200".equals(status)) {
                Log.d(TAG, "Request the success");
                if (listener != null) {
                    listener.onSuccess(result.get("response"));
                }

            } else {
                if (listener != null) {
                    listener.onFail(new Exception());
                }
            }
        }

        public String convertStreamToString(InputStream inputStream) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream), 1024);
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return stringBuilder.toString();

        }
    }

    public interface HttpRequestCallback {
        void onSuccess(String response);
        void onFail(Exception e);
    }
}
