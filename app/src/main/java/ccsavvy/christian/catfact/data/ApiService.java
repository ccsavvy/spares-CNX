package ccsavvy.christian.catfact.data;

import ccsavvy.christian.catfact.model.Facts;
import ccsavvy.christian.catfact.model.NinjaCatFacts;
import io.reactivex.Single;
import retrofit2.http.GET;

interface ApiService {
    @GET("/facts/random")
    Single<Facts> getFact();
}

