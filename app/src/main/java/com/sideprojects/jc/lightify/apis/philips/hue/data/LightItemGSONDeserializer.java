package com.sideprojects.jc.lightify.apis.philips.hue.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A custom Deserializer written for {@link LightItem}.
 * The JSON response from Philips Hue Bridge uses "key" as the ID of light item.
 * A custom Gson deserializer is needed to parse light item correctly.
 *
 * Created by {@author justin.chu} on 2/6/17.
 */
public class LightItemGSONDeserializer implements JsonDeserializer<List<LightItem>> {
    @Override
    public List<LightItem> deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<LightItem> items = new ArrayList<>();
        for(Map.Entry<String, JsonElement> entry : element.getAsJsonObject().entrySet()){
            LightItem item = context.deserialize(entry.getValue(), LightItem.class);
            item.setId(entry.getKey());
            items.add(item);
        }
        return items;
    }
}
