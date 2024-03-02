package ru.job4j.serialization.json;

import org.json.JSONObject;

public class JsonObject {
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

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("agrIsn", agreement.getAgrIsn());
        jsonObject.put("agrID", agreement.getAgrID());
        jsonObject.put("prolongation", agreement.isProlongation());
        jsonObject.put("agrObj", agreement.getAgrObj());

        System.out.println(jsonObject);
        System.out.println(new JSONObject(agreement));


    }
}
