package com.example.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThemeListViewFragment extends Fragment {
    ListView listView;

    ThemeListViewAdapter adapter;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> code = new ArrayList<>();
    ArrayList<String> theme = new ArrayList<>();

    ArrayList<ThemeList> arrayList = new ArrayList<>();

    String  findThemeCode;

    public ThemeListViewFragment(String code)
    {
        findThemeCode = code;
    }

    // SELECT * FROM (datestring)_themecode WHERE themcode == findThemeCode
    // 주어진 parameter 테마 코드로 테마코드 내의 stock 데이터를 받아오기

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.theme_list, container, false);
        listView = view.findViewById(R.id.sortedThemeList);
        String name1 = "";
        String code1 = "";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
//                    System.out.println(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        System.out.println(jsonObject);
                        if (!response.isEmpty()) {
                            String name1 = jsonObject.getString("stockName");
                            String code1 = jsonObject.getString("stockCode");
//                            System.out.println(name1);
//                            System.out.println(code1);
                            name.add(name1);
                            code.add(code1);
                        } else {
                            Toast.makeText(getContext(), "목록 불러오기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i<name.size(); i++){
                    ThemeList themeList = new ThemeList(name.get(i), code.get(i));
                    arrayList.add(themeList);
                }

                adapter = new ThemeListViewAdapter(getActivity(), arrayList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };

        ThemeListRequest listRequest = new ThemeListRequest(findThemeCode, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(listRequest);

        return view;
    }
}
