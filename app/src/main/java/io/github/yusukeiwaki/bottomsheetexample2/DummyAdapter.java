package io.github.yusukeiwaki.bottomsheetexample2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 */
public class DummyAdapter extends RecyclerView.Adapter<DummyItemViewHolder> {

    private final List<DummyItem> data;

    public DummyAdapter(List<DummyItem> data) {
        this.data = data;
    }

    @Override
    public DummyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dummy, parent, false);
        return new DummyItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DummyItemViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
