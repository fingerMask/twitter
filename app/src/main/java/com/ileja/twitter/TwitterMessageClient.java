package com.ileja.twitter;

import android.content.Context;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;

/**
 * Created by chentao on 16/6/7.
 */
public class TwitterMessageClient extends TwitterApiClient {
    /**
     * Must be instantiated after {@link TwitterCore} has been
     * initialized via {@link Fabric#with(Context, Kit[])}.
     *
     * @param session Session to be used to create the API calls.
     * @throws IllegalArgumentException if TwitterSession argument is null
     */
    public TwitterMessageClient(Session session) {
        super(session);
    }

    public TwitterMessageApi getMessageApi(){
        return getService(TwitterMessageApi.class);
    }
}
