package ccsavvy.christian.catfact.data.db;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import ccsavvy.christian.catfact.data.model.CatFactModel;

public class DataBaseManager {

    private AppDatabase appDatabase;
    private LiveData<List<CatFactModel>> data;

    public AppDatabase initializeDB(Context context) {
        return AppDatabase.getAppDatabase(context);
    }

    public void insertData(Context context, CatFactModel catFactModel) {
        appDatabase = initializeDB(context);
        appDatabase.catFactDao().insertData(catFactModel);
    }

    public LiveData<List<CatFactModel>> getData(Context context) {
        appDatabase = initializeDB(context);
        data = appDatabase.catFactDao().getAll();
        return data;
    }

    public void delete(Context context, CatFactModel catFactModel) {
        appDatabase = initializeDB(context);
        Thread thread = new Thread(() -> appDatabase.catFactDao().delete(catFactModel));
        thread.start();
    }
}
