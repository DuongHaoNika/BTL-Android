package com.example.btl.customer.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.btl.R;

public class BookingDetailActivity extends AppCompatActivity {

    private TextView txtResName, txtResAddress, txtDateTime;
    private ImageView imgLogo;
    private Spinner spTables;
    private EditText edtName, edtPhone, edtNote;
    private Button btnBookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booking_detail);

        initViews();
        setupToolbar();
        displayData();
        setupActions();
    }

    private void initViews() {
        txtResName = findViewById(R.id.txtRestaurantName);
        txtResAddress = findViewById(R.id.txtRestaurantAddress);
        txtDateTime = findViewById(R.id.txtSelectedDateTime);
        imgLogo = findViewById(R.id.imgRestaurantLogo);
        spTables = findViewById(R.id.spTables);
        edtName = findViewById(R.id.edtBookingName);
        edtPhone = findViewById(R.id.edtBookingPhone);
        edtNote = findViewById(R.id.edtBookingNote);
        btnBookNow = findViewById(R.id.btnBookNow);

        // Giả lập danh sách bàn (Sau này lấy từ TableManager)
        String[] mockTables = {"Bàn A1 (4 người)", "Bàn A2 (2 người)", "Bàn B5 (10 người)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mockTables);
        spTables.setAdapter(adapter);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarBookingDetail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void displayData() {
        // Nhận dữ liệu thời gian từ BookingActivity
        String dateTime = getIntent().getStringExtra("dateTime");
        if (dateTime != null && !dateTime.isEmpty()) {
            txtDateTime.setText("Thời gian: " + dateTime);
        }

        // Giả lập thông tin nhà hàng (Sau này lấy từ Database theo chi nhánh đã chọn)
         txtResName.setText("Nhà hàng KFC - Chi nhánh Nam Từ Liêm");
         txtResAddress.setText("Số 1, Tây Mỗ, Nam Từ Liêm, Hà Nội");
    }

    private void setupActions() {
        btnBookNow.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên người đặt", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Đặt bàn thành công!", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}