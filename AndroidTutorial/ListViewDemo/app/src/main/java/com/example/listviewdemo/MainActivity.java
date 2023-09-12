package com.example.listviewdemo;
import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.net.Uri;


public class MainActivity extends Activity
{
    // Array of strings...
    ListView serviceListView;
    String serviceList[] = {"Message", "Map", "Image "};
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      setContentView(R.layout.activity_main);
        serviceListView = (ListView)findViewById(R.id.serviceListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_service, R.id.service_item, serviceList);
        serviceListView.setAdapter(arrayAdapter);
        registerForContextMenu(serviceListView);

        serviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedServices = serviceList[i];
                if (selectedServices.equals("Message")) {
                    openMessageApp();
                } else if (selectedServices.equals("Map")) {
                    openMapActivity();
                }
            }
        });
    }
    private void openMessageApp(){
//        using service manager to open SMS app
        // if you want send message directly
//        String phoneNumber = "975-17414860"; // Replace with the desired phone number
//        String messageText = "Hello! This is a pre-filled message."; // Replace with the desired message text
//
//        Uri uri = Uri.parse("smsto:" + phoneNumber);
//        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
//        smsIntent.putExtra("sms_body", messageText);
//        startActivity(smsIntent);
////
        Intent smsIntent = new Intent(Intent.ACTION_MAIN);
        smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
        smsIntent.setType("vnd.android-dir/mms-sms");
        startActivity(smsIntent);


        // if you want to open whole message app
//        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//        smsIntent.setData(Uri.parse("sms:"));
//        startActivity(smsIntent);
//        Toast.makeText(MainActivity.this, "Open Message App", Toast.LENGTH_LONG).show();
    }
    public void openMapActivity(){
//         create another activity and input the address. get the address and locate it in the map
        Intent map_intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(map_intent);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.serviceListView) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            int position = info.position;

            if (position == 2) {
                getMenuInflater().inflate(R.menu.image_context_menu, menu);
            }
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.image_item1) {
            showImageDialog(R.drawable.ddc);
            return true;
        } else if (item.getItemId() == R.id.image_item2) {
            showImageDialog(R.drawable.ddc);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
    private void showImageDialog(int imageResource) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra("imageResource", imageResource);
        startActivity(intent);
    }



}
