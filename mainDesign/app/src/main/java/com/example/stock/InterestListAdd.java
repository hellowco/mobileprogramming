package com.example.stock;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InterestListAdd extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://116.34.178.39:8080/addinterest.php";
    private Map<String, String> map;


    public InterestListAdd(String name, String code, Response.Listener < String > listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("Name", name);
        map.put("Code", code);

    }

    @Override
    protected Map<String, String> getParams () throws AuthFailureError {
        return map;
    }
}