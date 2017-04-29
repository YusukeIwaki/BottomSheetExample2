package io.github.yusukeiwaki.bottomsheetexample2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomSheetBehavior bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setupBottomSheetBehavior();
        setupRecyclerView();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrHideBottomSheet(true);
            }
        });
    }

    private void setupBottomSheetBehavior() {
        bottomSheet = BottomSheetBehavior.from(findViewById(R.id.recyclerView));
        bottomSheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset >= 0) {
                    setToolbarTranslationOffset(1.0f - slideOffset);
                }
            }
        });

        showOrHideBottomSheet(false);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.INVISIBLE);
        toolbar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom > top) {
                    setToolbarTranslationOffset(1);
                    toolbar.setVisibility(View.VISIBLE);
                    toolbar.removeOnLayoutChangeListener(this);
                }
            }
        });
    }

    private void showOrHideBottomSheet(boolean show) {
        if (show) {
            bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheet.setHideable(false);
        } else {
            bottomSheet.setHideable(true);
            bottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    /**
     * offset=0: 完全に見せる
     * offset=1: 完全に画面外
     */
    private void setToolbarTranslationOffset(float offset) {
        toolbar.setTranslationY(-toolbar.getHeight() * offset);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ArrayList<DummyItem> data = new ArrayList<>();
        for (int i=0; i<40; i++) {
            data.add(new DummyItem(i+1));
        }

        recyclerView.setAdapter(new DummyAdapter(data));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        if (!bottomSheet.isHideable()) {
            showOrHideBottomSheet(false);
            return;
        }

        super.onBackPressed();
    }
}
