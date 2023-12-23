package com.example.fitnesshub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesshub.R;
import com.example.fitnesshub.model.BookingModel;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{
    private Context context;
    private List<BookingModel> bookingModels;
    private AdapterCallBackListener callback;

    public BookingAdapter(Context context, List<BookingModel> bookingModels,AdapterCallBackListener callback) {
        this.context = context;
        this.bookingModels = bookingModels;
        this.callback = callback;
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.bookingadapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {

        holder.bind(bookingModels.get(position));

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.edit(bookingModels.get(position));
                notifyDataSetChanged();
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.delete(bookingModels.get(position));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookingModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView btnEdit,btnDelete;
        TextView date,time,duration,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tvdate);
            time = itemView.findViewById(R.id.tvtime);
            duration = itemView.findViewById(R.id.tvduration);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            name = itemView.findViewById(R.id.tvname);

        }
        public void bind(BookingModel bookingModel){
            name.setText(bookingModel.getName());
            date.setText(bookingModel.getDate());
            time.setText(bookingModel.getTime());
            duration.setText(bookingModel.getDuration());
        }
    }
}
