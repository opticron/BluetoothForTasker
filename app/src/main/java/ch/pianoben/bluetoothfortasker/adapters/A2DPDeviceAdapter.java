package ch.pianoben.bluetoothfortasker.adapters;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.pianoben.bluetoothfortasker.R;

/**
 * Created by opticron on 4/15/15.
 * This holds a self-populating array adapter that contains a2dp devices.
 */
public class A2DPDeviceAdapter extends ArrayAdapter<BluetoothDevice> {
    final int[]states = new int[]{
            BluetoothA2dp.STATE_CONNECTED,
            BluetoothA2dp.STATE_CONNECTING,
            BluetoothA2dp.STATE_DISCONNECTED,
            BluetoothA2dp.STATE_DISCONNECTING,
            BluetoothA2dp.STATE_NOT_PLAYING,
            BluetoothA2dp.STATE_PLAYING
    };

    class A2dpListener implements BluetoothProfile.ServiceListener {
        @Override
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            BluetoothA2dp a2dp_proxy = (BluetoothA2dp)bluetoothProfile;
            clear();

            List<BluetoothDevice> bt_list;
            bt_list = a2dp_proxy.getDevicesMatchingConnectionStates(states);
            addAll(bt_list);
        }

        @Override
        public void onServiceDisconnected(int i) {
        }
    }

    Context mContext;
    public A2DPDeviceAdapter(Context context) {
        super(context, R.layout.bt_list_item);
        mContext = context;

        BluetoothManager btmanager = (BluetoothManager)context.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter btadapter = btmanager.getAdapter();
        btadapter.getProfileProxy(context.getApplicationContext(), new A2dpListener(), BluetoothProfile.A2DP);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BluetoothDevice device = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bt_list_item, parent, false);
        }

        TextView tv_device_name = (TextView)convertView.findViewById(R.id.tv_device_name);
        TextView tv_device_address = (TextView)convertView.findViewById(R.id.tv_device_address);

        tv_device_name.setText(device.getName());
        tv_device_address.setText(device.getAddress());

        return convertView;
    }
}
