package ch.pianoben.bluetoothfortasker;

import android.content.Context;

/**
 * Created by opticron on 4/30/15.
 * Constants for use by tasker
 */
public class Constants {
    /**
     * Log tag for logcat messages.
     */
    public static final String LOG_TAG = "BTForTasker"; //$NON-NLS-1$

    /**
     * Constant for event type
     */
    public static final String BT_EVENT_TYPE = "BT_EVENT_TYPE";

    /**
     * BT Event Types
     */
    public static enum BT_EVENT_TYPES {
        A2DP_CONNECT,
        A2DP_DISCONNECT,
        BT_CONNECT,
        BT_DISCONNECT,
    }

    /**
     * Constant for device name
     */
    public static final String BT_DEVICE_NAME = "BT_DEVICE_NAME";

    /**
     * Constant for device address
     */
    public static final String BT_DEVICE_ADDRESS = "BT_DEVICE_ADDRESS";

    /**
     * Flag to enable logcat messages.
     */
    public static final boolean IS_LOGGABLE = BuildConfig.DEBUG;

    /**
     * Flag to enable runtime checking of method parameters.
     */
    public static final boolean IS_PARAMETER_CHECKING_ENABLED = BuildConfig.DEBUG;

    /**
     * Flag to enable runtime checking of whether a method is called on the correct thread.
     */
    public static final boolean IS_CORRECT_THREAD_CHECKING_ENABLED = BuildConfig.DEBUG;

    /**
     * Determines the "versionCode" in the {@code AndroidManifest}.
     *
     * @param context to read the versionCode.
     * @return versionCode of the app.
     */
    public static int getVersionCode(final Context context)
    {
        if (Constants.IS_PARAMETER_CHECKING_ENABLED)
        {
            if (null == context)
            {
                throw new IllegalArgumentException("context cannot be null"); //$NON-NLS-1$
            }
        }

        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (final UnsupportedOperationException e) {
            /*
             * This exception is thrown by test contexts
             */

            return 1;
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Private constructor prevents instantiation.
     *
     * @throws UnsupportedOperationException because this class cannot be instantiated.
     */
    private Constants()
    {
        throw new UnsupportedOperationException("This class is non-instantiable"); //$NON-NLS-1$
    }
}
