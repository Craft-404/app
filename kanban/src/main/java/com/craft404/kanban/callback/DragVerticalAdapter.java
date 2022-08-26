package com.craft404.kanban.callback;

import com.craft404.kanban.model.DragItem;

public interface DragVerticalAdapter {
    void onDrag(int position);
    void onDrop(int page, int position, DragItem tag);
    void onDragOut();
    void onDragIn(int position, DragItem tag);
    void updateDragItemVisibility(int position);
}
