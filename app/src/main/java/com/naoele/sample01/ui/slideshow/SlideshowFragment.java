package com.naoele.sample01.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naoele.sample01.R;
import com.naoele.sample01.databinding.SlideshowFragmentBinding;
import com.naoele.sample01.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.naoele.sample01.R.layout.slideshow_fragment;

public class SlideshowFragment extends Fragment {

    private SlideshowFragmentBinding _binding;

    public static SlideshowFragment newInstance() {
        return new SlideshowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(slideshow_fragment, container, false);
        _binding = DataBindingUtil.bind(viewRoot);
        return _binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SlideshowViewModel vm = new ViewModelProvider(this).get(SlideshowViewModel.class);
        vm.setFragment(this);
        _binding.setViewModel(vm);
        _binding.setLifecycleOwner(getViewLifecycleOwner());

        final RecyclerView recyclerView = _binding.getRoot().findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        UserListAdapter adapter = new UserListAdapter();
        List<User> list = new ArrayList<User>();
        list.add(new User(1L, "test1"));
        list.add(new User(2L, "test2"));
        list.add(new User(3L, "test3"));
        adapter.submitList(list);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                recyclerView.scrollToPosition(0);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}