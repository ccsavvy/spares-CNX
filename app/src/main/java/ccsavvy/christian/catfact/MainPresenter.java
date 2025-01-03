package ccsavvy.christian.catfact;

import android.content.Context;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import ccsavvy.christian.catfact.data.ApiManager;
import ccsavvy.christian.catfact.data.db.DataBaseManager;
import ccsavvy.christian.catfact.data.model.CatFactModel;
import ccsavvy.christian.catfact.model.Facts;
import ccsavvy.christian.catfact.model.NinjaCatFacts;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpPresenter;

public class MainPresenter extends MvpPresenter<MainView> {

    private DataBaseManager dataBaseManager = new DataBaseManager();
    private CatFactModel catFactModel;

    public void loadData(Context context) {
        getViewState().lockView(true);
        new ApiManager().getFact()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Facts>() {
                    @Override
                    public void onSuccess(Facts model) {
                        catFactModel = new CatFactModel();
                        catFactModel.id = System.nanoTime();
                        catFactModel.fact = model.getText();
                        catFactModel.type = model.getType();
                        catFactModel.createAt = model.getCreatedAt();

                        Thread thread = new Thread(() -> {
                            try {
                                catFactModel.cat = getLogoImage("https://cataas.com/cat");
                                dataBaseManager.insertData(context, catFactModel);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        thread.start();
                        getViewState().lockView(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LoadFacts::Throwable", e.getLocalizedMessage());
                        getViewState().lockView(false);
                    }
                });
    }


    public DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }

    private byte[] getLogoImage(String url) {

        try {
            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();
            InputStream is = ucon.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read = 0;

            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }

            baos.flush();
            return baos.toByteArray();

        } catch (Exception e) {
            Log.d("ImageManager", "Error: " + e.toString());
        }

        return null;
    }
}
