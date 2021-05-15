package com.example.stock;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://116.34.178.39:8080/register.php"; // php url
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userGender, String userName, String userEmail, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userGender", userGender);
        parameters.put("userName", userName);
        parameters.put("userEmail", userEmail);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}

