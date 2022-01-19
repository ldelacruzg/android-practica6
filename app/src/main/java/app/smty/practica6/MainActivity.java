package app.smty.practica6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;

import java.util.List;

import app.smty.practica6.Adapters.ArchivoAdapter;
import app.smty.practica6.Models.Archivo;
import app.smty.practica6.Services.ArchivosServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewArchivos;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init components UI
        recyclerViewArchivos = findViewById(R.id.recyclerViewArchivos);

        requestArchivos();
    }

    private void descargarArchivo(Archivo archivo) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(archivo.getArchivo()))
                .setTitle("Archivo-" + archivo.getNombre())
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true);
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }

    private void rellenarLista(List<Archivo> archivoList) {
        // Adaptador es la forma visual como se mostraran los datos
        layoutManager = new LinearLayoutManager(this);
        adapter = new ArchivoAdapter(R.layout.file_item, archivoList, new ArchivoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Archivo archivo, int position) {
                descargarArchivo(archivo);
            }
        });

        // establecemos el adaptador con nuestro recyclerView
        recyclerViewArchivos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewArchivos.setLayoutManager(layoutManager);
        recyclerViewArchivos.setAdapter(adapter);
    }

    private void requestArchivos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/otherluis/db-fake-archivos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArchivosServices service = retrofit.create(ArchivosServices.class);
        Call<List<Archivo>> archivos = service.getArchivos();
        archivos.enqueue(new Callback<List<Archivo>>() {
            @Override
            public void onResponse(Call<List<Archivo>> call, Response<List<Archivo>> response) {
                List<Archivo> body = response.body();
                rellenarLista(body);
            }

            @Override
            public void onFailure(Call<List<Archivo>> call, Throwable t) {

            }
        });
    }

}
