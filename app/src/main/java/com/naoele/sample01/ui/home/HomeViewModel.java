package com.naoele.sample01.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DiffUtil;

import com.naoele.sample01.model.RowData;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public DiffUtil.ItemCallback<RowData> getItemCallback() {
        return new DiffUtil.ItemCallback<RowData>() {
            @Override
            public boolean areItemsTheSame(@NonNull RowData oldRow, @NonNull RowData newRow) {
                // DBからリロードするとユーザープロパティが変更された可能性がありますが、IDは固定されています
//            return oldRow.getId() == newRow.getId();
                return oldRow.equals(newRow);
            }

            @Override
            public boolean areContentsTheSame(@NonNull RowData oldRow, @NonNull RowData newRow) {
                // 注：equalsを使用する場合、オブジェクトはObject＃equals（）を適切にオーバーライドする必要があります
                // ここで誤ってfalseを返すと、アニメーションが多すぎます。
                return oldRow.equals(newRow);
            }
        };
    }

    public List<RowData> getDataSet() {
        List<RowData> dataset = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            RowData data = new RowData();
            data.setTitle("カサレアル　太郎" + i + "号");
            data.setDetail("カサレアル　太郎は" + i + "個の唐揚げが好き");

            dataset.add(data);
        }
        return dataset;
    }
}