package com.sv.proye.tecaapp.dto;

public class KeysMatch {

    private String keyName;
    private Integer keyType;
    private Boolean notNull;
    private Integer intValue;
    private Double realValue;
    private String textValue;

    public KeysMatch(String keyName, Integer keyType, Boolean notNull, Integer intValue, Double realValue, String textValue) {
        this.keyName = keyName;
        this.keyType = keyType;
        this.notNull = notNull;
        this.intValue = intValue;
        this.realValue = realValue;
        this.textValue = textValue;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    public Boolean getNotNull() {
        return notNull;
    }

    public void setNotNull(Boolean notNull) {
        this.notNull = notNull;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Double getRealValue() {
        return realValue;
    }

    public void setRealValue(Double realValue) {
        this.realValue = realValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getDataTypeMatch() {
        String dataType = null;
        switch (keyType) {
            case 1:
            case 4:
                dataType = "INTEGER";
                break;
            case 2:
            case 5:
                dataType = "TEXT";
                break;
            case 3:
                dataType = "REAL";
                break;
        }
        return dataType;
    }
}
