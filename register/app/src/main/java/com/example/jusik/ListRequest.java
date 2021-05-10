package com.example.jusik;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ListRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://116.34.178.39:8080/getlist.php";
    private Map<String, String> map;


    public ListRequest(String name, String code, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",name);
        map.put("userPassword", code);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}