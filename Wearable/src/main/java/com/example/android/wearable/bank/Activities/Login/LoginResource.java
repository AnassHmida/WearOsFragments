package com.example.android.wearable.bank.Activities.Login;





import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LoginResource<T> {

    @NonNull
    public final PostStatus status;

    @Nullable
    public final T data;


    @Nullable
    public final String message;


    public LoginResource(@NonNull PostStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> LoginResource<T> authenticated (@Nullable T data) {
        return new LoginResource<>(PostStatus.AUTHENTICATED, data, null);
    }

    public static <T> LoginResource<T> error(@NonNull String msg, @Nullable T data) {
        return new LoginResource<>(PostStatus.ERROR, data, msg);
    }

    public static <T> LoginResource<T> loading(@Nullable T data) {
        return new LoginResource<>(PostStatus.LOADING, data, null);
    }

    public static <T> LoginResource<T> logout () {
        return new LoginResource<>(PostStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum PostStatus { AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED}

}