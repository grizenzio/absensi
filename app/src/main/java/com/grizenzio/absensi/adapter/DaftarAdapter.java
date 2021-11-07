package com.grizenzio.absensi.adapter;

import android.content.Context;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.grizenzio.absensi.R;

import com.grizenzio.absensi.kelas.Pegawai;

import java.util.List;

public class DaftarAdapter extends RecyclerView.Adapter<DaftarAdapter.MyViewHolder> {

    private List<Pegawai> pegawaiList;

    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //Ini adalah item dari item_daftar
        public TextView id, nama, nidn, tanggal, status;

        public MyViewHolder(View view) {

            super(view);

            //Ini adalah item dari item_daftar
            id = view.findViewById(R.id.tv_id_item_daftar);

            nama = view.findViewById(R.id.tv_nama_item_daftar);

            nidn = view.findViewById(R.id.tv_nidn_item_daftar);

            tanggal = view.findViewById(R.id.tv_tanggal_item_daftar);

            status = view.findViewById(R.id.tv_status_item_daftar);
        }
    }

    public DaftarAdapter(Context context, List<Pegawai> pegawaiList) {

        mContext = context;

        this.pegawaiList = pegawaiList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daftar, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Pegawai pegawai = pegawaiList.get(position);

        holder.id.setText(pegawai.getId());

        holder.nama.setText(pegawai.getNama());

        holder.nidn.setText(pegawai.getNidn());

        holder.tanggal.setText(pegawai.getTanggal());

        if(pegawai.getStatus().equals("1")){

            holder.status.setText("  Hadir  ");

        }else if(pegawai.getStatus().equals("0")){

            holder.status.setText("  Tidak Hadir  ");

        }

    }

    @Override
    public int getItemCount() {
        return pegawaiList.size();
    }
}