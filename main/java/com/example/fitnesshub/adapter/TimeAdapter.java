package com.example.fitnesshub.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesshub.R;
import com.example.fitnesshub.model.BookingModel;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    private Context context;
    private List<String> timeList;
    private List<BookingModel> dblist;

    private AdapterCallBackListener callback;
    private int selectedItem = -1;

    public TimeAdapter(Context context, List<String> timeList, List<BookingModel> dbList, AdapterCallBackListener callback) {
        this.context = context;
        this.timeList = timeList;
        this.dblist = dbList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.timeadapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.ViewHolder holder, int position) {
        holder.binditem(timeList.get(position),position);



        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dblist.size() == 0) {
                    holder.cvMain.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.blue)));
                    Toast.makeText(context, "Clicked else", Toast.LENGTH_SHORT);
                    selectedItem = position;
                    callback.onClick(timeList.get(position));
                } else {
                    for (int i = 0; i < dblist.size(); i++) {
                        if (dblist.get(i).getTime().contains(timeList.get(position))) {
                            Toast.makeText(context, "Already schedule", Toast.LENGTH_SHORT).show();
                        } else {
                            holder.cvMain.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.blue)));
                            Toast.makeText(context, "Clicked else", Toast.LENGTH_SHORT);
                            selectedItem = position;
                            callback.onClick(timeList.get(position));
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvtimeset;
        CardView cvMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtimeset = itemView.findViewById(R.id.tv_timeset);
            cvMain = itemView.findViewById(R.id.cv_adapterTime);


        }

        public void binditem(String item,int position) {
            if (dblist.size() == 0) {
                if (selectedItem == position) {
                    cvMain.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.blue)));
                    tvtimeset.setTextColor(context.getColor(R.color.white));
                    tvtimeset.setText(timeList.get(position));
                }else {
                    tvtimeset.setText(timeList.get(position));
                    tvtimeset.setTextColor(context.getColor(R.color.black));
                    cvMain.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.white)));
                }
            } else {
                for (int i = 0; i < dblist.size(); i++) {

                    if (dblist.get(i).getTime().contains(item)) {
//                    holder.cvMain.setBackgroundColor(context.getColor(R.color.grey));
//                    holder.cvMain.setCardBackgroundColor(context.getColor(R.color.grey));
                        cvMain.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.grey)));
                        tvtimeset.setText(item);
                    } else {
                        if (selectedItem == position) {
                            cvMain.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.blue)));
                            tvtimeset.setTextColor(context.getColor(R.color.white));
                            tvtimeset.setText(item);
                        } else {
                            cvMain.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.white)));
                            tvtimeset.setTextColor(context.getColor(R.color.black));
                            tvtimeset.setText(item);
                        }

                    }
                }
            }
        }
    }

}

