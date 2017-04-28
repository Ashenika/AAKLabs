package com.example.main.smarttoothdetector;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** zedawOw- 2017/3/20 **/

public class Feature extends AppCompatActivity {

    Button button3;
    Button button4;
    TextView folder;

    String KEY_TEXTPSS = "TESTPASS";
    static final int DIALOG = 0;
    ListView d_list;

    File root;
    File currentf;

    private List<String> fileList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG);
            }
        });

        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        currentf = root;
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        Dialog dialog = null;

        switch (id) {
            case DIALOG:
                dialog = new Dialog(Feature.this);
                dialog.setContentView(R.layout.dialoglayout);
                dialog.setTitle("Custom Dialog");
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                folder = (TextView) dialog.findViewById(R.id.folder);
                button4 = (Button) dialog.findViewById(R.id.up);
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ListDir(currentf.getParentFile());
                    }
                });

                d_list = (ListView) dialog.findViewById(R.id.dialoglist);
                d_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File selected = new File(fileList.get(position));
                        if(selected.isDirectory()) {
                            ListDir(selected);
                        } else {
                            Toast.makeText(Feature.this, selected.toString() + " selected",
                                    Toast.LENGTH_LONG).show();
                            dismissDialog(DIALOG);
                        }
                    }
                });

                break;
        }
        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DIALOG:
                ListDir(currentf);
                break;
        }
    }

    void ListDir(File f) {
        if(f.equals(root)) {
            button4.setEnabled(false);
        } else {
            button4.setEnabled(true);
        }

        currentf = f;
        folder.setText(f.getPath());

        File[] files = f.listFiles();
        fileList.clear();

        for(File file : files) {
            fileList.add(file.getPath());
        }

        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, fileList);
        d_list.setAdapter(directoryList);
    }
}
