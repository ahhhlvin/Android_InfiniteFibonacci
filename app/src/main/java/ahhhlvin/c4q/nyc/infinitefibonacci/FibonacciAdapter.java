package ahhhlvin.c4q.nyc.infinitefibonacci;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigInteger;
import java.util.List;
import java.util.Locale;

/**
 * Created by alvin2 on 11/30/16.
 */

public class FibonacciAdapter extends RecyclerView.Adapter<FibonacciViewHolder> {

    private List<BigInteger> itemList;

    private int visibleThreshold = 5;
    private int actualItemCount, visibleItemCount;
    private int firstVisibleItem;
    private boolean loading = true;

    FibonacciAdapter(final List<BigInteger> itemList, RecyclerView recyclerView) {
        this.itemList = itemList;

        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    if(dy < 0) {
                        return;
                    }

                    visibleItemCount = recyclerView.getChildCount();
                    actualItemCount = mLinearLayoutManager.getItemCount();
                    firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

                    synchronized (this) {
                        if (loading) {
                            if (actualItemCount > visibleItemCount) {
                                loading = false;
                                visibleItemCount = actualItemCount;
                            }
                        }
                        if (!loading && (actualItemCount - visibleItemCount)
                                <= (firstVisibleItem + visibleThreshold)) {
                            MainActivity.getFibSequence(BigInteger.valueOf(itemList.size()+10));
                            recyclerView.getAdapter().notifyDataSetChanged();;
                            loading = true;
                        }
                    }
                }
            });
        }
    }

    @Override
    public FibonacciViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cell_layout, null);
        return new FibonacciViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(FibonacciViewHolder holder, int position) {
        holder.textView.setText(position + " : " + String.format(Locale.US, "%s", itemList.get(position).toString()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}


