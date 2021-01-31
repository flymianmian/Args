package com.trs.laiyi.args;

import java.util.HashMap;
import java.util.Map;

public class Schema {

    private final Map<String, String> definitions = new HashMap<>();

    Schema(String schemaAsText) throws ArgsException {
        if (schemaAsText.isEmpty()) {
            throw new ArgsException("can not parse empty text!");
        }
        String[] defTexts = schemaAsText.split(" +");
        for (String defText : defTexts) {
            String[] keyValue = defText.split(":");
            this.definitions.put(keyValue[0], keyValue[1]);
        }
    }

    public String getDefinition(String key) {
        return this.definitions.get(key);
    }
}
