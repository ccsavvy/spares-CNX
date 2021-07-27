package ccsavvy.christian.catfact.data;

import ccsavvy.christian.catfact.model.NinjaCatFacts;
import io.reactivex.Single;
import retrofit2.http.GET;

interface ApiService {
    @GET("/fact")
    Single<NinjaCatFacts> getFact();
}

