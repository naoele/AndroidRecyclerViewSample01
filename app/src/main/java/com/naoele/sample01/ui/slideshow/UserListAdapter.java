package com.naoele.sample01.ui.slideshow;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;

import com.naoele.sample01.R;
import com.naoele.sample01.databinding.UserViewBinding;
import com.naoele.sample01.model.User;

public class UserListAdapter extends ListAdapter<User, UserViewHolder> {

    private UserViewBinding _binding;

    public UserListAdapter() {
        super(DIFF_CALLBACK);
    }

    public UserListAdapter(@NonNull ItemCallback<User> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        _binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ichiran_view, parent, false);
        return new UserViewHolder(_binding.getRoot());
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final User user = getItem(position);

        holder._title.setText(user.getIdAsString());
        holder._name.setText(user.getName());

//        holder.getBinding().setVariable(BR.user, user);
//        holder.getBinding().executePendingBindings();
    }

    public static final DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(
                @NonNull User oldUser, @NonNull User newUser) {
            // DBからリロードするとユーザープロパティが変更された可能性がありますが、IDは固定されています
            return oldUser.getId() == newUser.getId();
        }

        @Override
        public boolean areContentsTheSame(
                @NonNull User oldUser, @NonNull User newUser) {
            // 注：equalsを使用する場合、オブジェクトはObject＃equals（）を適切にオーバーライドする必要があります
            // ここで誤ってfalseを返すと、アニメーションが多すぎます。
            return oldUser.equals(newUser);
        }
    };
}
