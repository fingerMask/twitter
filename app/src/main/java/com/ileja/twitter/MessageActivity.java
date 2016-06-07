package com.ileja.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MessageActivity extends AppCompatActivity {

    private long uid;
    @Bind(R.id.et_message)
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        uid = intent.getLongExtra("uid", 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_send)
    public void onUpdate(){
        String text = etMessage.getText().toString();
        String message = "";
        try {
            message = URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(TextUtils.isEmpty(message)){
            Toast.makeText(this, "message is empty", Toast.LENGTH_LONG).show();
            return;
        }

        StatusesService statusesService = TwitterCore.getInstance().getApiClient().getStatusesService();
        statusesService.update(text, 0L, false, 39.20, 116.40, null, true, false, null, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                Timber.d("send success = \r\n%s", result.data.toString());
            }

            @Override
            public void failure(TwitterException exception) {
                Timber.d("send failure = %s", exception.getMessage());
            }
        });
    }


    public void onSendMessage(){

        String text = etMessage.getText().toString();
        String message = "";
        try {
            message = URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(TextUtils.isEmpty(message)){
            Toast.makeText(this, "message is empty", Toast.LENGTH_LONG).show();
            return;
        }

        Session session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if(session == null){
            session = TwitterCore.getInstance().getAppSessionManager().getActiveSession();
        }
        TwitterMessageClient twitterMessageClient = new TwitterMessageClient(session);
        twitterMessageClient.getMessageApi().sendDirectMessage(uid, message, new Callback<MsgResponseEntity>() {
            @Override
            public void success(Result<MsgResponseEntity> result) {
                Timber.d("send success = \r\n%s", result.data.toString());
            }

            @Override
            public void failure(TwitterException exception) {
                Timber.d("send failure = %s", exception.getMessage());
            }
        });
    }
}
