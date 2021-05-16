package com.example.stock;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NewsListRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )

    final static private String URL = "http://116.34.178.39:8080/newslist.php";
    final private Map<String, String> map;

    // Name : 종목 이름
    // Code : 종목 코드
    // Theme : 테마 코드

    public NewsListRequest( Response.Listener < String > listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams () throws AuthFailureError {
        return map;
    }
}