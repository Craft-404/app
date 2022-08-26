package com.craft404.kanban.callback;

import com.craft404.kanban.model.DragColumn;

public interface DragHorizontalAdapter {
    void onDrag(int position);
    void onDrop(int page, int position, DragColumn tag);
    void onDragOut();
    void onDragIn(int position, DragColumn tag);
    void updateDragItemVisibility(int position);
}
