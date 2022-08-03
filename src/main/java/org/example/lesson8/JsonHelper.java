package org.example.lesson8;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonHelper {
    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<Map<String, Object>> mapTypeReference = new TypeReference<>() {};

    //TODO: Получить значения по ключу, если на пути есть массив.
    public <T> T getDataFromJson(File file, String path, Class<T> tClass) throws IOException {
        TypeReference<T> reference = new TypeReference<>() {};
        return getComplexDataFromJson(file, path, reference);
    }

    public <T> T getComplexDataFromJson(File file, String path, TypeReference<T> reference) throws IOException {
        Map<String, Object> data = mapper.readValue(file, mapTypeReference);
        Map<String, Object> finalMap = getFinalMap(data, path);

        return mapper.convertValue(finalMap.get(getFinalKey(path)), reference);
    }

    public void saveDataToJson(File file, Map<String, Object> data) throws IOException {
        mapper.writeValue(file, data);
    }

    public Map<String, Object> getData(File file) throws IOException {
        return mapper.readValue(file, mapTypeReference);
    }

    public void addValueToData(String path, Object value, Map<String, Object> data) {
        if (containsKey(data, getPathToKey(path))) {
            Map<String, Object> finalMap = getFinalMap(data, getPathToKey(path));
            finalMap.put(getFinalKey(path), value);
        }

        //TODO: Сделать генерацию мапы, если нет такой мапы нет на пути
    }

    public String getPathToKey(String path) {
        if (path.contains(".")) {
            return path.substring(0, path.lastIndexOf("."));
        }
        return "";
    }

    public Map<String, Object> removeKey(Map<String, Object> data, String path) throws IOException {
        Map<String, Object> finalMap = getFinalMap(data, path);
        finalMap.remove(getFinalKey(path));
        return data;
    }

    private boolean containsKey(Map<String, Object> data, String path) {
        if (path.equals("")) {
            return false;
        }

        Map<String, Object> finalMap = getFinalMap(data, path);
        return finalMap.containsKey(getFinalKey(path));
    }

    public boolean containsKey(File file, String path) throws IOException {
        Map<String, Object> data = mapper.readValue(file, mapTypeReference);
        return containsKey(data, path);
    }
    private String getFinalKey(String path) {
        String[] fields = path.split("[.]");
        return fields[fields.length - 1];
    }

    private Map<String, Object> getFinalMap(Map<String, Object> map, String path) {
        String[] fields = path.split("[.]");
        Map<String, Object> result = map;

        if (fields.length == 1) {
            return result;
        }

        for (int i = 0; i < fields.length - 1; i++) {
            result = (Map<String, Object>) result.get(fields[i]);
        }

        return result;
    }
}
