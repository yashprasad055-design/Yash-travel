package com.yashtourtravel.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.VH> {

    List<TourModel> list;

    public TourAdapter(List<TourModel> list) {
        this.list = list;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, price, desc;
        VH(View v) {
            super(v);
            name = v.findViewById(R.id.tourName);
            price = v.findViewById(R.id.tourPrice);
            desc = v.findViewById(R.id.tourDesc);
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup p, int v) {
        return new VH(LayoutInflater.from(p.getContext())
                .inflate(R.layout.tour_item, p, false));
    }

    @Override
    public void onBindViewHolder(VH h, int i) {
        TourModel t = list.get(i);
        h.name.setText(t.getName());
        h.price.setText("₹" + t.getPrice());
        h.desc.setText(t.getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
