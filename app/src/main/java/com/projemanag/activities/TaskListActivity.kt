package com.projemanag.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.projemanag.R
import com.projemanag.adapters.TaskListAdapter
import com.projemanag.firebase.FirestoreClass
import com.projemanag.model.Board
import com.projemanag.model.Task
import com.projemanag.utils.Constants
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)


        var boardDocumentId = ""
        if (intent.hasExtra(Constants.DOCUMENT_ID)){
            boardDocumentId = intent.getStringExtra(Constants.DOCUMENT_ID).toString()
        }

        showProgressDialog(resources.getString(R.string.please_wait))
        FirestoreClass().getBoardDetails(this@TaskListActivity, boardDocumentId)
    }

    private fun setupActionBar(title: String) {

        setSupportActionBar(toolbar_task_list_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
            actionBar.title = title
        }

        toolbar_task_list_activity.setNavigationOnClickListener { onBackPressed() }
    }



    fun boardDetails(board: Board) {
        hideProgressDialog()
        setupActionBar(board.name)

        val addTaskList = Task(resources.getString(R.string.add_list))
        board.taskList.add(addTaskList)


        rv_task_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_task_list.setHasFixedSize(true)


        val adapter = TaskListAdapter(this, board.taskList)
        rv_task_list.adapter = adapter
    }
}