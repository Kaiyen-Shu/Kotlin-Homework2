package com.example.lab4_java;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Step1：宣告 ActivityResultLauncher，負責處理 SecActivity 回傳結果
    private final ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // Step12：判斷回傳結果是否為 RESULT_OK，若是則執行以下程式碼
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Step13：取得回傳的 Intent，並從 Intent 中取得飲料名稱、甜度、冰塊的值
                    Intent intent = result.getData();
                    String drink = intent != null ? intent.getStringExtra("drink") : null;
                    String sugar = intent != null ? intent.getStringExtra("sugar") : null;
                    String ice = intent != null ? intent.getStringExtra("ice") : null;

                    // Step14：設定 tvMeal 的文字
                    TextView tvMeal = findViewById(R.id.tvMeal);
                    tvMeal.setText("飲料：" + drink + "\n\n甜度：" + sugar + "\n\n冰塊：" + ice);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            int left = insets.getSystemWindowInsetLeft();
            int top = insets.getSystemWindowInsetTop();
            int right = insets.getSystemWindowInsetRight();
            int bottom = insets.getSystemWindowInsetBottom();

            v.setPadding(left, top, right, bottom);
            return insets;
        });


        // Step2：定義元件變數，並通過 findViewById 取得元件
        Button btnChoice = findViewById(R.id.btnChoice);
        // Step3：設定 btnChoice 的點擊事件
        btnChoice.setOnClickListener(v -> {
            // Step4：宣告 Intent，透過 Intent 從 MainActivity 切換到 SecActivity
            Intent intent = new Intent(this, SecActivity.class);
            // Step5：使用 startForResult 啟動 Intent
            startForResult.launch(intent);
        });
    }
}
