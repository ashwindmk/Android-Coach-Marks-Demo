package com.example.coachmarks;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    private int mSpacing = 20, mButtonHeight = 0, mButtonWidth = 0, mButton1Bottom = 0, mButton1Left = 0, mButton2Bottom = 0, mButton2Left = 0, mButton3Bottom = 0, mButton3Left = 0, mTextViewHeight = 200, mTextViewTop = 0, mTextViewLeft = 0;

    private Button mButton1, mButton2, mButton3;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        initViews();

        buildOverlay();
    }

    private void initViews() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mTextView = (TextView) findViewById(R.id.textView);
    }

    private void buildOverlay() {
        mButton1.post(new Runnable() {
            @Override
            public void run() {
                mButtonHeight = mButton1.getHeight();
                mButtonWidth = mButton1.getWidth();
                mButton1Bottom = mButton1.getBottom();
                mButton1Left = mButton1.getLeft();
                //Log.w(TAG, "button1 left : " + mButton1.getLeft() + ", right : " + mButton1.getRight() + ", top : " + mButton1.getTop() + ", bottom : " + mButton1.getBottom() + ", height : " + mButton1.getHeight());
            }
        });

        mButton2.post(new Runnable() {
            @Override
            public void run() {
                mButton2Bottom = mButton2.getBottom();
                mButton2Left = mButton2.getLeft();
                //Log.w(TAG, "button2 left : " + mButton2.getLeft() + ", right : " + mButton2.getRight() + ", top : " + mButton2.getTop() + ", bottom : " + mButton2.getBottom() + ", height : " + mButton2.getHeight());
            }
        });

        mButton3.post(new Runnable() {
            @Override
            public void run() {
                mButton3Bottom = mButton3.getBottom();
                mButton3Left = mButton3.getLeft();
                //Log.w(TAG, "button3 left : " + mButton3.getLeft() + ", right : " + mButton3.getRight() + ", top : " + mButton3.getTop() + ", bottom : " + mButton3.getBottom() + ", height : " + mButton3.getHeight());
            }
        });

        mTextView.post(new Runnable() {
            @Override
            public void run() {
                mTextViewHeight = mTextView.getHeight();
                mTextViewTop = mTextView.getTop();
                mTextViewLeft = mTextView.getLeft();
                //Log.w(TAG, "textview left : " + mTextView.getLeft() + ", right : " + mTextView.getRight() + ", top : " + mTextView.getTop() + ", bottom : " + mTextView.getBottom() + ", height : " + mTextView.getHeight());
                showOverlay();
            }
        });
    }

    private void showOverlay() {
        final Dialog dialog = new Dialog(mContext, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(R.layout.overlay_view);
        RelativeLayout layout = (RelativeLayout) dialog.findViewById(R.id.overlayLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });

        dialog.show();

        LinearLayout button1Layout = (LinearLayout) dialog.findViewById(R.id.button1Layout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(mButton1Left + mButtonWidth/2, mButton1Bottom + mButtonHeight + mSpacing, 0, 0);
        button1Layout.setLayoutParams(params);

        LinearLayout button2Layout = (LinearLayout) dialog.findViewById(R.id.button2Layout);
        params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(mButton2Left + mButtonWidth/2, mButton1Bottom + mButtonHeight + mSpacing, 0, 0);
        button2Layout.setLayoutParams(params);

        LinearLayout button3Layout = (LinearLayout) dialog.findViewById(R.id.button3Layout);
        params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(mButton3Left + mButtonWidth/2, mButton1Bottom + mButtonHeight + mSpacing, 0, 0);
        button3Layout.setLayoutParams(params);

        LinearLayout textLayout = (LinearLayout) dialog.findViewById(R.id.textLayout);
        params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(mTextViewLeft, mTextViewTop - mTextViewHeight - mSpacing, 0, 0);
        textLayout.setLayoutParams(params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_help) {
            showOverlay();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
