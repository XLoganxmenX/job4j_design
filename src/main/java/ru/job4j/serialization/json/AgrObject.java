package ru.job4j.serialization.json;

public class AgrObject {

    private long objIsn;
    private String agrName;
    private boolean isPerson;

    public AgrObject(long objIsn, String agrName, boolean isPerson) {
        this.objIsn = objIsn;
        this.agrName = agrName;
        this.isPerson = isPerson;
    }

    public long getObjIsn() {
        return objIsn;
    }

    public void setObjIsn(long objIsn) {
        this.objIsn = objIsn;
    }

    public String getAgrName() {
        return agrName;
    }

    public void setAgrName(String agrName) {
        this.agrName = agrName;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setPerson(boolean person) {
        isPerson = person;
    }

    @Override
    public String toString() {
        return "AgrObject{"
                + "objIsn=" + objIsn
                + ", agrName='" + agrName + '\''
                + ", isPerson=" + isPerson
                + '}';
    }
}
