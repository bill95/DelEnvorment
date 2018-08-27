package com.temple.ne.delenvorment.app;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {
    private Gson gson;

    @Override
    public void init(Context context) {
        gson = new Gson();
    }

    @Override
    public <T> T json2Object(String text, Class<T> clazz) {


        return gson.fromJson(text, clazz);

    }

    @Override
    public String object2Json(Object instance) {
        return gson.toJson(instance);
    }

    /**
     * Parse json to object
     *
     * @param input json string
     * @param clazz object type
     * @return instance of object
     */
    @Override
    public <T> T parseObject(String input, Type clazz) {
        return gson.fromJson(input, clazz);
    }
}