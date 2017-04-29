package io.github.yusukeiwaki.bottomsheetexample2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class DummyItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView;
    private final TextView subtitleTextView;

    public DummyItemViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.text1);
        subtitleTextView = (TextView) itemView.findViewById(R.id.text2);
    }

    public void bind(DummyItem item) {
        titleTextView.setText(item.title);
        subtitleTextView.setText(item.subtitle);
    }
}
