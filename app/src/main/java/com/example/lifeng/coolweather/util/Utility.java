package com.example.lifeng.coolweather.util;

import android.text.TextUtils;


import com.example.lifeng.coolweather.db.City;
import com.example.lifeng.coolweather.db.County;
import com.example.lifeng.coolweather.db.Province;
import com.example.lifeng.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by lifeng on 2017/5/27 0027.
 */

public class Utility {
    public static boolean handleProviceRes(String res){
        if(!TextUtils.isEmpty(res)){
            try {
                JSONArray allProvinces=new JSONArray(res);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }
    public static boolean handleCityRes(String res,int pId) {
        if(!TextUtils.isEmpty(res)){
            try {
                JSONArray allCities=new JSONArray(res);
                for (int i=0;i<allCities.length();i++){
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(pId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }
    public static boolean handleCountyRes(String res,int cId) {
        if(!TextUtils.isEmpty(res)){
            try {
                JSONArray allCounties=new JSONArray(res);
                for (int i=0;i<allCounties.length();i++){
                    JSONObject countObj=allCounties.getJSONObject(i);
                    County county =new County();
                    county.setCountyName(countObj.getString("name"));
                    county.setWeatherId(countObj.getString("weather_id"));
                    county.setCityId(cId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }
    public static Weather handleWeatherRes(String res) {
        try {
            JSONObject jsonObject=new JSONObject(res);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
