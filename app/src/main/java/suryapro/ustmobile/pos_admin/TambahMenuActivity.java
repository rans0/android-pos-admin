package suryapro.ustmobile.pos_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class TambahMenuActivity extends AppCompatActivity {

    private EditText namaMakanan, deskripsiMakanan, hargaMakanan;
    private Button tambahMenu, lihatMenu;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);

        namaMakanan = findViewById(R.id.nama_makanan);
        deskripsiMakanan = findViewById(R.id.deskripsi);
        hargaMakanan = findViewById(R.id.harga);
        tambahMenu = findViewById(R.id.tambah_menu);
        lihatMenu = findViewById(R.id.lihatMenu);

        db = FirebaseFirestore.getInstance();

        tambahMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = UUID.randomUUID().toString();
                String nama = namaMakanan.getText().toString();
                String deskripsi = deskripsiMakanan.getText().toString();
                String harga = hargaMakanan.getText().toString();

                saveToFireStore(id, nama, deskripsi, harga);
            }
        });

        lihatMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeMenu();
            }
        });
    }

    private void seeMenu() {
        Intent intent = new Intent(this, ListMenuActivity.class);
        startActivity(intent);
    }

    private void saveToFireStore(String id, String nama, String deskripsi, String harga) {
        if (!nama.isEmpty() && !deskripsi.isEmpty() && !harga.isEmpty()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("nama", nama);
            map.put("deskripsi", deskripsi);
            map.put("harga", harga);

            db.collection("DaftarMenu").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(TambahMenuActivity.this, "Menu Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(TambahMenuActivity.this, DaftarMenuActivity.class));
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(TambahMenuActivity.this, "Gagal Menambahkan Menu", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }
    }
}
