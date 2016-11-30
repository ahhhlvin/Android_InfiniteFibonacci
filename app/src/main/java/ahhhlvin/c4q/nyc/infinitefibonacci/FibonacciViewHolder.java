package ahhhlvin.c4q.nyc.infinitefibonacci;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by alvin2 on 11/30/16.
 */

public class FibonacciViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    FibonacciViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text_view);
    }
}
