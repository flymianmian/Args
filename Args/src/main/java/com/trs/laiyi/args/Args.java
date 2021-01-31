package com.trs.laiyi.args;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Args {

    private final Map<String, Object> values = new HashMap<>();

    public Args(Schema schema, List<String> argsAsArray) {
        for (String argAsText : argsAsArray) {
            String[] keyValue = argAsText.split(" ");
            String key = keyValue[0].replace("-", "");
            if (keyValue.length > 1) {
                String value = keyValue[1];
                String definition = schema.getDefinition(key);
                if (definition.equals("integer")) {
                    this.values.put(key, Integer.valueOf(value));
                } else if (definition.equals("string")) {
                    this.values.put(key, value);
                }
            } else {
                this.values.put(key, true);
            }
        }
    }

    public Object getValue(String key) throws ArgsException {
        if (values.containsKey(key)) {
            return values.get(key);
        }
        throw new ArgsException(String.format("no such arg '%s'!", key));
    }
}
