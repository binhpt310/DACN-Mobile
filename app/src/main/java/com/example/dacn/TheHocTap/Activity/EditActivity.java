package com.example.dacn.TheHocTap.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dacn.R;
import com.example.dacn.TheHocTap.Model.Note;
import com.example.dacn.TheHocTap.Util.GeneralUtil;
import com.example.dacn.TheHocTap.Util.PrefsUtil;
import java.util.ArrayList;
import java.util.Date;
import com.github.dhaval2404.colorpicker.*;
import com.github.dhaval2404.colorpicker.listener.ColorListener;
import com.github.dhaval2404.colorpicker.listener.DismissListener;
import com.github.dhaval2404.colorpicker.model.ColorSwatch;
import com.github.dhaval2404.colorpicker.util.ColorUtil;

import org.jetbrains.annotations.NotNull;

public class EditActivity extends AppCompatActivity {
    private boolean isTrash;
    private boolean isFavorite;
    private boolean isOldNote;
    private boolean newFavorite;
    private int colorPicked;
    private int index;

    private SharedPreferences prefs;
    private Context mContext;
    private Menu mMenu;
    private ArrayList<Note> notesList;
    private EditText contentView;
    private EditText titleView;
    private String mMaterialColorCircle = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit_layout);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

        // Initialize fields
        isTrash = getIntent().getBooleanExtra("trash", false);
        isFavorite = getIntent().getBooleanExtra("favorite", false);
        isOldNote = getIntent().getBooleanExtra("oldNote", false);
        index = getIntent().getIntExtra("index", 0);
        colorPicked = Color.WHITE;
        mContext = this;

        TextView dateView = findViewById(R.id.date);
        titleView = findViewById(R.id.titleText);
        contentView = findViewById(R.id.editText);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int fontSize = GeneralUtil.getFontSize(prefs.getString("font_size", ""));
        notesList = getCurrentNoteList();

        // Set newFavorite as the value of isFavorite first so the toggle works correctly
        newFavorite = isFavorite;

        // Set attributes of EditTexts
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontSize + 5));
        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleView.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        contentView.setScrollY(0);

        if (isOldNote) { // Case where user is editing old note
            Note currentNote = getCurrentNoteList().get(index);
            // Set date information; uses substring because original date string includes seconds
            String dateString = currentNote.getDate().substring(0, currentNote.getDate().length() - 6)
                    + " " + currentNote.getDate().substring(currentNote.getDate().length() - 2);
            dateView.setText(getString(R.string.date_created, dateString));

            // Set content and title
            titleView.setText(currentNote.getTitle());
            contentView.setText(currentNote.getContent()); // Set the text on the note page as the old string

            // Set color
            colorPicked = currentNote.getColor();
            CardView cv = findViewById(R.id.card);
            cv.setCardBackgroundColor(colorPicked);

            // Set text color depending if color is dark or not
            if (GeneralUtil.isDarkColor(colorPicked, this)) {
                titleView.setTextColor(getResources().getColor(R.color.white));
                contentView.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    private ArrayList<Note> getCurrentNoteList(){
        if (isTrash)
            return PrefsUtil.getNotes("trash", this);
        else if (isFavorite)
            return PrefsUtil.getNotes("favorites", this);
        else {
            return PrefsUtil.getNotes("notes", this);
        }
    }

    private String returnKeyFromList(){
        if (isTrash)
            return "trash";
        else if (isFavorite)
            return "favorites";
        else return "notes";
    }

    private void returnToPreviousActivity(){
        Intent intent = new Intent(this, NoteMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (isTrash)
            intent.putExtra("trash", true);
        else if (isFavorite)
            intent.putExtra("favorite", true);

        startActivity(intent);
    }

    private void saveOldNoteData(Note note){
        notesList.remove(index);

        // If the note is NOT originally a favorited note and is now being favorited by the user
        if (!isFavorite && newFavorite) {
            ArrayList<Note> fav = PrefsUtil.getNotes("favorites", this);
            fav.add(0, note);
            PrefsUtil.saveNotes(fav, "favorites", this);
        } else if (isFavorite && !newFavorite){
            // If the note is being unfavorited
            ArrayList<Note> notes = PrefsUtil.getNotes("notes", this);
            notes.add(0, note);
            PrefsUtil.saveNotes(notes, "notes", this);
        } else
            notesList.add(0, note);
    }

    private void saveNewNoteData(Note note){
        if (newFavorite) {
            ArrayList<Note> fav = PrefsUtil.getNotes("favorites", this);
            fav.add(0, note);
            PrefsUtil.saveNotes(fav, "favorites", this);
        } else
            notesList.add(0, note);
    }

    private void saveText(){
        String contentText = contentView.getText().toString().trim();
        String titleText = titleView.getText().toString().trim();

        if (isOldNote) {
            Note note = getCurrentNoteList().get(index);
            note.setColor(colorPicked);
            note.setContent(contentText);
            note.setTitle(titleText);
            note.setFavorite(newFavorite);
            saveOldNoteData(note);
        } else {
            Note newNote = Note.createWithTitleAndContent(titleText, contentText);
            newNote.setFavorite(newFavorite);
            saveNewNoteData(newNote);
        }
        PrefsUtil.saveNotes(notesList, returnKeyFromList(), this);
        returnToPreviousActivity();
    }

    private void deleteNote(){
        ArrayList<Note> trashList = PrefsUtil.getNotes("trash", this);

        if (isTrash)
            trashList.remove(index);
        else {
            trashList.add(0, notesList.get(index));
            notesList.remove(index);
        }
        PrefsUtil.saveNotes(notesList, returnKeyFromList(), this);
        PrefsUtil.saveNotes(trashList, "trash", this);

        GeneralUtil.showShortToast(getString(R.string.delete_toast), this);
        returnToPreviousActivity();
    }

    private void restoreNote(){
        ArrayList<Note> trash, list;
        trash = PrefsUtil.getNotes("trash", this);
        list = PrefsUtil.getNotes("notes", this);

        list.add(0, trash.get(index));
        trash.remove(index);

        PrefsUtil.saveNotes(trash, "trash", this);
        PrefsUtil.saveNotes(list, "notes", this);

        GeneralUtil.showShortToast(getString(R.string.restore_toast), this);
        returnToPreviousActivity();
    }

    private void toggleFavoriteIcon(){
        if (newFavorite){
            mMenu.findItem(R.id.action_fav).setIcon(R.drawable.favorite_icon);
            mMenu.findItem(R.id.action_fav).setTitle(R.string.action_fav);
            newFavorite = false;
        } else {
            mMenu.findItem(R.id.action_fav).setIcon(R.drawable.favorite_icon_selected);
            mMenu.findItem(R.id.action_fav).setTitle(R.string.action_unfav);
            newFavorite = true;
        }
    }

    private void showColorDialog(){

        new MaterialColorPickerDialog
                .Builder(mContext)
                .setTitle("Chọn màu")
                //.setColorSwatch(ColorSwatch._500)
                //.setDefaultColor(mMaterialColorCircle)
                .setColorRes(getResources().getIntArray(R.array.color_array))
                .setColorListener(new ColorListener() {
                    @Override
                    public void onColorSelected(int color, @NotNull String colorHex) {
                        colorPicked = Color.parseColor(colorHex);
                        CardView cv = findViewById(R.id.card);
                        cv.setCardBackgroundColor(Color.parseColor(colorHex));
                    }
                })

                .setDismissListener(new DismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.d("ColorPickerDialog", "Dismiss");
                    }
                })
                .show();
    }

    private void setButtonBackground(int color) {
        if (ColorUtil.isDarkColor(color)) {
            contentView.setTextColor(Color.WHITE);
            titleView.setTextColor(Color.WHITE);
        } else {
            contentView.setTextColor(Color.BLACK);
            titleView.setTextColor(Color.BLACK);
        }
    }

    // Creates dialog for empty notes
    private void warningDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogThemeLight);
        builder.setTitle(getString(R.string.warning_title));
        builder.setMessage(getString(R.string.warning_confirm));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showDiscardDialog(){
        View checkBoxView = getLayoutInflater().inflate(R.layout.checkbox, null);
        CheckBox checkBox = checkBoxView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("back_dialog_toggle", false);
                editor.apply();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogThemeLight);
        builder.setView(checkBoxView);
        builder.setTitle(getString(R.string.discard_title));
        builder.setMessage(getString(R.string.discard_confirm));
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
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

    private void showConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogThemeLight);
        if (newFavorite) {
            builder.setTitle(getString(R.string.delete_favorite_title));
            builder.setMessage(getString(R.string.delete_favorite_message));

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        } else {
            builder.setTitle(getString(R.string.delete_title));
            builder.setMessage(getString(R.string.delete_confirm));
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteNote();
                }
            });

            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        AlertDialog alert = builder.create();
        alert.show();
    }

    // Notification Functions
    private Notification buildNotification(String title, String content) {
        // Create an Intent for the activity
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("oldNote", isOldNote);
        intent.putExtra("favorite", isFavorite);
        intent.putExtra("trash", isTrash);
        intent.putExtra("index", index);
        // Create the TaskStackBuilder and add the intent
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_MUTABLE);

        // Build the notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "NoteChannel")
                        .setSmallIcon(R.drawable.ic_event_icon)
                        .setContentTitle(title)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                        .setAutoCancel(true)
                        .setContentText(content)
                        .setContentIntent(resultPendingIntent)
                        .setGroupSummary(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder.build();
    }

    // Calls notify on the made notification
    private void showNotification(){
        String notifTitle = titleView.getText().toString().trim();
        String notifContent = contentView.getText().toString().trim();
        int id = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE); // Unique value for id
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(id, buildNotification(notifTitle, notifContent));
    }

    // Toolbar Functions
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;

        // Inflate the menu; this adds items to the action bar if it is present.
        if (isTrash)
            getMenuInflater().inflate(R.menu.trash_note_actions, menu);
        else
            getMenuInflater().inflate(R.menu.edit_actions, menu);

        // Change favorite icon depending on if the current note is favorited or not
        if (isFavorite) {
            menu.findItem(R.id.action_fav).setIcon(R.drawable.favorite_icon_selected);
            mMenu.findItem(R.id.action_fav).setTitle(R.string.action_unfav);
        }
        else {
            menu.findItem(R.id.action_fav).setIcon(R.drawable.favorite_icon);
            mMenu.findItem(R.id.action_fav).setTitle(R.string.action_fav);
        }
        return true;
    }

    private void homeAction(){
        Intent intent = new Intent(this, NoteMainActivity.class);
        intent.putExtra("caller", getIntent().getStringExtra("caller"));
        if (prefs.getBoolean("back_dialog_toggle", true))
            showDiscardDialog();
        else{
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                homeAction();
                return true;

            case R.id.action_save:
                if (contentView.length() == 0)
                    warningDialog();
                else
                    saveText();
                return true;

            case R.id.action_fav:
                toggleFavoriteIcon();
                return true;

            case R.id.action_restore:
                restoreNote();
                return true;

            case R.id.action_delete:
                if (!isOldNote)
                    GeneralUtil.goToActivity(NoteMainActivity.class, this);
                else
                    showConfirmDialog();
                return true;

            case R.id.action_pin:
                showNotification();
                return true;

            case R.id.action_color:
                showColorDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Shows a dialog when the user presses back while editing a note
    @Override
    public void onBackPressed() {
        if (prefs.getBoolean("back_dialog_toggle", true))
            showDiscardDialog();
        else
            finish();
    }
}