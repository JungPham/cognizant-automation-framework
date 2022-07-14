package shared;

import lombok.*;
import utils.*;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

import static utils.ConvertToDataModel.*;

@SuppressWarnings("SameParameterValue, unused")
public abstract class BaseEndpoint {

    //    public String jsonFilePath;
    private Object[] dataModelAsArray;
    private Object dataModel;
    private String requestBody;

    //Get JSON as string from Data Model
    public String getRequestBody() {
        this.requestBody = convertDataModelToJson(false);
        return requestBody;
    }

    //Get JSON as string from Data Model as array
    public String getRequestBody(boolean isArray) {
        this.requestBody = convertDataModelToJson(isArray);
        return requestBody;
    }

    @SneakyThrows
    protected <T>void initRequestBody(Class<T[]> valueType, String jsonPath, boolean isArray) {
        this.dataModelAsArray = valueType.cast(convertJsonFileToDataModel(this.getClass().getClassLoader().getResourceAsStream(jsonPath), valueType));
        this.requestBody = convertDataModelToJson(isArray);
    }

    @SneakyThrows
    protected <T>void initRequestBody(Class<T[]> valueType, String jsonPath) {
        URL[] url = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
        String targetPath = Objects.requireNonNull(Arrays.stream(url).filter(el -> el.getPath().contains("target")).findFirst().orElse(null)).getPath();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(jsonPath);
        if (inputStream == null) {
            throw new NoSuchFileException("File not found. Path: " + targetPath + jsonPath);
        }
        this.dataModelAsArray = valueType.cast(convertJsonFileToDataModel(inputStream, valueType));
        this.requestBody = convertDataModelToJson(false);
    }

    //Converts JSON to data model as array
    @SneakyThrows
    protected <T>void convertJsonToDataModelArray(String response, Class<T[]> valueType) {
        this.dataModelAsArray = convertJsonAsStringToDataModel(response, valueType);
    }

    //Gets data model as an array
    @SneakyThrows
    protected <T>T getDataModelAsArray(Class<T> type) {
        if (dataModelAsArray.length != 0) {
            return type.cast(dataModelAsArray);
        } else {
            throw new Exception("Data model is empty! " + type.getSimpleName());
        }
    }

    protected <T>T getDataModel(Class<T> type) {
        return type.cast(dataModel);
    }

    protected void setValueOfField(String fieldName, Object value, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private String convertDataModelToJson(boolean isArray) {
        if (isArray) {
            return new ConvertDataModelToJson(dataModelAsArray).getJson();
        } else {
            return new ConvertDataModelToJson(dataModelAsArray[0]).getJson();
        }
    }

    public <T>void convertResponseToDataModel(String response, Class<T> valueType) throws IOException {
        this.dataModel = convertResponseAsStringToDataModel(response, valueType);
    }
}
