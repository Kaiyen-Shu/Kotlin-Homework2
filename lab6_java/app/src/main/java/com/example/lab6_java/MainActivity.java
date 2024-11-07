package com.example.lab6_java;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            int left = insets.getSystemWindowInsetLeft();
            int top = insets.getSystemWindowInsetTop();
            int right = insets.getSystemWindowInsetRight();
            int bottom = insets.getSystemWindowInsetBottom();

            v.setPadding(left, top, right, bottom);
            return insets;
        });

        // 定義元件變數，並通過 findViewById 取得元件
        Button btnToast = findViewById(R.id.btnToast);
        Button btnSnackBar = findViewById(R.id.btnSnackBar);
        Button btnDialog1 = findViewById(R.id.btnDialog1);
        Button btnDialog2 = findViewById(R.id.btnDialog2);
        Button btnDialog3 = findViewById(R.id.btnDialog3);

        // 建立要顯示在的列表上的字串陣列
        String[] items = {"選項 1", "選項 2", "選項 3", "選項 4", "選項 5"};

        // 設定按鈕的點擊事件
        btnToast.setOnClickListener(view -> showToast("預設 Toast"));

        btnSnackBar.setOnClickListener(view ->
                Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
                        .setAction("按鈕", v -> showToast("已回應"))
                        .show()
        );

        btnDialog1.setOnClickListener(view ->
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("按鈕式 AlertDialog")
                        .setMessage("AlertDialog 內容")
                        .setNeutralButton("左按鈕", (dialogInterface, which) -> showToast("左按鈕"))
                        .setNegativeButton("中按鈕", (dialogInterface, which) -> showToast("中按鈕"))
                        .setPositiveButton("右按鈕", (dialogInterface, which) -> showToast("右按鈕"))
                        .show()
        );

        btnDialog2.setOnClickListener(view ->
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("列表式 AlertDialog")
                        .setItems(items, (dialogInterface, i) -> showToast("你選的是" + items[i]))
                        .show()
        );

        btnDialog3.setOnClickListener(view -> {
            final int[] position = {0};
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("單選式 AlertDialog")
                    .setSingleChoiceItems(items, 0, (dialogInterface, i) -> position[0] = i)
                    .setPositiveButton("確定", (dialog, which) -> showToast("你選的是" + items[position[0]]))
                    .show();
        });
    }

    // 建立 showToast 方法，顯示 Toast 訊息
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
