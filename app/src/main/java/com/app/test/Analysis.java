package com.app.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Analysis extends RecyclerView.Adapter<Analysis.DataView> {

    private Context context;
    private Activity activity;
    private ArrayList sentiment_id, sentiment_text, sentiment_label;
    public Analysis(){

    }
    public Analysis(Activity activity,Context _context, ArrayList _sentiment_id, ArrayList _sentiment_text, ArrayList _sentiment_label) {
        this.activity = activity;
        this.context = _context;
        this.sentiment_id = _sentiment_id;
        this.sentiment_text = _sentiment_text;
        this.sentiment_label = _sentiment_label;
    }

    @Override
    public DataView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.text_row_item, parent, false);
        return new DataView(view);
    }
    @Override
    public void onBindViewHolder(DataView holder, int position) {
        holder.sentiment_id.setText(String.valueOf(sentiment_id.get(position)));
        holder.sentence.setText(String.valueOf(sentiment_text.get(position)));
        holder.label.setText(String.valueOf(sentiment_label.get(position)));
        holder.dataLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecyclerViewTable.class);
                intent.putExtra("sentiment_id", String.valueOf(sentiment_id.get(holder.getAbsoluteAdapterPosition())));
                intent.putExtra("sentence", String.valueOf(sentiment_text.get(holder.getAbsoluteAdapterPosition())));
                intent.putExtra("label", String.valueOf(sentiment_label.get(holder.getAbsoluteAdapterPosition())));
//                ((Activity) context).startActivityForResult(intent, 1);
                activity.startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public int getItemCount() {
        return sentiment_id.size();
    }
    class DataView extends RecyclerView.ViewHolder {
        LinearLayout dataLayout;
        TextView sentiment_id, sentence, label;
        public DataView(View itemView) {
            super(itemView);
            sentiment_id = itemView.findViewById(R.id.sentiment_id);
            sentence = itemView.findViewById(R.id.sentence);
            label = itemView.findViewById(R.id.label);
            dataLayout = itemView.findViewById(R.id.dataLayout);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(Analysis.this, R.anim.translate_anim);
//            dataLayout.setAnimation(translate_anim);
        }
    }

}