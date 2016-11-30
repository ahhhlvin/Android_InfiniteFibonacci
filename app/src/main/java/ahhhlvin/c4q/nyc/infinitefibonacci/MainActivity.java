package ahhhlvin.c4q.nyc.infinitefibonacci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<BigInteger> fibList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.resultsList);
        fibList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        getFibSequence(BigInteger.valueOf(10));

        FibonacciAdapter mAdapter = new FibonacciAdapter(fibList, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    public static void getFibSequence(BigInteger position) {

        BigInteger first = BigInteger.ZERO;
        BigInteger second = BigInteger.ONE;
        BigInteger result;

        if (fibList.size() < 3) {
            fibList.add(first);
            fibList.add(second);
        }

        else {
            first = fibList.get(fibList.size()-2);
            second = fibList.get(fibList.size()-1);
        }

        for (int i = fibList.size(); i < position.intValue(); i++) {

            result = first.add(second);
            fibList.add(first.add(second));
            first = second;
            second = result;
        }
    }
}

