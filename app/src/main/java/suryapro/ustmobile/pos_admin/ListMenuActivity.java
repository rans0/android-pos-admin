package suryapro.ustmobile.pos_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListMenuActivity extends AppCompatActivity {
    ListView listView;
    SimpleAdapter adapter;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;
    String[] img;
    String[] nama;
    String[] deskripsi;
    String[] harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
        listView = (ListView)findViewById(R.id.list_view);

        nama = new String[] {
                "Nasi Goreng Rempah","Baso","Lontong Sayur","Chocolate","Jeruk Nipis","Es Buah"
        };
        deskripsi = new String[] {
                "Nasi Goreng dengan rempah-rempah","Baso Sapi","Lontong Sayur Jawa","Ice Chocolate",
                "Jeruk Nipis Segar", "Es Buah Segar"
        };
        harga = new String[]{
                "25000", "15000", "20000", "18000", "12000", "10000"
        };
        img = new String[]{
                Integer.toString(R.drawable.nasgor), Integer.toString(R.drawable.baso),
                Integer.toString(R.drawable.lontongsayur), Integer.toString(R.drawable.chocolate),
                Integer.toString(R.drawable.nipis), Integer.toString(R.drawable.seger)
        };
        mylist = new ArrayList<HashMap<String, String>>();

        for (int i=0; i<nama.length; i++){
            map = new HashMap<String, String>();
            map.put("Nama", nama[i]);
            map.put("Keterangan", deskripsi[i]);
            map.put("Harga", harga[i]);
            map.put("Gambar", img[i]);
            mylist.add(map);
        }

        adapter = new SimpleAdapter(this, mylist, R.layout.menu_item,
                new String[]{"Nama", "Keterangan", "Gambar", "Harga"}, new int[]{R.id.nama,(R.id.deskripsi),(R.id.img),(R.id.harga)});
        listView.setAdapter(adapter);
    }
}