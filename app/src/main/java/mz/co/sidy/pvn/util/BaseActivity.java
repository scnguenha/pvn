package mz.co.sidy.pvn.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Locale;

import mz.co.sidy.pvn.R;
import mz.co.sidy.pvn.activity.MenuPrincipal;
import mz.co.sidy.pvn.activity.login.LoginFireBase;
import mz.co.sidy.pvn.model.Coordenada;

/**
 * Created by Sidónio Goenha on 02/13/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected Context context = this;
    // protected ObjectMapper objectMapper = new ObjectMapperProvider().getContext();
    protected CommonFunctions cf = CommonFunctions.getInstance();
    //protected Data data = Data.getInstance();
    protected String LOG_TAG = "PVN";
    protected boolean firstLog = false;
    protected SessionManager session = SessionManager.getInstance();
    protected SharedPreferences sharedPreferences;
    protected DisplayMetrics dm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dm = context.getResources().getDisplayMetrics();

        /*if (!(context instanceof LoginActivity)) {
            session.checkLogin();
        }*/


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Keep the Screen On
        boolean keepScreenOn = sharedPreferences.getBoolean(AppConstants.KEEP_SCREEN_ON, false);
        if (keepScreenOn)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Verify Permissions
        if (Build.VERSION.SDK_INT >= 23)
            cf.verifyPermissions(this);

        loadLocale(context);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    protected void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Method which navigates from Login Activity to Home Activity
     */
    public void goToHomePage() {
        /*Intent homeIntent = new Intent(getApplicationContext(), MenuPrincipal.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);*/
    }

    public void goToMenuPrincipal() {
        Intent main = new Intent(getApplicationContext(), MenuPrincipal.class);
        main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
    }

    public void preencherSpinner(List<Object> list, ArrayAdapter<Object> adapter) {
        adapter.clear();

        for (Object object : list) {
            adapter.add(object);
        }
    }

    protected void checkRadioButtonByTag(RadioGroup radioGroup, String tag) {
        RadioButton rb = (RadioButton) radioGroup.findViewWithTag(tag);

        if (rb != null)
            rb.setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return false;
    }

    protected void mensagem(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        Log.d(msg, msg);
    }


    protected void clearBackstack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(0);

            getSupportFragmentManager().popBackStack(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    protected String getOriginalImagePath(Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

        int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index_data);
    }

    protected void showSnackBar(String msg, View view, int color) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(color);
        snackbar.show();
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    /**
     * @param context - O contexto.
     */
    @SuppressLint("NewApi")
    private void loadLocale(Context context) {
        String language = sharedPreferences.getString("language", "pt");
        Locale myLocale = new Locale(language);
        Locale.setDefault(myLocale);
        Configuration config = context.getResources().getConfiguration();
        config.setLocale(myLocale);
        context.getResources().updateConfiguration(config, dm);
    }

    protected boolean getLocationStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    protected void showGPSSettingsAlert(final Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.gps_disabled_title);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(R.string.gps_disabled_Msg);
        // On pressing Settings button
        alertDialog.setPositiveButton(R.string.setting_btn, (dialog, which) -> {
            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        });

        // Showing Alert Message
       /* if (!((Activity) context).isFinishing())
            alertDialog.show();*/
    }


    protected void showCoordinates(Coordenada ponto) {
        LayoutInflater li = LayoutInflater.from(this);
        View v = li.inflate(R.layout.dialog_ponto, null);

        ((EditText) v.findViewById(R.id.edtLat)).setText(String.valueOf(ponto.getLatitude()));
        ((EditText) v.findViewById(R.id.edtLong)).setText(String.valueOf(ponto.getLongitude()));
        ((EditText) v.findViewById(R.id.edtPrec)).setText(String.valueOf(ponto.getPrecisao()));

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(v);
        dialog.setCancelable(true);
        dialog.show();
    }

    protected void mensagem(String msg, String type, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);
        builder.setTitle(type);
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }

    protected void signOut() {
        // this listener will be called when there is change in firebase user session
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginFireBase.class));
        finish();
    }

    protected boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    protected boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}
