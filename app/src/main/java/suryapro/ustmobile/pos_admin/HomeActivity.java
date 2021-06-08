package suryapro.ustmobile.pos_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button btnMenu, btnPesanan, btnFormInv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnMenu = findViewById(R.id.menu);
        btnPesanan = findViewById(R.id.pesanan);
        btnFormInv = findViewById(R.id.btnFormInv);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lihatMenu();
            }
        });

        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lihatPesanan();
            }
        });

        btnFormInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invoice();
            }
        });
    }

    private void invoice() {
        startActivity(new Intent(HomeActivity.this, InvoiceActivity.class));
    }

    private void lihatMenu() {
        startActivity(new Intent(HomeActivity.this, DaftarMenuActivity.class));
    }

    private void lihatPesanan() {
        startActivity(new Intent(HomeActivity.this, DaftarPesananActivity.class));
    }
}