package ch.pianoben.bluetoothfortasker.receivers;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int prev_state = intent.getIntExtra(BluetoothA2dp.EXTRA_PREVIOUS_STATE, -1);
        int state = intent.getIntExtra(BluetoothA2dp.EXTRA_STATE, -1);

        if (state != prev_state && state == BluetoothA2dp.STATE_CONNECTED) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (device != null) {
                // TODO we have information with which to notify!
            }
        }
    }
}
