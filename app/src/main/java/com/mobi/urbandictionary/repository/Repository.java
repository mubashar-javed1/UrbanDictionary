package com.mobi.urbandictionary.repository;

import com.mobi.urbandictionary.data.DefinitionResponse;
import com.mobi.urbandictionary.networkcall.ApiCall;

import io.reactivex.Observable;

public class Repository {
    private ApiCall apiCall;

    public Repository(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public Observable<DefinitionResponse> getDefinitions(String abbreviation) {
        return apiCall.getDefinitions(abbreviation);
    }
}