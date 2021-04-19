package suryapro.ustmobile.pos_admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DaftarMenuAdapter extends RecyclerView.Adapter<DaftarMenuAdapter.MenuViewHolder> {

    private DaftarMenuActivity activity;
    private List<ModelMenu> mList;

    public DaftarMenuAdapter(DaftarMenuActivity activity, List<ModelMenu> mList) {
        this.activity = activity;
        this.mList = mList;
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
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView namaMakanan, deskripsiMakanan, hargaMakanan;
        public MenuViewHolder(@NonNull View itemMenu) {
            super(itemMenu);

            namaMakanan = itemMenu.findViewById(R.id.nama_makanan);
            deskripsiMakanan = itemMenu.findViewById(R.id.deskripsi_makanan);
            hargaMakanan = itemMenu.findViewById(R.id.harga_makanan);
        }

    }

}
