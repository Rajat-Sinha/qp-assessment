package com.grocery.mgmt.platform.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.UUID;

@Slf4j
@UtilityClass
public final class Convert {

    public static ObjectMapper getObjectMapper() {

        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;

    }

    public static JSONObject convertStringIntoJSONObject(String value) {
        try {
            if (value != null) {
                JSONParser parser = new JSONParser();
                return (JSONObject) parser.parse(value);
            }
        } catch (Exception e) {
            log.error("Error in parsing json object" , e);
        }
        return null;
    }

    public static JSONArray convertStringIntoJSONArray(String value) {
        try {
            if (value != null) {
                JSONParser parser = new JSONParser();
                return (JSONArray) parser.parse(value);
            }
        } catch (Exception e) {
            log.error("Error in parsing json array " , e);
        }
        return null;
    }

    public static UUID stringToUUID(String value) {
        if(StringUtils.isNotEmpty(value)) {
            return UUID.fromString(value);
        }
        return null;
    }
}
