package com.launcher.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.root.cossb.MainActivity;
import java.util.ArrayList;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class Launcher extends CordovaPlugin {
    public static String name;
    JSONArray jsonArray = new JSONArray();
    ArrayList<String> name1 = new ArrayList();
    public ArrayList<String> name3 = new ArrayList();
    String namePassword;
    String password;

    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        if (!action.equals("launch")) {
            return false;
        }
        name = data.getString(0);
        String message = "Hello, " + name;
        System.out.println("back button Intent" + name);
        if (name.equals("com.skubbs.ml")) {
            try {
                Bundle extras = this.cordova.getActivity().getIntent().getExtras();
                if (MainActivity.isOnpausecalled) {
                    this.name1.clear();
                    this.jsonArray = new JSONArray();
                    this.name1 = new ArrayList();
                    this.jsonArray.put(this.name1);
                } else {
                    this.name1.clear();
                    this.jsonArray = new JSONArray();
                    this.name1 = extras.getStringArrayList("message");
                    this.jsonArray.put(this.name1);
                }
                if (!this.name1.equals("null")) {
                    callbackContext.success(this.jsonArray);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            callbackContext.success(this.jsonArray);
            return true;
        }
        int i = 0;
        while (i < data.length()) {
            try {
                this.name3.add(data.get(i).toString());
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this.cordova.getActivity(), "App Not Found: ", 1).show();
            }
        }
        Intent i2 = new Intent("android.intent.action.MAIN");
        i2.addCategory("android.intent.category.HOME");
        i2.setFlags(536870912);
        this.cordova.getActivity().startActivity(this.cordova.getActivity().getPackageManager().getLaunchIntentForPackage("com.skubbs.ml"));
        this.name3.clear();
        callbackContext.success(this.jsonArray);
        return true;
    }
}
