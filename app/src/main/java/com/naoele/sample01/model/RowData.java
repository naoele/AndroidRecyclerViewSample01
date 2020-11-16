package com.naoele.sample01.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class RowData {

    private String title;
    private String detail;

    public RowData(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public RowData(String title) {
        this.title = title;
    }

    public RowData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowData rowData = (RowData) o;
        return Objects.equals(title, rowData.title) &&
                Objects.equals(detail, rowData.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, detail);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
