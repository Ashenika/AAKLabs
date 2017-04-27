package com.example.main.smarttoothdetector;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

public class Feature extends AppCompatActivity {

    Button buttonLive;
   // private String Package = "com.wifiview.endoscope";
   // private String className = "com.wifiview.endoscope.BuildConfig";


    Button buttonOpenDialog;
    Button buttonUp;
    TextView textFolder;

    String KEY_TEXTPSS = "TEXTPSS";
    static final int CUSTOM_DIALOG_ID = 0;
    ListView dialog_ListView;

    File root;
    File curFolder;

    private List<String> fileList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);

        buttonOpenDialog = (Button) findViewById(R.id.button3);
        buttonOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(CUSTOM_DIALOG_ID);
            }
        });

        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        curFolder = root;


        buttonLive = (Button)findViewById(R.id.buttonLive);
        buttonLive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openWiFiView(Feature.this,"com.wifiview.endoscope" );
            }
        }
        );

    }
    public static boolean openWiFiView(Context context, String packageName){
        PackageManager manager = context.getPackageManager();
        Intent i = manager.getLaunchIntentForPackage(packageName);
        if(i == null ){
            return false;
        }
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);
        return true;
    }

   /* protected void call(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(new ComponentName(Package,className));
        try{
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    @Override
    protected Dialog onCreateDialog(int id) {

        Dialog dialog = null;

        switch (id) {
            case CUSTOM_DIALOG_ID:
                dialog = new Dialog(Feature.this);
                dialog.setContentView(R.layout.dialoglayout);
                dialog.setTitle("Custom Dialog");
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                textFolder = (TextView) dialog.findViewById(R.id.folder);
                buttonUp = (Button) dialog.findViewById(R.id.up);
                buttonUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ListDir(curFolder.getParentFile());
                    }
                });

                dialog_ListView = (ListView) dialog.findViewById(R.id.dialoglist);
                dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File selected = new File(fileList.get(position));
                        if(selected.isDirectory()) {
                            ListDir(selected);
                        } else {
                            Toast.makeText(Feature.this, selected.toString() + " selected",
                                    Toast.LENGTH_LONG).show();
                            dismissDialog(CUSTOM_DIALOG_ID);
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
            case CUSTOM_DIALOG_ID:
                ListDir(curFolder);
                break;
        }
    }

    void ListDir(File f) {
        if(f.equals(root)) {
            buttonUp.setEnabled(false);
        } else {
            buttonUp.setEnabled(true);
        }

        curFolder = f;
        textFolder.setText(f.getPath());

        File[] files = f.listFiles();
        fileList.clear();

        for(File file : files) {
            fileList.add(file.getPath());
        }

        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, fileList);
        dialog_ListView.setAdapter(directoryList);
    }
}
