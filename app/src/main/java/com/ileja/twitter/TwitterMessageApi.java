package com.ileja.twitter;

import com.twitter.sdk.android.core.Callback;

import retrofit.http.POST;
import retrofit.http.Query;


/**
 * Created by chentao on 16/6/7.
 */
public interface TwitterMessageApi {

    @POST("/1.1/direct_messages/new.json")
    void sendDirectMessage(@Query("user_id") long user_id, @Query("text") String text, Callback<MsgResponseEntity> callback);
}
