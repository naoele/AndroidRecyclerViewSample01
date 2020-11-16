package com.naoele.sample01.ui.slideshow;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.naoele.sample01.databinding.UserViewBinding;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private final UserViewBinding _binding;

    public TextView _title;
    public TextView _name;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        _binding = DataBindingUtil.bind(itemView);
    }

    public ViewDataBinding getBinding() {
        return _binding;
    }
}
