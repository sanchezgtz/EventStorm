package com.tahsan.eventstorm.utilerias;

import android.content.Context;
import android.content.SharedPreferences;

import com.tahsan.eventstorm.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utileria {

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void savePreference_String(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreference_String(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_preferences), Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void deletePreference_String(Context context, String key){
        SharedPreferences settings = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        settings.edit().remove(key).commit();
    }

}
