package com.example.dacn.TheHocTap.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dacn.R;
import com.example.dacn.TheHocTap.Util.GeneralUtil;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements Filterable {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;
        public LinearLayout img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            content = itemView.findViewById(R.id.note_content);
            img = itemView.findViewById(R.id.linear);
        }
    }

    //private AdapterView.
    private List<Note> notes;
    private List<Note> copy;
    private Context context;

    public NotesAdapter(List<Note> notes/*, AdapterView.OnItemClickListener listener*/){
        this.notes = notes;
        copy = new ArrayList<>(notes);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int fontSize = GeneralUtil.getFontSize(prefs.getString("font_size", null));
        Note note = notes.get(position);

        // Set title, content, and color of the note
        TextView title_tv = holder.title;
        title_tv.setText(note.getTitle());
        title_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize + 3);

        TextView content_tv = holder.content;
        content_tv.setText(note.getContent());
        content_tv.setMaxLines(determineLines());


        content_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);

        LinearLayout view = holder.img;
        view.setBackgroundColor(note.getColor());

    }

    private int determineLines(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String font = prefs.getString("font_size", null);
        return 10;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getItem(int position){
        Log.d("index_test", "Current title: " + notes.get(position).getTitle());
        return notes.get(position);
    }

    private Filter filter = new Filter() {
        FilterResults results = new FilterResults();
        @Override
        protected FilterResults performFiltering(CharSequence query) {
            List<Note> filteredList = new ArrayList<>();

            if (query == null || query.toString().trim().length() == 0){
                filteredList.addAll(copy);
            } else {
                String search = query.toString().toLowerCase().trim();
                for (int i = 0; i < copy.size(); i++){
                    if (copy.get(i).getTitle().toLowerCase().contains(search))
                        filteredList.add(copy.get(i));
                }
            }

            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            notes.clear();
            notes.addAll((List<Note>)results.values);
            Log.d("index_test", "Current amt of notes: " + getItemCount());
            notifyDataSetChanged();
        }

    };

    @Override
    public Filter getFilter() {
        return filter;
    }
}
