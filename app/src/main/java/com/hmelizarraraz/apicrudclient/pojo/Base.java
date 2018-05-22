package com.hmelizarraraz.apicrudclient.pojo;

public class Base<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
