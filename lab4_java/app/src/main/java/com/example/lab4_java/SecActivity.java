package com.example.lab4_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BundleCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );
        setContentView(R.layout.activity_sec);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            int left = insets.getSystemWindowInsetLeft();
            int top = insets.getSystemWindowInsetTop();
            int right = insets.getSystemWindowInsetRight();
            int bottom = insets.getSystemWindowInsetBottom();

            v.setPadding(left, top, right, bottom);
            return insets;
        });

        // Step6：定義元件變數，並通過 findViewById 取得元件
        TextView edDrink = findViewById(R.id.edDrink);
        RadioGroup rgSugar = findViewById(R.id.rgSugar);
        RadioGroup rgIce = findViewById(R.id.rgIce);
        Button btnSend = findViewById(R.id.btnSend);

        // Step7：設定 btnSend 的點擊事件
        btnSend.setOnClickListener(v -> {
            // Step8：如果 edDrink 為空，則顯示提示文字
            if (edDrink.getText().toString().isEmpty()) {
                Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show();
            } else {
                // Step9：宣告 Bundle，並將飲料名稱、甜度、冰塊的值放入 Bundle 中
                Bundle b = new Bundle();
                b.putString("drink", edDrink.getText().toString());
                b.putString("sugar", ((RadioButton) rgSugar.findViewById(rgSugar.getCheckedRadioButtonId())).getText().toString());
                b.putString("ice", ((RadioButton) rgIce.findViewById(rgIce.getCheckedRadioButtonId())).getText().toString());

                // Step10：宣告 Intent，並將 Bundle 放入 Intent 中
                Intent i = new Intent();
                i.putExtras(b);

                // Step11：設定 Activity 的結果，並關閉 Activity
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
