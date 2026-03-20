package com.example.btl.quanly;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.model.Voucher;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class VoucherEditActivity extends AppCompatActivity {

    private TextInputEditText etVoucherCode, etVoucherName, etVoucherValue;
    private RadioGroup rgDiscountType;
    private RadioButton rbPercent, rbAmount;
    private SwitchMaterial swVoucherActive;
    private MaterialButton btnSaveVoucher, btnDeleteVoucher;
    private MaterialToolbar toolbar;
    private VoucherManager voucherManager;
    private String mode;
    private int voucherId = -1;
    private Voucher currentVoucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_edit);

        voucherManager = VoucherManager.getInstance();
        initViews();
        getIntentData();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        etVoucherCode = findViewById(R.id.etVoucherCode);
        etVoucherName = findViewById(R.id.etVoucherName);
        etVoucherValue = findViewById(R.id.etVoucherValue);
        rgDiscountType = findViewById(R.id.rgDiscountType);
        rbPercent = findViewById(R.id.rbPercent);
        rbAmount = findViewById(R.id.rbAmount);
        swVoucherActive = findViewById(R.id.swVoucherActive);
        btnSaveVoucher = findViewById(R.id.btnSaveVoucher);
        btnDeleteVoucher = findViewById(R.id.btnDeleteVoucher);

        // Nút Back trên Toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void getIntentData() {
        mode = getIntent().getStringExtra("mode");
        voucherId = getIntent().getIntExtra("voucherId", -1);

        if ("edit".equals(mode) && voucherId != -1) {
            currentVoucher = voucherManager.getVoucherById(voucherId);
            if (currentVoucher != null) {
                etVoucherCode.setText(currentVoucher.getCode());
                etVoucherName.setText(currentVoucher.getName());
                etVoucherValue.setText(String.valueOf(currentVoucher.getDiscountValue()));
                
                if ("PERCENT".equals(currentVoucher.getDiscountType())) {
                    rbPercent.setChecked(true);
                } else {
                    rbAmount.setChecked(true);
                }
                
                swVoucherActive.setChecked(currentVoucher.isActive());
                btnDeleteVoucher.setVisibility(View.VISIBLE);
                toolbar.setTitle("Sửa mã giảm giá");
            }
        } else {
            toolbar.setTitle("Thêm mã giảm giá");
        }
    }

    private void setupActions() {
        btnSaveVoucher.setOnClickListener(v -> handleSave());
        btnDeleteVoucher.setOnClickListener(v -> handleDelete());
    }

    private void handleSave() {
        String code = etVoucherCode.getText().toString().trim();
        String name = etVoucherName.getText().toString().trim();
        String valueStr = etVoucherValue.getText().toString().trim();
        String type = rbPercent.isChecked() ? "PERCENT" : "AMOUNT";
        boolean active = swVoucherActive.isChecked();

        if (code.isEmpty() || name.isEmpty() || valueStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        double value = Double.parseDouble(valueStr);

        if ("add".equals(mode)) {
            Voucher newVoucher = new Voucher(
                    (int) (System.currentTimeMillis() % Integer.MAX_VALUE),
                    code,
                    name,
                    value,
                    type,
                    "01/01/2025",
                    "31/12/2025",
                    active
            );
            voucherManager.addVoucher(newVoucher);
            Toast.makeText(this, "Thêm voucher thành công", Toast.LENGTH_SHORT).show();
        } else {
            if (currentVoucher != null) {
                currentVoucher.setCode(code);
                currentVoucher.setName(name);
                currentVoucher.setDiscountValue(value);
                currentVoucher.setDiscountType(type);
                currentVoucher.setActive(active);
                voucherManager.updateVoucher(currentVoucher);
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void handleDelete() {
        if (currentVoucher != null) {
            voucherManager.deleteVoucher(currentVoucher.getId());
            Toast.makeText(this, "Đã xóa voucher", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}