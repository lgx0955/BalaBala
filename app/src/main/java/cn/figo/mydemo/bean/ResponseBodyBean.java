package cn.figo.mydemo.bean;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.figo.mydemo.utils.Const;
import cn.figo.mydemo.utils.JSONUtil;


/**
 * User: Ligx
 * Date: 2015-10-28
 * Time: 11:19
 * Copyright (c)Ligx All rights reserved.
 */
public class ResponseBodyBean<T> extends BaseBean{

    static {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        gsonDefault = new GsonBuilder().enableComplexMapKeySerialization().create();
    }

    @Expose
    static Gson gson;
    @Expose
    static Gson gsonDefault;
    @Expose
    public String result;
    @Expose
    public boolean isCache;
    @Expose
    public JSONObject jo;

    public int status;
    public String info;
    public T data;

    public ResponseBodyBean(String result) {
        this.result = result;
        this.status = 0;
        // 默认处理结果为 true
        // 有返回success code 登按 返回结果
        if (!TextUtils.isEmpty(result)) {
            // json对象
            if (result.trim().startsWith("{")) {
                try {
                    jo = new JSONObject(result);
                    if (jo.has(Const.response_status)) {
                        status = JSONUtil.getInt(jo, Const.response_status);
                    }
                    if (jo.has(Const.response_info)) {
                        info = jo.getString(Const.response_info);
                    }
                    if (jo.has(Const.response_data)) {
                        String json = jo.getString("data");
                        data = gsonDefault.fromJson(json,new TypeToken<T>(){}.getType());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (result.trim().startsWith("[")) {
                // 不处理
            }
        }
    }

    public ResponseBodyBean() {
    }

    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) throws Exception {
        List<T> lst =  new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            lst.add(new Gson().fromJson(elem, clazz));
        }
        return lst;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setIsCache(boolean isCache) {
        this.isCache = isCache;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isCache() {
        return isCache;
    }

    public void isCache(boolean isCache) {
        this.isCache = isCache;
    }

    //////////////////////
    /**
     * 返回纯文本
     *
     * @return
     */
    public String plain() {
        return result;
    }

    /**
     * 将返回的数据转换为jsonobject
     *
     * @return
     */
    public JSONObject jSON() {
        return jo;
    }

    /**
     * 获取json结果 其中的某个属性 为 jsonobject
     *
     * @param prefix
     * @return
     */
    public JSONObject jSONFrom(String prefix) {
        if (jo != null) {
            return JSONUtil.getJSONObject(jo, prefix);
        }
        return null;
    }

    /**
     * 获取json结果 其中data为 jsonobject
     */
    public JSONObject jSONFromData() {
        return jSONFrom(Const.response_data);
    }

    /**
     * 获取json结果 对象中的某个属性 为对象 prefix data
     */
    public <T> T modelFrom(String prefix) {
        if (jo != null) {
            String str = JSONUtil.getString(jo, prefix);
            Type type = new TypeToken<T>() {
            }.getType();
            T obj = gson.fromJson(str, type);
            return obj;
        }
        return null;
    }

    /**
     * 解析json结果 为bean
     *
     * @return
     */
    public <T> T modelFrom(Class<T> clazz, String prefix) {
        String str = JSONUtil.getString(jo, prefix);
        T obj = gson.fromJson(str, clazz);
        return obj;
    }

    public <T> T modelFromData() {
        return modelFrom(Const.response_data);
    }

    public <T> T modelFromData(Class<T> clazz) {
        return modelFrom(clazz, Const.response_data);
    }

    /**
     * 获取json结果 对象中的某个属性 为对象 list
     *
     * @param prefix
     * @return
     */
    public <T> List<T> listFrom(final Class<T> clazz, String prefix) {
        if (jo != null) {
            String str = JSONUtil.getString(jo, prefix);
            Type type = new ParameterizedType() {
                public Type getRawType() {
                    return ArrayList.class;
                }

                public Type getOwnerType() {
                    return null;
                }

                public Type[] getActualTypeArguments() {
                    Type[] type = new Type[1];
                    type[0] = clazz;
                    return type;
                }
            };
            List<T> list = gsonDefault.fromJson(str, type);
            return list;
        }
        return null;
    }

    public <T> List<T> listFromData(Class<T> clazz) {
        return listFrom(clazz, Const.response_data);
    }

}