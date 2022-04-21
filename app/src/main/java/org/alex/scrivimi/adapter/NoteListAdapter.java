package org.alex.scrivimi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.ramotion.foldingcell.FoldingCell;
import org.alex.scrivimi.R;
import org.alex.scrivimi.data.Note;
import org.alex.scrivimi.data.NoteManager;
import org.alex.scrivimi.fragments.FragmentsLoader;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteHolder> {
   private List<Note> listNote;
   private FragmentsLoader loader;
   public NoteListAdapter(List<Note> listNote, FragmentsLoader loader) {
       this.loader = loader;
       this.listNote = listNote;
   }

    @NonNull
    @Override
    public NoteListAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View noteView = inflater.inflate(R.layout.note_item,null);
       final FoldingCell foldingNote = noteView.findViewById(R.id.foldingNote);
       foldingNote.setOnClickListener(source->foldingNote.toggle(false));
       return new NoteHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.NoteHolder holder, int position) {
        final Note currentNote = this.listNote.get(position);
        holder.idNote.setText(String.valueOf(currentNote.getIdNota()));
        holder.noteTitle.setText(currentNote.getTitolo());
        holder.noteContent.setText(currentNote.getTesto());
        holder.noteDate.setText(holder.noteDate.getText()+currentNote.getDataPubblicazione());
        holder.delete.setOnClickListener(source->{
            removeItemFromDb(currentNote.getIdNota(), source.getContext());
            this.listNote.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,this.listNote.size());
         });
    }

    @Override
    public int getItemCount() {
        return this.listNote.size();
    }
    public  static class NoteHolder extends RecyclerView.ViewHolder {
       public TextView idNote,noteTitle,noteDate,noteContent;
       public ImageView update,delete;
       public NoteHolder(View noteView) {
           super(noteView);
           this.idNote = noteView.findViewById(R.id.idNoteLabel);
           this.noteTitle = noteView.findViewById(R.id.titleNoteLabel);
           this.noteDate = noteView.findViewById(R.id.noteDateText);
           this.noteContent = noteView.findViewById(R.id.contentNoteText);
           this.delete = noteView.findViewById(R.id.deleteImg);
           this.update = noteView.findViewById(R.id.editImg);
       }
    }
    private void removeItemFromDb(int pk, Context ctx) {
        NoteManager manager = Room.databaseBuilder(ctx,NoteManager.class,"scrivimi").build();
        ExecutorService eraseService = Executors.newSingleThreadExecutor();
        eraseService.submit(()->{
            manager.getDAO().delete(pk);
            manager.close();
        });
        eraseService.shutdown();
        Toast.makeText(ctx, ctx.getString(R.string.removed_note_message),Toast.LENGTH_LONG).show();
    }
}
