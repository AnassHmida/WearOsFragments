package com.example.android.wearable.bank.Activities;






import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainResource<T> {

    @NonNull
    public final com.example.android.wearable.bank.Activities.MainResource.PostStatus status;

    @Nullable
    public final T data;


    @Nullable
    public final String message;


    public MainResource(@NonNull com.example.android.wearable.bank.Activities.MainResource.PostStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> com.example.android.wearable.bank.Activities.MainResource<T> datareceived (@Nullable T data) {
        return new com.example.android.wearable.bank.Activities.MainResource<>(PostStatus.DATARECEIVED, data, null);
    }

    public static <T> com.example.android.wearable.bank.Activities.MainResource<T> error(@NonNull String msg, @Nullable T data) {
        return new com.example.android.wearable.bank.Activities.MainResource<>(com.example.android.wearable.bank.Activities.MainResource.PostStatus.ERROR, data, msg);
    }

    public static <T> com.example.android.wearable.bank.Activities.MainResource<T> loading(@Nullable T data) {
        return new com.example.android.wearable.bank.Activities.MainResource<>(com.example.android.wearable.bank.Activities.MainResource.PostStatus.LOADING, data, null);
    }

    public static <T> com.example.android.wearable.bank.Activities.MainResource<T> logout () {
        return new com.example.android.wearable.bank.Activities.MainResource<>(com.example.android.wearable.bank.Activities.MainResource.PostStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum PostStatus { DATARECEIVED, ERROR, LOADING, NOT_AUTHENTICATED}

}