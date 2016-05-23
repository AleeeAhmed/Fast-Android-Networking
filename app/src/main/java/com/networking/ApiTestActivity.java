package com.networking;

import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.AndroidNetworkingError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.networking.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by amitshekhar on 30/03/16.
 */
public class ApiTestActivity extends AppCompatActivity {

    private static final String TAG = ApiTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);
    }

    public void getAllUsers(View view) {
        AndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_ARRAY)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse array : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void getAnUser(View view) {
        AndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_OBJECT)
                .addPathParameter("userId", "1")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse object : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void checkForHeaderGet(View view) {
        AndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.CHECK_FOR_HEADER)
                .addHeaders("token", "1234")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse object : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void checkForHeaderPost(View view) {
        AndroidNetworking.post(ApiEndPoint.BASE_URL + ApiEndPoint.CHECK_FOR_HEADER)
                .addHeaders("token", "1234")
                .setTag(this)
                .setPriority(Priority.LOW)
                .setExecutor(Executors.newSingleThreadExecutor())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse object : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void createAnUser(View view) {
        AndroidNetworking.post(ApiEndPoint.BASE_URL + ApiEndPoint.POST_CREATE_AN_USER)
                .addBodyParameter("firstname", "Suman")
                .addBodyParameter("lastname", "Shekhar")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse object : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }


    public void createAnUserJSONObject(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstname", "Rohit");
            jsonObject.put("lastname", "Kumar");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post(ApiEndPoint.BASE_URL + ApiEndPoint.POST_CREATE_AN_USER)
                .addJSONObjectBody(jsonObject)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse object : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void downloadFile(final View view) {
        String url = "http://www.colorado.edu/conflict/peace/download/peace_problem.ZIP";
        AndroidNetworking.download(url, Utils.getRootDirPath(getApplicationContext()), "file1.zip")
                .setPriority(Priority.IMMEDIATE)
                .setTag(this)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        Log.d(TAG, "bytesDownloaded : " + bytesDownloaded + " totalBytes : " + totalBytes);
                        Log.d(TAG, "setDownloadProgressListener isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Log.d(TAG, "File download Completed");
                        Log.d(TAG, "onDownloadComplete isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });


    }

    public void downloadImage(final View view) {
        String url = "http://i.imgur.com/AtbX9iX.png";
        AndroidNetworking.download(url, Utils.getRootDirPath(getApplicationContext()), "image1.png")
                .setPriority(Priority.MEDIUM)
                .setTag(this)
                .build()
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Log.d(TAG, "Image download Completed");
                        Log.d(TAG, "onDownloadComplete isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void uploadImage(final View view) {
        AndroidNetworking.upload(ApiEndPoint.UPLOAD_IMAGE_URL)
                .setPriority(Priority.MEDIUM)
                .addMultipartFile("image", new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "test.png"))
                .setTag(this)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        Log.d(TAG, "bytesUploaded : " + bytesUploaded + " totalBytes : " + totalBytes);
                        Log.d(TAG, "setUploadProgressListener isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Image upload Completed");
                        Log.d(TAG, "onResponse object : " + response.toString());
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void doNotCacheResponse(View view) {
        AndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_ARRAY)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .doNotCacheResponse()
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse array : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void getResponseOnlyIfCached(View view) {
        AndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_ARRAY)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .getResponseOnlyIfCached()
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse array : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void getResponseOnlyFromNetwork(View view) {
        AndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_ARRAY)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .getResponseOnlyFromNetwork()
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse array : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void setMaxAgeCacheControl(View view) {
        AndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_ARRAY)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .setMaxAgeCacheControl(10, TimeUnit.SECONDS)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse array : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void setMaxStaleCacheControl(View view) {
        AndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_ARRAY)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .setMaxStaleCacheControl(10, TimeUnit.SECONDS)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse array : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(AndroidNetworkingError error) {
                        if (error.hasErrorFromServer()) {
                            Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                        } else {
                            Log.d(TAG, "onError : " + error.getError());
                        }
                        Log.d(TAG, "onError isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

}
