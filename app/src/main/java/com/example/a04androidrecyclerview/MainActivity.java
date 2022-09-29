package com.example.a04androidrecyclerview;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a04androidrecyclerview.databinding.ActivityMainBinding;
import com.example.a04androidrecyclerview.MountainAdapter;
import com.example.a04androidrecyclerview.MountainData;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList mWordList = new LinkedList<String>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private ArrayList<MountainData> mountainList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMountainList();
        @NonNull ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        mRecyclerView = findViewById(R.id.recyclerview);
        MountainAdapter mountainAdapter = new MountainAdapter(MainActivity.this, mountainList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mountainAdapter);
        mountainAdapter.setOnItemClickListener(onItemClickListener);
    }

    private void setMountainList() {
        mountainList = new ArrayList<>();
        MountainData data;
        data = new MountainData(getString(R.string.semeru_name), getString(R.string.semeru_description), R.drawable.semeru, getString(R.string.semeru_details));
        mountainList.add(data);
        data = new MountainData(getString(R.string.arjuno_name), getString(R.string.arjuno_description), R.drawable.arjuno, getString(R.string.arjuno_details));
        mountainList.add(data);
        data = new MountainData(getString(R.string.merapi_name), getString(R.string.merapi_description), R.drawable.merapi, getString(R.string.merapi_details));
        mountainList.add(data);
        data = new MountainData(getString(R.string.lawu_name), getString(R.string.lawu_description), R.drawable.lawu, getString(R.string.lawu_details));
        mountainList.add(data);
        data = new MountainData(getString(R.string.gede_name), getString(R.string.gede_description), R.drawable.gede, getString(R.string.gede_details));
        mountainList.add(data);

    }

    public void openDetailActivity(int imageId, String details){
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("image", imageId);
        intent.putExtra("details", details);
        startActivity(intent);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            MountainData thisMountain = mountainList.get(position);
            openDetailActivity(thisMountain.getImage(), thisMountain.getDetails());
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}