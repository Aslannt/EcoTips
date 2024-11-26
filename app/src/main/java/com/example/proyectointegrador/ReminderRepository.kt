package com.example.proyectointegrador

import android.util.Log
import com.google.firebase.database.*

class ReminderRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun addReminder(userId: String, reminder: Reminder, onComplete: (Boolean) -> Unit) {
        val reminderId = database.child("reminders").child(userId).push().key ?: return
        val reminderWithId = reminder.copy(id = reminderId)
        database.child("reminders").child(userId).child(reminderId)
            .setValue(reminderWithId)
            .addOnCompleteListener { task -> onComplete(task.isSuccessful) }
    }

    fun getReminders(userId: String, onResult: (List<Reminder>) -> Unit) {
        database.child("reminders").child(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        val reminders = snapshot.children.mapNotNull {
                            val reminder = it.getValue(Reminder::class.java)
                            if (reminder != null) {
                                reminder
                            } else {
                                Log.e("ReminderRepository", "Invalid reminder data: ${it.value}")
                                null
                            }
                        }
                        onResult(reminders)
                    } catch (e: DatabaseException) {
                        Log.e("ReminderRepository", "Error converting data: ${e.message}")
                        onResult(emptyList())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("ReminderRepository", "Database error: ${error.message}")
                    onResult(emptyList())
                }
            })
    }

    fun completeReminder(userId: String, reminderId: String, isCompleted: Boolean, onComplete: (Boolean) -> Unit) {
        database.child("reminders").child(userId).child(reminderId).child("completed")
            .setValue(isCompleted)
            .addOnCompleteListener { task -> onComplete(task.isSuccessful) }
    }
}
