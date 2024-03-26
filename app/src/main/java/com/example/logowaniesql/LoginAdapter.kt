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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = logins.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val login = logins[position]
        holder.firstName.text
        holder.lastName.text
        holder.Email.text
        holder.password.text

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", login.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener {
            db.deleteNote(login.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note Delete", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newNotes:List<Login>){
        logins = newNotes
        notifyDataSetChanged()
    }
}