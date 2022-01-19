package app.smty.practica6.Services;

import java.util.List;

import app.smty.practica6.Models.Archivo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ArchivosServices {
    @GET("archivos")
    Call<List<Archivo>> getArchivos();
}
