package com.example.logowaniesql
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class LoginAdapter(private  var logins: List<Login>, context: Context):
    RecyclerView.Adapter<LoginAdapter.NoteViewHolder>() {

    private val db : LoginDatabaseHelper = LoginDatabaseHelper(context)
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val firstName : EditText = itemView.findViewById(R.id.firstName)
        val lastName : EditText = itemView.findViewById(R.id.lastName)
        val Email : EditText = itemView.findViewById(R.id.Email)
        val password : EditText = itemView.findViewById(R.id.password)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = logins.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val login = logins[position]
        holder.firstName.text = login.title
        holder.lastName.text = login.content

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note Delete", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newNotes:List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }
}