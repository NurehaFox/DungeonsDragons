package com.softwareproject.dungeonsdragons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DMMainScreenActivity extends AppCompatActivity {

    private Button PartyMembers;
    private Button Items;
    private Button Monsters;
    private Button NPC;
    private Button Rules;
    private Button Notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmmain_screen);
        //Open Party Members Activity
        PartyMembers = (Button) findViewById(R.id.PartyMembers);
        PartyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPartyActivity();
            }
        });
        //Open Items Activity
        Items = (Button) findViewById(R.id.Items);
        Items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemsActivity();
            }
        });
        //Open Monsters Activity
        Monsters = (Button) findViewById(R.id.Monsters);
        Monsters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMonstersActivity();
            }
        });
        //Open NPC Activity
        NPC = (Button) findViewById(R.id.NPC);
        NPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNPCActivity();
            }
        });
        //Open Rules Activity
        Rules = (Button) findViewById(R.id.Rules);
        Rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRulesActivity();
            }
        });
        //Open Notes Activity
        Notes = (Button) findViewById(R.id.Notes);
        Notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNotesActivity();
            }
        });

    }
    private void openPartyActivity() {
        Intent intent = new Intent(this, PartyMembersActivity.class);
        startActivity(intent);
    }
    private void openItemsActivity() {
        Intent intent = new Intent(this, ItemsActivity.class);
        startActivity(intent);
    }
    private void openMonstersActivity() {
        Intent intent = new Intent(this, MonstersActivity.class);
        startActivity(intent);
    }
    private void openNPCActivity() {
        Intent intent = new Intent(this, NPCActivity.class);
        startActivity(intent);
    }
    private void openRulesActivity() {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }
    private void openNotesActivity() {
        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }
}
