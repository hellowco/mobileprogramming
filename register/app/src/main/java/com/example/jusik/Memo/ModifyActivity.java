package com.example.jusik.Memo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jusik.MainActivity;
import com.example.jusik.R;
import com.example.jusik.Room.AppDatabase;
import com.example.jusik.Room.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ModifyActivity extends AppCompatActivity {
    private EditText detailTitle;
    private EditText detailContext;
    private TextView detailDate;
    private AppDatabase db;

    private FloatingActionButton exit;
    private FloatingActionButton update;

    private int id;
    private String title;
    private String context;
    private String date;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        initialized();


        // 수정
        update.setOnClickListener(v -> {
            title = detailTitle.getText().toString();
            context = detailContext.getText().toString();
            date = detailDate.getText().toString();
            db.userDao().update(title, context, date, id);
            finish();
        });
        //그냥 종료
        exit.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), MemoActivity.class);
            startActivity(intent);
        });
    }

    private void initialized() {
        update = findViewById(R.id.update);
        exit = findViewById(R.id.exit);
        detailTitle = findViewById(R.id.detailTitle);
        detailContext = findViewById(R.id.detailContent);
        detailDate = findViewById(R.id.detailDate);
        db = AppDatabase.getInstance(this);

        User detail = getIntent().getParcelableExtra("data");

        id = detail.getId();
        title = detail.getTitle();
        context = detail.getContext();
        date = detail.getDate();

        detailTitle.setText(title);
        detailContext.setText(context);
        detailDate.setText(date);
    }
}
