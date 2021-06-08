package suryapro.ustmobile.pos_admin;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DaftarMenuAdapter extends RecyclerView.Adapter<DaftarMenuAdapter.MenuViewHolder> {

    private DaftarMenuActivity activity;
    private List<ModelMenu> mList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public DaftarMenuAdapter(DaftarMenuActivity activity, List<ModelMenu> mList) {
        this.activity = activity;
        this.mList = mList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.namaMakanan.setText(mList.get(position).getNama());
        holder.deskripsiMakanan.setText(mList.get(position).getDeskripsi());
        holder.hargaMakanan.setText(mList.get(position).getHarga());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference ref = firestore.collection("DaftarMenu").document(mList.get(position).getId());
                ref.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(activity, "Deleted", Toast.LENGTH_LONG);
                                    mList.remove(position);
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(activity, "Failed to delete", Toast.LENGTH_LONG);
                                }
                            }
                        });
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView namaMakanan, deskripsiMakanan, hargaMakanan;
        Button deleteButton;
        public MenuViewHolder(@NonNull View itemMenu) {
            super(itemMenu);

            namaMakanan = itemMenu.findViewById(R.id.nama_makanan);
            deskripsiMakanan = itemMenu.findViewById(R.id.deskripsi_makanan);
            hargaMakanan = itemMenu.findViewById(R.id.harga_makanan);
            deleteButton = itemMenu.findViewById(R.id.delete);

        }

    }

}
