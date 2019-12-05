package com.temple.ne.basecomponent.retrofit;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    /**
     * @param value
     * @return
     * @throws IOException
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        String result = value.string();

        try {

            Log.d("GsonResponseBodyConvert", result);
            if (!TextUtils.isEmpty(result) && !result.startsWith("{")) {
                result = DESUtil.decrypt(result, Constants.KEY);
                MediaType mediaType = value.contentType();
                ResponseBody.create(mediaType, result);
                JsonReader jsonReader2 = gson.newJsonReader(value.charStream());
                try {
                    return adapter.read(jsonReader2);
                } finally {
                    value.close();
                }
            } else {
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.has("resText")) {
                    String resText = jsonObject.getString("resText");
                    String isEnc = jsonObject.getString("isEnc");
                    if ("Y".equals(isEnc)) {
                        result = DESUtil.decrypt(resText, Constants.KEY);
                        MediaType mediaType = value.contentType();
                        ResponseBody responseBody = ResponseBody.create(mediaType, result);
                        JsonReader jsonReader2 = gson.newJsonReader(responseBody.charStream());
                        try {
                            return adapter.read(jsonReader2);
                        } finally {
                            value.close();
                        }
                    }
                }
            }

        } catch (Exception e) {
            String message = e.getMessage();
            try {

            } catch (Exception e2) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }

            }

        }


        JsonReader jsonReader = gson.newJsonReader( ResponseBody.create(value.contentType(), result).charStream());

        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }


}