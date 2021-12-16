package edu.neu.khoury.madsea.matthewgatesdehn.data;

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

    void onItemDropped();

    void onSelectedChanged(int layoutPosition);
}
