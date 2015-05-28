package ch.pianoben.bluetoothfortasker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ch.pianoben.bluetoothfortasker.adapters.A2DPDeviceAdapter;


public class EditActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ListView mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(new A2DPDeviceAdapter(this));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView tv_name = (TextView) view.findViewById(R.id.tv_device_name);
                TextView tv_address = (TextView) view.findViewById(R.id.tv_device_address);
                sendTaskerIntent(Constants.BT_EVENT_TYPES.A2DP_CONNECT.toString(),
                        tv_name.getText().toString(),
                        tv_address.getText().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, getString(R.string.no_device_selected), Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    public void sendTaskerIntent(String action, String name, String address) {
        Intent resultIntent = new Intent();

        Bundle result = new Bundle();
        result.putString(Constants.BT_EVENT_TYPE, action);
        result.putString(Constants.BT_DEVICE_NAME, name);
        result.putString(Constants.BT_DEVICE_ADDRESS, address);

        resultIntent.putExtra(com.twofortyfouram.locale.Intent.EXTRA_BUNDLE, result);
        resultIntent.putExtra(com.twofortyfouram.locale.Intent.EXTRA_STRING_BLURB, name+"/"+address);

        setResult(RESULT_OK, resultIntent);

        super.finish();
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return super.getSharedPreferences(name, Context.MODE_MULTI_PROCESS);
    }
}
