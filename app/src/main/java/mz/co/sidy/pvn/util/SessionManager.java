package mz.co.sidy.pvn.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import mz.co.sidy.pvn.model.Utilizador;

/**
 * Created by Simao on 10/20/2016.
 */
public class SessionManager {

    // User (make variable public to access from outside)
    public static final String KEY_USER = "user";
    // Sharedpref file name
    private static final String PREF_NAME = "PARQUE_VIATURA";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static SessionManager mInstance;
    // Shared Preferences
    private static SharedPreferences pref;
    // Editor for Shared preferences
    private static SharedPreferences.Editor editor;
    // Context
    private static Context _context;
    //ObjectMapper to convert objects to json
    private static ObjectMapper objectMapper;

    /**
     * @return
     */
    public static SessionManager getInstance() {
        if (mInstance == null)
            mInstance = new SessionManager();
        return mInstance;
    }

    /**
     * @param context
     */
    public static void init(Context context) {
        _context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        editor = pref.edit();
        objectMapper = new ObjectMapper();
    }

    /**
     * Create login session
     *
     * @param utilizador
     * @throws JsonProcessingException
     */
    public void createLoginSession(Utilizador utilizador) throws JsonProcessingException {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing the user in pref
        editor.putString(KEY_USER, objectMapper.writeValueAsString(utilizador));

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        /*// Check login status and session timeout.
        if (!this.isLoggedIn()) {
            // User is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }*/
    }

    /**
     * Get stored session data
     */
    public Utilizador getUserDetails() throws IOException {
        //convert json data to user value object.
        String userData = pref.getString(KEY_USER, null);

        if (userData != null) {
            Utilizador utilizador = objectMapper.readValue(userData, Utilizador.class);
            // return user
            return utilizador;
        }

        return null;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        /*// Clearing all data from Shared Preferences
        editor.remove(KEY_USER);
        editor.putBoolean(IS_LOGIN, false);
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);*/
    }

    /**
     * Quick check for login (Get Login State)
     **/
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
