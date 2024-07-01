package com.example.allinone;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

public class MemoriesAdapter extends CursorAdapter {

    public MemoriesAdapter(Context context, Cursor cursor, boolean autoRequery) {
        super(context, cursor, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.memory_list_item, viewGroup, false);
        view.setTag(new ViewHolder(view));
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();

        Memory memory = new Memory(cursor);

        holder.titleTextView.setText(memory.getTitle());
        holder.imageView.setImageBitmap(memory.getImage());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).setTitledata((String) holder.titleTextView.getText());
                //((MainActivity)context).setImagedata(holder.imageView);

                //((MainActivity)context).setImageiddata(String.valueOf(cursor.getPosition()));
                //((MainActivity)context).replaceFragments(UpdateMemoryFragment.class);
            }
        });
    }

    private class ViewHolder {
        final ImageView imageView;
        final TextView titleTextView;

        ViewHolder(View view) {
            imageView = view.findViewById(R.id.list_item_image_view);
            titleTextView = view.findViewById(R.id.list_item_text_view);
        }
    }
}
