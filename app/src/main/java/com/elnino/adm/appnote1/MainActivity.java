package com.elnino.adm.appnote1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GhiChuDao ghiChuDao;
    private ListView lv_list;
    final int RESULT_PRODUCT_ACTIVITY = 1;
    private List<GhiChu> dsGhiChu = new ArrayList<>();
    private EditText edt_title,edt_content;
    private ArrayAdapter<GhiChu> adapter;
    private ImageButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_title = findViewById(R.id.edttitle);
        edt_content = findViewById(R.id.edtcontent);

        findViewById(R.id.addbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("isupdate", false);
                intent.setClass(MainActivity.this, ThemGhiChu.class);
                startActivityForResult(intent, RESULT_PRODUCT_ACTIVITY);


            }
        });



        ghiChuDao = new GhiChuDao(this);
        dsGhiChu = ghiChuDao.getAllGhiChu();
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1 ,
                dsGhiChu);
        lv_list = findViewById(R.id.lvList);

        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showInfo(position);
            }
        });
        lv_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                deleteItem(position);
                return false;
            }
        });
    }
    private void showInfo(int position){
        GhiChu ghiChu = dsGhiChu.get(position);

        Intent i = new Intent(this, ThemGhiChu.class);
        i.putExtra("title",ghiChu.getTitle());
        i.putExtra("content",ghiChu.getContent());
        startActivity(i);
    }
    private void deleteItem(final int position){
        new AlertDialog.Builder(this).setTitle("Xoa Ghi Chu").setMessage("Ban Co muon xoa ghi chu khong ?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dsGhiChu.remove(position);
                        adapter.notifyDataSetChanged();
                    }

                }).setNegativeButton("Cancel",null).show();


    }

}
