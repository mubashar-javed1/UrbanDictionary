package com.mobi.urbandictionary.networkcall;

import com.mobi.urbandictionary.data.DefinitionResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {
    @GET(ApiConfig.GET_DEFINITION)
    Observable<DefinitionResponse> getDefinitions(@Query(ApiConst.TERM) String term);
}