package com.elitetask1.elitetask1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.etsy.android.grid.util.DynamicHeightTextView;

import java.util.Random;

import static android.content.ContentValues.TAG;

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.MyViewHolder> {

    int flags[];
    String[] countryNames;
    Context context;
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    public CustomAdapter3(Context context, int[] flags, String[] countryNames) {
        this.context = context;
        this.flags = flags;
        this.countryNames=countryNames;
        mRandom = new Random();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowforone, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.image.setImageResource(flags[position]);
        holder.text.setText(countryNames[position]);
        double positionHeight = getPositionRatio(position);
        Log.d(TAG, "getView position:" + position + " h:" + positionHeight);
        holder.image.setHeightRatio(positionHeight);

    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }


    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0)+0.5; // height will be 1.0 - 1.5 the width
    }

    @Override
    public int getItemCount() {
        return flags.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        DynamicHeightImageView image;
        DynamicHeightTextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            text = itemView.findViewById(R.id.textView);
        }
    }
}
