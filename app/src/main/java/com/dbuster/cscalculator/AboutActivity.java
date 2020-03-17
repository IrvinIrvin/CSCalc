package com.dbuster.cscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        final TextView githubTV = findViewById(R.id.githubrepoTV);
        final TextView telegramTV = findViewById(R.id.telegramTV);
        githubTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("github repo", githubTV.getText().toString());
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplication(), "Ссылка скопирована", Toast.LENGTH_SHORT).show();
            }
        });
        telegramTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("telegram", telegramTV.getText().toString());
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplication(), "Username скопирован", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
