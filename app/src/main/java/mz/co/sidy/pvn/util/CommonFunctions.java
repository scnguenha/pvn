package mz.co.sidy.pvn.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.view.menu.MenuPopupHelper;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import mz.co.sidy.pvn.R;

/**
 * Created by Simao on 10/31/2016.
 */
public class CommonFunctions {

    public static final String parentFolderName = "SIGIT-CAMPO";
    public static final String dataFolderName = "spatialdata";
    public static final String dbFolderName = "database";
    public static final String mediaFolderName = "multimedia";
    // Permissions variables
    private static final int REQUEST_PERMISSIONS = 1;
    private static CommonFunctions mInstance;
    private static Context mContext;
    private static SharedPreferences mMyPreferences;
    private static SharedPreferences.Editor mEditor;
    private static String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE
    };
    String appLogFileName = Environment.getExternalStorageDirectory() + "/SIGIT-CAMPO/SIGIT-CAMPOApp_LOG.txt";
    String syncLogFileName = Environment.getExternalStorageDirectory() + "/SIGIT-CAMPO/SIGIT-CAMPOSync_LOG.txt";

    public static CommonFunctions getInstance() {
        if (mInstance == null)
            mInstance = new CommonFunctions();
        return mInstance;
    }

    public static void init(Context ctxt) {
        mContext = ctxt;
        mMyPreferences = mContext.getSharedPreferences("SIGIT-CAMPO", Activity.MODE_PRIVATE);
        mEditor = mMyPreferences.edit();
    }

    // Convert InputStream to String
    public static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * \
     *
     * @param popupMenu
     */
    @SuppressLint("RestrictedApi")
    public static void forceIconMenuShow(android.support.v7.widget.PopupMenu popupMenu) {
        try {
            Field mFieldPopup = popupMenu.getClass().getDeclaredField("mPopup");
            mFieldPopup.setAccessible(true);
            MenuPopupHelper mPopup = (MenuPopupHelper) mFieldPopup.get(popupMenu);
            mPopup.setForceShowIcon(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Date getDateFromDatePicker(DatePicker datePicker) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        return calendar.getTime();
    }

    //persmission method.
    public void verifyPermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int fineLocationPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        int internetPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.INTERNET);
        int cameraPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        int networkStatePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_NETWORK_STATE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED || fineLocationPermission != PackageManager.PERMISSION_GRANTED || internetPermission != PackageManager.PERMISSION_GRANTED || cameraPermission != PackageManager.PERMISSION_GRANTED || networkStatePermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS,
                    REQUEST_PERMISSIONS
            );
        }
    }

    public void exitApplication(Context cntxt) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        cntxt.startActivity(intent);
    }

    public void appLog(String Tag, Exception e) {
        try {
            PrintWriter pw = new PrintWriter(new File(appLogFileName));
            e.printStackTrace(pw);
            pw.append("##----------------------------------------------##");
            pw.append(new Date().toString());
            pw.close();
        } catch (Exception e1) {
        }
    }

    public void syncLog(String Tag, Exception e) {
        try {
            PrintWriter pw = new PrintWriter(new File(syncLogFileName));
            e.printStackTrace(pw);
            pw.append("##----------------------------------------------##");
            pw.append(new Date().toString());
            pw.close();
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public SharedPreferences getmMyPreferences() {
        if (mMyPreferences == null) {
            init(mContext.getApplicationContext());
        }
        return mMyPreferences;
    }

    public void addErrorMessage(String Tag, String message) {
        try {
            FileWriter fw = new FileWriter(new File(syncLogFileName));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Tag + " " + new Date().toString() + " :>>>> " + message);
            bw.append("##----------------------------------------------##");
            bw.close();
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void createLogfolder() {
        try {
            String extPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File parentdir = new File(extPath + "/" + parentFolderName);
            File datadir = new File(extPath + "/" + parentFolderName + "/" + dataFolderName);
            File dbdir = new File(extPath + "/" + parentFolderName + "/" + dbFolderName);
            File mediadir = new File(extPath + "/" + parentFolderName + "/" + mediaFolderName);

            if (!parentdir.exists() || !parentdir.isDirectory()) {
                parentdir.mkdirs();
            }

            if (!datadir.exists() || !datadir.isDirectory()) {
                datadir.mkdirs();
            }
            if (!dbdir.exists() || !dbdir.isDirectory()) {
                dbdir.mkdirs();
            }
            if (!mediadir.exists() || !mediadir.isDirectory()) {
                mediadir.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage(Context cntxt, String header, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(cntxt);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setTitle(header);

        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.show();
    }

    @SuppressLint("MissingPermission")
    public String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public boolean getConnectivityStatus() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return true;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
        }
        return false;
    }

    public boolean getLocationStatus() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        boolean statusOfGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return statusOfGPS;
    }

    public void showIntenetSettingsAlert(final Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.internet_disabled_title);
        alertDialog.setMessage(R.string.internet_disabled_msg);
        // On pressing Settings button
        alertDialog.setPositiveButton(R.string.setting_btn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }

    public void showGPSSettingsAlert(final Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.gps_disabled_title);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(R.string.gps_disabled_Msg);
        // On pressing Settings button
        alertDialog.setPositiveButton(R.string.setting_btn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }

    /**
     * Sets ListView height dynamically based on the height of the items.
     *
     * @param listView to be resized
     * @return true if the listView is successfully resized, false otherwise
     */
    public boolean setListViewHeightBasedOnItems(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter != null) {
            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;
        } else {
            return false;
        }
    }

    /**
     * Put values into shared preferences.
     *
     * @param name
     * @param value
     */
    public void storeInfo(String name, Object value) {
        // Storing the value in pref

        if (value instanceof Boolean) {
            mEditor.putBoolean(name, (Boolean) value);
        } else if (value instanceof Float) {
            mEditor.putFloat(name, (Float) value);
        } else if (value instanceof Integer) {
            mEditor.putInt(name, (Integer) value);
        } else if (value instanceof Long) {
            mEditor.putLong(name, (Long) value);
        } else if (value instanceof String) {
            mEditor.putString(name, (String) value);
        }

        // commit changes
        mEditor.commit();
    }

    //public boolean isExternalStorageAvailable() {
     /*   Device[] devices = Environment3.getDevices(null, true, true, false);

        if (devices.length <= 1)
            return false;
        else
            return true;
    }*/
}
