package com.naoele.sample01.ui.home;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.naoele.sample01.R;
import com.naoele.sample01.model.RowData;

public class CasarealRecycleViewAdapter extends ListAdapter<RowData, CasarealViewHolder> {

    protected CasarealRecycleViewAdapter(@NonNull DiffUtil.ItemCallback<RowData> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CasarealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        CasarealViewHolder vh = new CasarealViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CasarealViewHolder holder, final int position) {

        final RowData row = getItem(position);

        holder.titleView.setText(row.getTitle());
        holder.detailView.setText(row.getDetail());
        holder.layout.setId(holder.getAdapterPosition());
        holder.layout.setOnClickListener(_listener);
    }

    private View.OnClickListener _listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//                selectedPosition = position;
            notifyDataSetChanged();
        }
    };
}
