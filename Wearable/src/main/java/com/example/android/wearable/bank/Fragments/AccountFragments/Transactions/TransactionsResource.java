package com.example.android.wearable.bank.Fragments.AccountFragments.Transactions;






import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TransactionsResource<T> {

    @NonNull
    public final TransactionsResource.PostStatus status;

    @Nullable
    public final T data;


    @Nullable
    public final String message;


    public TransactionsResource(@NonNull TransactionsResource.PostStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> TransactionsResource<T> datareceived (@Nullable T data) {
        return new TransactionsResource<>(PostStatus.DATARECEIVED, data, null);
    }

    public static <T> TransactionsResource<T> error(@NonNull String msg, @Nullable T data) {
        return new TransactionsResource<>(TransactionsResource.PostStatus.ERROR, data, msg);
    }

    public static <T> TransactionsResource<T> loading(@Nullable T data) {
        return new TransactionsResource<>(TransactionsResource.PostStatus.LOADING, data, null);
    }

    public static <T> TransactionsResource<T> logout () {
        return new TransactionsResource<>(TransactionsResource.PostStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum PostStatus { DATARECEIVED, ERROR, LOADING, NOT_AUTHENTICATED}

}