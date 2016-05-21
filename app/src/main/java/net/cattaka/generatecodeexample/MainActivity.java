package net.cattaka.generatecodeexample;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.cattaka.generatecodeexample.generated.IntentFilterHelper;

public class MainActivity extends AppCompatActivity {
    private TextView mDataText;
    private TextView mKeyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataText = (TextView) findViewById(R.id.text_data);
        mKeyText = (TextView) findViewById(R.id.text_key);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        refreshDataUrl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshDataUrl();
    }

    private void refreshDataUrl() {
        Uri uri = getIntent().getData();
        IntentFilterHelper helper = IntentFilterHelper.findHelper(uri);
        String key = (helper != null) ? helper.pickKey(uri) : null;

        mDataText.setText(String.valueOf(uri));
        mKeyText.setText(String.valueOf(key));
    }
}
