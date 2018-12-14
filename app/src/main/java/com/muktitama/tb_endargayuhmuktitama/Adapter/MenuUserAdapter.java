package com.muktitama.tb_endargayuhmuktitama.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muktitama.tb_endargayuhmuktitama.MenuMakananActivity.EditMakananActivity;
import com.muktitama.tb_endargayuhmuktitama.R;
import com.muktitama.tb_endargayuhmuktitama.model.Menu;

import java.util.List;

public class MenuUserAdapter  extends RecyclerView.Adapter<MenuUserAdapter.MyViewHolder>{

    List<Menu> mMenuList;

    public MenuUserAdapter(List <Menu> MenuList) {
        mMenuList = MenuList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_admin, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.mTextViewId_makanan.setText("ID Menu : " + mMenuList.get(position).getId_makanan());
        holder.mTextViewNama.setText(mMenuList.get(position).getNama_makanan());
        holder.mTextViewHarga.setText("Rp " + mMenuList.get(position).getHarga());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(view.getContext(), EditMakananActivity.class);
//                mIntent.putExtra("Id_makanan", mMenuList.get(position).getId_makanan());
//                mIntent.putExtra("Nama", mMenuList.get(position).getNama_makanan());
//                mIntent.putExtra("Harga", mMenuList.get(position).getHarga());
//                view.getContext().startActivity(mIntent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId_makanan,mTextViewNama,mTextViewHarga;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId_makanan = (TextView) itemView.findViewById(R.id.tvId);
            mTextViewNama = (TextView) itemView.findViewById(R.id.tvNama);
            mTextViewHarga = (TextView) itemView.findViewById(R.id.tvHarga);
        }
    }
}
