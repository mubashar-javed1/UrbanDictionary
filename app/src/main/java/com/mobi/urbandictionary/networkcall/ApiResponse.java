package com.mobi.urbandictionary.networkcall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobi.urbandictionary.data.DefinitionResponse;
import com.mobi.urbandictionary.enums.Status;

import static com.mobi.urbandictionary.enums.Status.ERROR;
import static com.mobi.urbandictionary.enums.Status.LOADING;
import static com.mobi.urbandictionary.enums.Status.SUCCESS;

public class ApiResponse {

    public final Status status;

    @Nullable
    public final DefinitionResponse data;

    @Nullable
    public final Throwable error;

    private ApiResponse(Status status, @Nullable DefinitionResponse data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(@NonNull DefinitionResponse data) {
        return new ApiResponse(SUCCESS, data, null);
    }

    public static ApiResponse responseError(@NonNull Throwable error) {
        return new ApiResponse(ERROR, null, error);
    }
}