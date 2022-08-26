package com.craft404.kanban.callback;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public interface DragHorizontalViewHolder {
    RecyclerView getRecyclerView();
    void findViewForContent(View itemView);
    void findViewForFooter(View itemView);
}
