package com.naoele.sample01.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naoele.sample01.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // 一覧表示
        final RecyclerView recyclerView = root.findViewById(R.id.casarealRecyclerView);
        CasarealRecycleViewAdapter adapter = new CasarealRecycleViewAdapter(homeViewModel.getItemCallback());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        // 区切り線
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), manager.getOrientation()));
        recyclerView.setLayoutManager(manager);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerView.scrollToPosition(0);
            }
        });
        adapter.submitList(homeViewModel.getDataSet());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                CasarealRecycleViewAdapter a = (CasarealRecycleViewAdapter) rv.getAdapter();
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                CasarealRecycleViewAdapter a = (CasarealRecycleViewAdapter) rv.getAdapter();
//                recyclerView.getAdapter().notifyDataSetChanged();
                //                        if (holder. != null)
//                            holder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.teal_200));
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        // 一覧アイテム操作設定
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView view, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView.ViewHolder target) {
                        // trueを返すと移動できるがfalseが返ると元に戻る
                        try {
                            int fromPosition = holder.getAdapterPosition();
                            int toPosition = target.getAdapterPosition();
                            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
                            return true;
                        } catch (Exception e) {
                            Log.e("test", "エラー", e);
                            return false;
                        }
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int direction) {
                        recyclerView.getAdapter().notifyItemRemoved(holder.getAdapterPosition());
                    }
                });
        itemTouchHelper.attachToRecyclerView(recyclerView);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }
}