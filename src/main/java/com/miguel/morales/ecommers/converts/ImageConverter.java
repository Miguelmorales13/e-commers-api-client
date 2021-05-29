package com.miguel.morales.ecommers.converts;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.AttributeConverter;

public class ImageConverter implements AttributeConverter<String, String> {
    @Value("${host.images}")
    private String imagesPath;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return imagesPath.replaceAll(imagesPath, "");
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return imagesPath.concat(dbData);
    }
}
