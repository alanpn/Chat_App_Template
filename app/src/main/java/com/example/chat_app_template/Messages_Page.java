package com.example.chat_app_template;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Messages_Page extends AppCompatActivity
{
    private ArrayList<String> mUsername=new ArrayList<>();
    private ArrayList<String> mNotification=new ArrayList<>();
    private ArrayList<String> mUid=new ArrayList<>();
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FloatingActionButton btnAddMessage;
    private ImageView noMessagesVectorart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages__page);
        btnAddMessage=findViewById(R.id.btnAddMessage);

        btnAddMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Messages_Page.this,Users_List_Chat.class);
                startActivity(intent);
            }
        });

        noMessagesVectorart=findViewById(R.id.noMessageVectorArt);
        if(mUsername.size()!=0)
        {
            noMessagesVectorart.setVisibility(View.INVISIBLE);
        }

        initImageBitmaps();
    }
    private void initImageBitmaps()
    {
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_messages);
        RecyclerViewAdapter_Messages adapter = new RecyclerViewAdapter_Messages(Messages_Page.this, mUsername, mNotification,mUid);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Messages_Page.this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        if(item.getItemId()==R.id.btnLogout);
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Messages_Page.this, MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
