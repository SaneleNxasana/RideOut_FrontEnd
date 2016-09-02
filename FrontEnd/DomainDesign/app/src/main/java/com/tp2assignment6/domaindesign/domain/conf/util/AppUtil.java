package com.tp2assignment6.domaindesign.domain.conf.util;

/**
 * Created by NXA-C.unltd on 2016/07/25.
 */
public class AppUtil {
/*
    public static String getStringLocation(Map<String, String> location){
        Gson gson = new Gson();
        return gson.toJson(location);
    }
    public static Map<String, String> getLocation(String value){
        final Gson gson = new Gson();
        Type typeOfHashMap = new TypeToken<Map<String, String>>(){
        }.getType();
        return gson.fromJson(value, typeOfHashMap);
    }

    public static Date getdate(string date){
        SimpledateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date value = null;
        try{
            value = formatter.parse(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return value;
    }

    public static Map<String ,Integer> getValue(String value){
        final Gson gson = new Gson();
        Type typeOfHashMap = new TypeToken<Map<String, Integer>>(){
        }.getType();
        return gson.fromJson(value, typeOfHashMap);
    }

    public static String getStringValue(Map<String, Integer> value){
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    public static String getBaserURL(){
        return "http:// /";
    }

    public static OkHttpClient getConnection(){
        return new OkHttpClient();
    }

    public static MediaType getJSONMediaType(){
        return MediaType.parse("application/json; charset=utf-8");
    }

    public static byte[] getImage(String imageUrl){
        byte[] data = null;
        Request request = new Request.Builder().url(imageUrl)
                .build();
        try{
            Response response = getConnection().newCall(request).execute();
            InputStream input = response.body().byteStream();
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while((bytesRead = input.read(buffer)) != -1){
                output.write(buffer, 0, bytesRead);
            }
            output.flush();
            output.close();
            input.close();
            data = output.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }
*/
}
