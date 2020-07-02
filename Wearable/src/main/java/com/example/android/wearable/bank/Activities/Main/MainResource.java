package com.example.android.wearable.bank.Activities.Main;






import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainResource<T> {

    @NonNull
    public final MainResource.PostStatus status;

    @Nullable
    public final T data;


    @Nullable
    public final String message;


    public MainResource(@NonNull MainResource.PostStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> MainResource<T> datareceived (@Nullable T data) {
        return new MainResource<>(PostStatus.DATARECEIVED, data, null);
    }

    public static <T> MainResource<T> error(@NonNull String msg, @Nullable T data) {
        return new MainResource<>(MainResource.PostStatus.ERROR, data, msg);
    }

    public static <T> MainResource<T> loading(@Nullable T data) {
        return new MainResource<>(MainResource.PostStatus.LOADING, data, null);
    }

    public static <T> MainResource<T> logout () {
        return new MainResource<>(MainResource.PostStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum PostStatus { DATARECEIVED, ERROR, LOADING, NOT_AUTHENTICATED}

}