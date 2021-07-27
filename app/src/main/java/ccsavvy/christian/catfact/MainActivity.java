package ccsavvy.christian.catfact;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ccsavvy.christian.catfact.adapter.Adapter;
import ccsavvy.christian.catfact.data.model.CatFactModel;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private final List<CatFactModel> facts = new ArrayList<>();
    private Adapter adapter;
    private TextView textView;

    @InjectPresenter
    public MainPresenter presenter;
    private boolean isRemove = false;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_welcome);

        RecyclerView recyclerView = findViewById(R.id.rvFacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, facts, (view, position) -> {
            presenter.getDataBaseManager().delete(MainActivity.this.getApplicationContext(), facts.get(position));
            facts.remove(facts.get(position));
            adapter.notifyItemRemoved(position);
            isRemove = true;
        });

        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);

        presenter.getDataBaseManager().getData(this).observe(this, catFactModels -> {
            if (!catFactModels.isEmpty())
                textView.setVisibility(View.GONE);
            if (facts.isEmpty()) {
                facts.addAll(catFactModels);
                adapter.notifyDataSetChanged();
            } else {
                if (!isRemove) {
                    facts.add(catFactModels.get(catFactModels.size() - 1));
                    adapter.notifyItemChanged(adapter.getItemCount() - 1);
                }
                isRemove = false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.item = item;
        if (item.getItemId() == R.id.download) {
            presenter.loadData(getApplicationContext());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void lockView(boolean is) {
        item.setVisible(!is);
    }
}