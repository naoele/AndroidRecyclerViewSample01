package com.naoele.sample01.ui.slideshow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.naoele.sample01.model.User;

import java.util.ArrayList;
import java.util.List;

public class SlideshowViewModel extends ViewModel {

    private Fragment _fra;
    private final List<User> _usersRaw;
    private final MutableLiveData<List<User>> _users;
    //    private final LiveData<List<User>> users;
    public MediatorLiveData<String> usersText;
    private Observer<String> txtObsever = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            usersText.postValue("");
        }
    };

    private final MutableLiveData<String> _toastRequest;

    public SlideshowViewModel() {
        _usersRaw = new ArrayList<>();
        _users = new MutableLiveData<>();
//        User u = new User();
//        u.setId(1);
//        u.setName("test1");
//        _users.getValue().add(u);
//        users = new MutableLiveData<List<User>>();

        usersText = new MediatorLiveData<String>();
        usersText.addSource(_users, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> s) {
                updateUsersText();
            }
        });

        _toastRequest = new MutableLiveData<String>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super String> observer) {
                super.observe(owner, observer);
            }
        };
    }

    public void updateUsersText() {
        StringBuilder sb = new StringBuilder();
        sb.append("users=[");
        for (User u : _users.getValue()) {
            sb.append("{\"id\":");
            sb.append(u.getIdAsString());
            sb.append(",{\"name\":");
            sb.append("},");
        }
        sb.append("]");
        usersText.postValue(sb.toString());
    }

    private long index = 0L;

    public void addElement() {
        double p = (index % 26);
        String name = String.valueOf('A' + p);
        User u = new User();
        u.setId(index);
        u.setName(name);
        _usersRaw.add(u);
    }

    public void checkAll() {
//        Toast.makeText(_fra.getContext(), "checkAllテスト", Toast.LENGTH_LONG).show();
        _usersRaw.forEach(u -> u.isChecked.postValue(true));
    }

    public void onClickItem(User user) {
//        Toast.makeText(_fra.getContext(), "onClickItemテスト", Toast.LENGTH_LONG).show();
        _toastRequest.postValue(user.toString());
    }

    public void setFragment(Fragment value) {
        _fra = value;
    }
}