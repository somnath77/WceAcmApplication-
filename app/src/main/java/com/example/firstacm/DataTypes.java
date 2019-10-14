package com.example.firstacm;

public class DataTypes {

    private String FullName;
    private String ClassName;
    private String CollageName;

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getCollageName() {
        return CollageName;
    }

    public void setCollageName(String collageName) {
        CollageName = collageName;
    }

    public DataTypes(String fullName, String className, String collageName) {
        FullName = fullName;
        ClassName = className;
        CollageName = collageName;
    }

    public DataTypes() {

    }
}
