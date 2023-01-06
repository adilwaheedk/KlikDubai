package com.khanstech.shop.klikdubai.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.khanstech.shop.klikdubai.models.Product;

/**
 * Helper class to store preference data of application
 */
public class PreferenceHelper {

    // Constant Keys values
    private static final String AGENT_PREFIX = "AgentSession_";
    public static final String CURRENT_AIRPORTS = "Current_Airports";
    public static final String CURRENT_LOCATION = "Current_Location";
    public static final String LOGGED_EMAIL = "last_logged_agent_email";
    public static final String LOGGED_PASS = "keep_logged_agent_pass";
    public static final String LOGGED_USER_TYPE = "keep_logged_user_type";
    public static final String KEEP_LOGGED = "keep_logged";

    private static SharedPreferences sharedPrefs;

    /**
     * Save String type value
     *
     * @param ctx   Context
     * @param key   Name of the preference
     * @param value Value of the preference
     */
    public static void setString(Context ctx, String key, String value) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        Editor edit = sharedPrefs.edit();
        edit.putString(key, value);
        edit.commit();
    }

    /**
     * Get String type value
     *
     * @param ctx Context
     * @param key Name of the preference
     * @return Value of the preference
     */
    public static String getString(Context ctx, String key) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sharedPrefs.getString(key, "");
    }

    /**
     * Save Integer type value
     *
     * @param ctx   Context
     * @param key   Name of the preference
     * @param value Value of the preference
     */
    public static void setInt(Context ctx, String key, int value) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        Editor edit = sharedPrefs.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    /**
     * Get Integer type value
     *
     * @param ctx Context
     * @param key Name of the preference
     * @return Value of the preference
     */
    public static int getInt(Context ctx, String key) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sharedPrefs.getInt(key, 0);
    }

    /**
     * Save List of String
     *
     * @param ctx   Context
     * @param key   Name of the preference
     * @param value Value of the preference
     */
    public static void setListValues(Context ctx, String key, List<String> value) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        Editor edit = sharedPrefs.edit();
        Set<String> set = new HashSet<>(value);
        edit.putStringSet(key, set);
        edit.commit();
    }

    /**
     * Get List of String
     *
     * @param ctx Context
     * @param key Name of the preference
     * @return Value of the preference
     */
    public static List<String> getListValues(Context ctx, String key) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        List<String> list = new ArrayList<>();
        list.addAll(sharedPrefs.getStringSet(key, null));
        return list;
    }

//    /**
//     * Save Logged User Properties
//     *
//     * @param ctx Context
//     * @param obj AgentSession object
//     */
//    public static void setAgentSession(Context ctx, AgentSession obj) {
//        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
//        Editor edit = sharedPrefs.edit();
//        edit.putString(AGENT_PREFIX + "AGENT_USER_ID", obj.AGENT_USER_ID);
//        edit.putString(AGENT_PREFIX + "AGENT_PARENT_ID", obj.AGENT_PARENT_ID);
//        edit.putString(AGENT_PREFIX + "AGENT_PARENT_OF_PARENT_ID", obj.AGENT_PARENT_OF_PARENT_ID);
//        edit.putString(AGENT_PREFIX + "AGENT_ID", obj.AGENT_ID);
//        edit.putString(AGENT_PREFIX + "AGENT_NAME", obj.AGENT_NAME);
//        edit.putString(AGENT_PREFIX + "AGENT_EMAIL", obj.AGENT_EMAIL);
//        edit.putString(AGENT_PREFIX + "AGENT_RIGHTS", obj.AGENT_RIGHTS);
//        edit.putString(AGENT_PREFIX + "AGENT_LOGED_IN_TYPE", obj.AGENT_LOGED_IN_TYPE);
//        edit.putString(AGENT_PREFIX + "AGENT_LOGGED_IN", obj.AGENT_LOGGED_IN);
//        edit.putString(AGENT_PREFIX + "AGENT_AGENCY_USER", obj.AGENT_AGENCY_USER);
//        edit.putString(AGENT_PREFIX + "AMS_AGENT_CODE", obj.AMS_AGENT_CODE);
//        edit.putString(AGENT_PREFIX + "RT_AGENT_KEY", obj.RT_AGENT_KEY);
//        edit.putString(AGENT_PREFIX + "phoneNo", obj.phoneNo);
//        edit.putString(AGENT_PREFIX + "mobileNo", obj.mobileNo);
//        edit.putString(AGENT_PREFIX + "managermobile", obj.managermobile);
//        edit.putString(AGENT_PREFIX + "accountmobile", obj.accountmobile);
//        edit.putString(AGENT_PREFIX + "ownermobile", obj.ownermobile);
//        edit.putString(AGENT_PREFIX + "creditLimit", obj.creditLimit);
//        edit.putString(AGENT_PREFIX + "tmpcreditLimit", obj.tmpcreditLimit);
//        edit.putString(AGENT_PREFIX + "currentCreditLimit", obj.currentCreditLimit);
//        edit.commit();
//    }
//
//    /**
//     * Get Logged User Properties
//     *
//     * @param ctx Context
//     * @return AgentSession object
//     */
//    public static AgentSession getAgentSession(Context ctx) {
//        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
//        AgentSession agent = new AgentSession();
//        agent.AGENT_USER_ID = sharedPrefs.getString(AGENT_PREFIX + "AGENT_USER_ID", "");
//        agent.AGENT_PARENT_ID = sharedPrefs.getString(AGENT_PREFIX + "AGENT_PARENT_ID", "");
//        agent.AGENT_PARENT_OF_PARENT_ID = sharedPrefs.getString(AGENT_PREFIX + "AGENT_PARENT_OF_PARENT_ID", "");
//        agent.AGENT_ID = sharedPrefs.getString(AGENT_PREFIX + "AGENT_ID", Constants.GUEST_ID);
//        agent.AGENT_NAME = sharedPrefs.getString(AGENT_PREFIX + "AGENT_NAME", Constants.GUEST_NAME);
//        agent.AGENT_EMAIL = sharedPrefs.getString(AGENT_PREFIX + "AGENT_EMAIL", "");
//        agent.AGENT_RIGHTS = sharedPrefs.getString(AGENT_PREFIX + "AGENT_RIGHTS", "");
//        agent.AGENT_LOGED_IN_TYPE = sharedPrefs.getString(AGENT_PREFIX + "AGENT_LOGED_IN_TYPE", "");
//        agent.AGENT_LOGGED_IN = sharedPrefs.getString(AGENT_PREFIX + "AGENT_LOGGED_IN", "");
//        agent.AGENT_AGENCY_USER = sharedPrefs.getString(AGENT_PREFIX + "AGENT_AGENCY_USER", "");
//        agent.AMS_AGENT_CODE = sharedPrefs.getString(AGENT_PREFIX + "AMS_AGENT_CODE", "");
//        agent.RT_AGENT_KEY = sharedPrefs.getString(AGENT_PREFIX + "RT_AGENT_KEY", Constants.GUEST_KEY);
//        agent.phoneNo = sharedPrefs.getString(AGENT_PREFIX + "phoneNo", "");
//        agent.mobileNo = sharedPrefs.getString(AGENT_PREFIX + "mobileNo", "");
//        agent.managermobile = sharedPrefs.getString(AGENT_PREFIX + "managermobile", "");
//        agent.accountmobile = sharedPrefs.getString(AGENT_PREFIX + "accountmobile", "");
//        agent.ownermobile = sharedPrefs.getString(AGENT_PREFIX + "ownermobile", "");
//        agent.creditLimit = sharedPrefs.getString(AGENT_PREFIX + "creditLimit", "");
//        agent.tmpcreditLimit = sharedPrefs.getString(AGENT_PREFIX + "tmpcreditLimit", "");
//        agent.currentCreditLimit = sharedPrefs.getString(AGENT_PREFIX + "currentCreditLimit", "");
//        return agent;
//    }

