package ccsavvy.christian.catfact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;

import java.util.List;

import ccsavvy.christian.catfact.R;
import ccsavvy.christian.catfact.data.model.CatFactModel;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final List<CatFactModel> facts;
    private final LayoutInflater inflater;
    private final ItemLongClickListener itemLongClickListener;
    private final Context context;

    public Adapter(Context context, List<CatFactModel> data, ItemLongClickListener itemLongClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.facts = data;
        this.itemLongClickListener = itemLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CatFactModel animal = facts.get(position);
        holder.itemView.setOnClickListener(v -> Toast.makeText(context, "Long press to delete", Toast.LENGTH_SHORT).show());
        holder.textView.setText(animal.type);
        holder.textDescription.setText(animal.fact);
        Glide.with(context)
                .load(animal.cat)
                .circleCrop()
                .signature(new ObjectKey(System.currentTimeMillis()))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    public interface ItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView textView;

        TextView textDescription;

        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvName);
            textDescription = itemView.findViewById(R.id.tvDescription);
            imageView = itemView.findViewById(R.id.ivImage);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (itemLongClickListener != null)
                itemLongClickListener.onItemLongClick(v, getAdapterPosition());
            return false;
        }
    }
}
