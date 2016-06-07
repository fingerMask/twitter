package com.ileja.twitter;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.TweetEntities;

/**
 * Created by chentao on 16/6/7.
 */
public class MsgResponseEntity {
    String created_at;
    long id;
    String id_str;
    long sender_id;
    String sender_screen_name;
    String text;
    @SerializedName("entities")
    public TweetEntities entities;

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("id=").append(id).append("\r\n")
                .append("id_str=").append(id_str).append("\r\n")
                .append("sender_id=").append(sender_id).append("\r\n")
                .append("sender_name=").append(sender_screen_name).append("\r\n")
                .append("text=").append(text).append("\r\n");

        return sb.toString();
    }
}
