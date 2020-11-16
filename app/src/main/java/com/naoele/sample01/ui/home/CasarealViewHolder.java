package com.naoele.sample01.ui.home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naoele.sample01.R;

public class CasarealViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout layout;
    public TextView titleView;
    public TextView detailView;

    public CasarealViewHolder(@NonNull View itemView) {
        super(itemView);

        layout = (LinearLayout) itemView.findViewById(R.id.lyt_item);
        titleView = (TextView) itemView.findViewById(R.id.title);
        detailView = (TextView) itemView.findViewById(R.id.detail);
    }


}