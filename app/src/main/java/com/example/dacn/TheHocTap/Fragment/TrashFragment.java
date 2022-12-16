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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;
import com.example.dacn.TheHocTap.Activity.EditActivity;
import com.example.dacn.TheHocTap.ItemClickSupport;
import com.example.dacn.TheHocTap.Model.Note;
import com.example.dacn.TheHocTap.Model.NotesAdapter;
import com.example.dacn.TheHocTap.Util.GeneralUtil;
import com.example.dacn.TheHocTap.Util.PrefsUtil;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class TrashFragment extends Fragment {
    private View view;
    private ArrayList<Note> noteList;
    private GeneralUtil UTIL;
    private Context context;
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private boolean isSearched = false;
    private int selectedSortItem = 4;


    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Thùng rác");
        if (isAdded()) context = getActivity();

        FloatingActionMenu fam = requireActivity().findViewById(R.id.fam);
        fam.close(true);
        fam.setVisibility(View.GONE);

        view = inflater.inflate(R.layout.note_recycler_layout, container, false);
        UTIL = new GeneralUtil(context);

        Intent def = new Intent();
        def.putExtra("isTrash", true);
        noteList = PrefsUtil.getNotes("trash", context);
        recyclerView = view.findViewById(R.id.recycler_notes);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(context, EditActivity.class);
                Note current = adapter.getItem(position);

                if (isSearched) {
                    for (int i = 0; i < PrefsUtil.getNotes("trash", context).size(); i++) {
                        if (current.equals(PrefsUtil.getNotes("trash", context).get(i))) {
                            intent.putExtra("index", i);
                            break;
                        }
                    }
                } else {
                    intent.putExtra("index", position);
                }
                intent.putExtra("oldNote", true);
                intent.putExtra("trash", true);
                startActivity(intent);
            }
        });

        if (noteList.size() > 0) {
            adapter = new NotesAdapter(noteList);
            UTIL.sortNotes(context.getSharedPreferences("NOTES", Context.MODE_PRIVATE).getInt("sort_index", 0),
                    noteList, "trash");
            adapter.notifyDataSetChanged();
        } else { // Show text showing the trash is empty
            TextView defaultText = view.findViewById(R.id.clear_text);
            defaultText.setText(getResources().getString(R.string.trash_empty));
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setHasOptionsMenu(true);
        return view;
    }

    private void confirmDialog(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog
                .Builder(context, R.style.DialogThemeLight);
        builder.setTitle("Dọn thùng rác");
        builder.setMessage(getResources().getString(R.string.trash_clear_confirm));
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

        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    private void clearNotes(){
        int size = noteList.size();
        noteList.clear();

        adapter.notifyItemRangeRemoved(0, size);

        if (getActivity() != null) {
            PrefsUtil.saveNotes(noteList, "trash", context);
        } else Log.e("null_err", "TrashFragment getActivity() in clearNotes() returns null");

        TextView defaultText = view.findViewById(R.id.clear_text);
        defaultText.setText(getResources().getString(R.string.trash_empty));

        Toast.makeText(getActivity().getApplicationContext(), "Trash emptied", Toast.LENGTH_LONG).show();
    }

    // Create dialog for sorting notes
    private void sortDialog() {
        int size = noteList.size();
        if (size > 0){
            final SharedPreferences prefs = getActivity().getSharedPreferences("NOTES", Context.MODE_PRIVATE);
            if (selectedSortItem != prefs.getInt("sort_index", 0)){
                selectedSortItem = prefs.getInt("sort_index", 0);
            }

            String[] items = getResources().getStringArray(R.array.sort_values);
            AlertDialog.Builder builder = new AlertDialog
                    .Builder(context, R.style.DialogThemeLight);
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
                    UTIL.sortNotes(selectedSortItem, noteList, "trash");
                    adapter.notifyDataSetChanged();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.show();}
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.trash_actions, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_empty) {
            if (noteList.size() != 0)
                confirmDialog();
            else {  // Case where the trash is already empty
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog
                        .Builder(requireActivity(), R.style.DialogThemeLight);
                builder.setTitle(R.string.clear_error_title);
                builder.setMessage(getResources().getString(R.string.trash_error));
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                android.app.AlertDialog alert = builder.create();
                alert.show();
            }
            return true;
        } else if (item.getItemId() == R.id.action_sort){ // Case where user selects sort
            sortDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