//    /**
//     * Clear preference values of Logged User
//     *
//     * @param ctx Context
//     * @return true if success, else false
//     */
//    public static boolean clearAgentSession(Context ctx) {
//        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
//        Editor edit = sharedPrefs.edit();
//        edit.remove(AGENT_PREFIX + "AGENT_USER_ID");
//        edit.remove(AGENT_PREFIX + "AGENT_PARENT_ID");
//        edit.remove(AGENT_PREFIX + "AGENT_PARENT_OF_PARENT_ID");
//        edit.remove(AGENT_PREFIX + "AGENT_ID");
//        edit.remove(AGENT_PREFIX + "AGENT_NAME");
//        edit.remove(AGENT_PREFIX + "AGENT_EMAIL");
//        edit.remove(AGENT_PREFIX + "AGENT_RIGHTS");
//        edit.remove(AGENT_PREFIX + "AGENT_LOGED_IN_TYPE");
//        edit.remove(AGENT_PREFIX + "AGENT_USER_ID");
//        edit.remove(AGENT_PREFIX + "AGENT_LOGGED_IN");
//        edit.remove(AGENT_PREFIX + "AMS_AGENT_CODE");
//        edit.remove(AGENT_PREFIX + "RT_AGENT_KEY");
//        edit.remove(AGENT_PREFIX + "phoneNo");
//        edit.remove(AGENT_PREFIX + "mobileNo");
//        edit.remove(AGENT_PREFIX + "managermobile");
//        edit.remove(AGENT_PREFIX + "accountmobile");
//        edit.remove(AGENT_PREFIX + "ownermobile");
//        edit.remove(AGENT_PREFIX + "creditLimit");
//        edit.remove(AGENT_PREFIX + "tmpcreditLimit");
//        edit.remove(AGENT_PREFIX + "currentCreditLimit");
//        return edit.commit();
//    }

    /**
     * Clear specific value from preference
     *
     * @param ctx Context
     * @param key Name of the preference
     * @return true if success, else false
     */
    public static boolean clearValue(Context ctx, String key) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        Editor edit = sharedPrefs.edit();
        edit.remove(key);
        return edit.commit();
    }

    /**
     * Clear specific list of values from preference
     *
     * @param ctx  Context
     * @param keys List of names of the preference
     * @return true if success, else false
     */
    public static boolean clearValues(Context ctx, String[] keys) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        Editor edit = sharedPrefs.edit();
        for (String key : keys) {
            edit.remove(key);
        }
        return edit.commit();
    }
}
