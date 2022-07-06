package ir.ebookline.functions;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedFunctions {

    Context con;
    private static final String DefaultName = "shp";
    SharedPreferences shared;

    public SharedFunctions(Context con,String name) {
        this.con = con;
        this.shared = con.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public boolean setString (String key,String value){
        return shared.edit().putString(key,value).commit();
    }

    public String getString (String key){
        return shared.getString(key,null);
    }

    public boolean setInteger (String key,int value){
        return shared.edit().putInt(key,value).commit();
    }

    public int getInteger (String key){
        return shared.getInt(key,0);
    }

    public boolean setBoolean (String key,boolean value){
        return shared.edit().putBoolean(key,value).commit();
    }

    public boolean getBoolean (String key){
        return shared.getBoolean(key,false);
    }



}