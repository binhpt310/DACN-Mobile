package com.example.dacn.TheHocTap.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;
import com.example.dacn.TheHocTap.Activity.EditActivity;
import com.example.dacn.TheHocTap.ItemClickSupport;
import com.example.dacn.TheHocTap.Model.Note;
import com.example.dacn.TheHocTap.Model.NotesAdapter;
import com.example.dacn.TheHocTap.Util.GeneralUtil;
import com.example.dacn.TheHocTap.Util.PrefsUtil;
import com.example.dacn.mucluc.mucluc;
import com.example.dacn.trangchu2;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class NoteFragment extends Fragment {
    private ArrayList<Note> notes;
    private GeneralUtil UTIL; // Needed for sort method
    private Context context;
    private boolean isSearched;
    private FloatingActionMenu fam;
    private NotesAdapter adapter;
    private int selectedSortItem = 4;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_recycler_layout, container, false);
        UTIL = new GeneralUtil(requireActivity());
        if (isAdded()) context = getActivity();
        ((AppCompatActivity) context).getSupportActionBar().setTitle("Thẻ học tập");

        fam = requireActivity().findViewById(R.id.fam);
        fam.setVisibility(View.VISIBLE);
        fam.setClosedOnTouchOutside(true);

        SharedPreferences sharedPreferences =
                requireActivity().getSharedPreferences("NOTES", Context.MODE_PRIVATE);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_notes);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        notes = PrefsUtil.getNotes("notes", context);

        int sortValue = sharedPreferences.getInt("sort_index", 0);
        if (notes.size() > 0)
            UTIL.sortNotes(sortValue, notes, "notes");
        else {
            TextView defaultText = view.findViewById(R.id.clear_text);
            defaultText.setText(getResources().getString(R.string.notes_empty));
        }

        adapter = new NotesAdapter(notes);
        adapter.notifyDataSetChanged();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(listener);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setHasOptionsMenu(true);
        return view;
    }

    ItemClickSupport.OnItemClickListener listener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            Intent intent = new Intent(context, EditActivity.class);
            Note current = adapter.getItem(position);

            if (isSearched)
                intent.putExtra("index", getIndexOfSearchedNote(current));
            else
                intent.putExtra("index", position);

            intent.putExtra("oldNote", true);
            startActivity(intent);
            fam.close(true);
        }
    };

    private int getIndexOfSearchedNote(Note note){
        for (int i = 0; i < PrefsUtil.getNotes("notes", context).size(); i++)
            if (note.equals(PrefsUtil.getNotes("notes", context).get(i)))
                return i;
        return 0;
    }

    // Create dialog for sorting notes
    private void sortDialog() {
        final SharedPreferences prefs = context.getSharedPreferences("NOTES", Context.MODE_PRIVATE);
        if (selectedSortItem != prefs.getInt("sort_index", 0)){
            selectedSortItem = prefs.getInt("sort_index", 0);
        }

        String[] items = getResources().getStringArray(R.array.sort_values);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogThemeLight);
        builder.setTitle("Sắp xếp");
        builder.setCancelable(true);
        builder.setSingleChoiceItems(items, selectedSortItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int index) {
                selectedSortItem = index;
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("sort_index", index);
                editor.apply();
            }
        });

        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("selected_index", selectedSortItem + "");
                dialogInterface.dismiss();
                UTIL.sortNotes(selectedSortItem, notes, "notes");
                adapter.notifyDataSetChanged();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }

    // Creates dialog for the clear
    private void createDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogThemeLight);
        builder.setTitle(getString(R.string.clear_notes_title));
        builder.setMessage(getString(R.string.clear_notes_text));
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearNotes();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    // Remove notes by clearing note ArrayList and resetting linear layout.
    private void clearNotes(){
        ArrayList<Note> trash = PrefsUtil.getNotes("trash", context);
        trash.addAll(notes);
        int size = notes.size();
        notes.clear();

        PrefsUtil.saveNotes(notes, "notes", context);
        PrefsUtil.saveNotes(trash, "trash", context);

        adapter.notifyItemRangeRemoved(0, size);
        Toast.makeText(getActivity(), getString(R.string.clear_notes_toast), Toast.LENGTH_LONG).show();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreateOptionsMenu(final Menu menu, @NonNull final MenuInflater inflater) {
        inflater.inflate(R.menu.notes_actions, menu);
        if (menu instanceof MenuBuilder)
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        // Initialize the searchview in the toolbar
        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        myActionMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Log.d("sv_test", "opened");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                isSearched = false;
                return true;
            }
        });

        searchView.setOnSearchClickListener(new SearchView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("sv_test", "opened");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                adapter.getFilter().filter(text);
                isSearched = true;
                return true;
            }
        });

        //Back button
//        final MenuItem backButton = menu.findItem(R.id.action_back);
//        backButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Intent myIntent = new Intent();
//                myIntent.setClassName("com.example.dacn", "com.example.dacn.trangchu2");
//                // for ex: your package name can be "com.example"
//                // your activity name will be "com.example.Contact_Developer"
//                startActivity(myIntent);
//                return true;
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                createDialog();
                return true;

            case R.id.action_sort:
                sortDialog();
                return true;

            case R.id.action_back:
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.dacn", "com.example.dacn.trangchu2");
                startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
