package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Agreement {

    private long agrIsn;
    private String agrID;
    private boolean isProlongation;
    private AgrObject[] agrObj;

    public Agreement(long agrIsn, String agrID, boolean isProlongation, AgrObject[] agrObj) {
        this.agrIsn = agrIsn;
        this.agrID = agrID;
        this.isProlongation = isProlongation;
        this.agrObj = agrObj;
    }

    public long getAgrIsn() {
        return agrIsn;
    }

    public void setAgrIsn(long agrIsn) {
        this.agrIsn = agrIsn;
    }

    public String getAgrID() {
        return agrID;
    }

    public void setAgrID(String agrID) {
        this.agrID = agrID;
    }

    public boolean isProlongation() {
        return isProlongation;
    }

    public void setProlongation(boolean prolongation) {
        isProlongation = prolongation;
    }

    public AgrObject[] getAgrObj() {
        return agrObj;
    }

    public void setAgrObj(AgrObject[] agrObj) {
        this.agrObj = agrObj;
    }

    @Override
    public String toString() {
        return "Agreement{"
                + "agrIsn=" + agrIsn
                + ", agrID='" + agrID + '\''
                + ", isProlongation=" + isProlongation
                + ", agrObj=" + Arrays.toString(agrObj)
                + '}';
    }



    public static void main(String[] args) {
        final Agreement agreement = new Agreement(
                121,
                "110/30-000001",
                false,
                new AgrObject[]{
                        new AgrObject(45, "House", false),
                        new AgrObject(99, "Flat", false),
                        new AgrObject(100, "Petrov Vasiliy Fedorovich", true)
                }
        );

        final Gson gsonBuilder = new GsonBuilder().create();
        String agrJson = gsonBuilder.toJson(agreement);
        System.out.println(agrJson);
        gsonBuilder.fromJson(agrJson, Agreement.class);

        final Agreement agrFromJson = gsonBuilder.fromJson(agrJson, Agreement.class);
        System.out.println(agrFromJson);
    }
}
