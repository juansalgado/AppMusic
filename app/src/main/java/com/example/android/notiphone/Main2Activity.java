package com.example.android.notiphone;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 1;
    MediaPlayer mp;
    String name = "notiphone";
    int b = -1;
    boolean a = false;
    final int[] textViewIds = {
            R.id.textView01,
            R.id.textView02,
            R.id.textView03,
            R.id.textView04,
            R.id.textView05,
            R.id.textView06,
            R.id.textView07,
            R.id.textView08,
            R.id.textView09,
            R.id.textView10,
            R.id.textView11
    };

    final int[] soundIds = {
            R.raw.classical1,
            R.raw.classical2,
            R.raw.classical3,
            R.raw.classical4,
            R.raw.classical5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mp = MediaPlayer.create(Main2Activity.this, R.raw.classical1);


        TextView textView1 = (TextView) findViewById(R.id.textView01);
        TextView textView2 = (TextView) findViewById(R.id.textView02);
        TextView textView3 = (TextView) findViewById(R.id.textView03);
        TextView textView4 = (TextView) findViewById(R.id.textView04);
        TextView textView5 = (TextView) findViewById(R.id.textView05);
        Button textView6 = (Button) findViewById(R.id.textView06);
        Button textView7 = (Button) findViewById(R.id.textView07);
        Button textView8 = (Button) findViewById(R.id.textView08);
        Button textView9 = (Button) findViewById(R.id.textView09);
        Button textView10 = (Button) findViewById(R.id.textView10);
        Button textView11 = (Button) findViewById(R.id.textView11);


        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView8.setOnClickListener(this);
        textView9.setOnClickListener(this);
        textView10.setOnClickListener(this);
        textView11.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        (findViewById(textViewIds[5])).setBackgroundResource(R.drawable.photo01);
        (findViewById(textViewIds[6])).setBackgroundResource(R.drawable.photo02);


        for (int i = 0; i < textViewIds.length; i++) {

            if (v.getId() == textViewIds[i]) {
                if (i < 5) {
                    if (b > -1) {
                        (findViewById(textViewIds[b])).setBackgroundResource(R.color.classical);
                    }
                    b = i;

                    (findViewById(textViewIds[i])).setBackgroundResource(R.drawable.select);
                    playMyMusic(soundIds[i]);
                } else {

                    if (i < 7) {
                        if (b > -1) {
                            if (i == 6) {
                                (findViewById(textViewIds[6])).setBackgroundResource(R.drawable.photo04);


                                final CharSequence[] items = {"Set as Notification", "Cancel"};
                                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                                builder.setItems(items, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {
                                        switch (item) {
                                            case 0:
                                                a = false;

                                                requestQuestion();
                                                Toast.makeText(getApplicationContext(), "Notification changed", Toast.LENGTH_SHORT).show();
                                                break;
                                            case 1:


                                                break;
                                            default:
                                                (findViewById(textViewIds[6])).setBackgroundResource(R.drawable.photo02);
                                                break;
                                        }
                                        dialog.cancel();
                                        (findViewById(textViewIds[6])).setBackgroundResource(R.drawable.photo02);
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();


                            } else {
                                (findViewById(textViewIds[5])).setBackgroundResource(R.drawable.photo03);


                                final CharSequence[] items = {"Set as Ringtone", "Cancel"};
                                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                                builder.setItems(items, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {
                                        switch (item) {
                                            case 0:
                                                a = true;

                                                requestQuestion();
                                                Toast.makeText(getApplicationContext(), "Ringtone changed ", Toast.LENGTH_SHORT).show();
                                                break;
                                            case 1:


                                                break;
                                            default:
                                                (findViewById(textViewIds[6])).setBackgroundResource(R.drawable.photo01);
                                                break;
                                        }
                                        dialog.cancel();
                                        (findViewById(textViewIds[5])).setBackgroundResource(R.drawable.photo01);
                                    }
                                });

                                AlertDialog alert = builder.create();
                                alert.show();

                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "No track selected", Toast.LENGTH_SHORT).show();
                            break;

                        }
                    } else {
                        if (i == 7) {
                            if (b > -1) {
                                (findViewById(textViewIds[b])).setBackgroundResource(R.color.classical);
                            } else {
                                b = 5;
                            }
                            b -= 1;
                            if (b < 0) b = 4;

                            (findViewById(textViewIds[b])).setBackgroundResource(R.drawable.select);


                            playMyMusic(soundIds[b]);
                        }


                        if (i == 8) {

                            if (b > -1) {

                                (findViewById(textViewIds[b])).setBackgroundResource(R.color.classical);
                            }
                            b += 1;
                            if (b > 4) b = 0;

                            (findViewById(textViewIds[b])).setBackgroundResource(R.drawable.select);


                            playMyMusic(soundIds[b]);


                        }


                        if (i == 9) {

                            if (!mp.isPlaying()) {
                                playing();
                                if (b < 0) b = 0;
                                (findViewById(textViewIds[b])).setBackgroundResource(R.drawable.select);
                            } else if (mp.isPlaying()) {
                                mp.pause();
                                (findViewById(textViewIds[b])).setBackgroundColor(Color.DKGRAY);
                                (findViewById(textViewIds[9])).setBackgroundResource(R.drawable.play);

                            }
                        }

                        if (i == 10) {
                            finish();
                        }


                    }

                }

            }


        }


    }

    private void playMyMusic(int t) {

        stopPlaying();
        mp = MediaPlayer.create(Main2Activity.this, t);
        playing();
    }

    private void playing() {

        (findViewById(R.id.textView10)).setBackgroundResource(R.drawable.pause);
        mp.start();


    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    private void requestQuestion() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                if (Settings.System.canWrite(Main2Activity.this)) {
                    setRingtone();
                } else {
                    Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS)
                            .setData(Uri.parse("package:" + getPackageName()))
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


            } else {
                requestPermission();
            }
        } else {
            setRingtone();

        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(Main2Activity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= 23) {
                        if (Settings.System.canWrite(Main2Activity.this)) {
                            setRingtone();
                        } else {
                            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS)
                                    .setData(Uri.parse("package:" + getPackageName()))
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                }
                break;
        }
    }


    private void setRingtone() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "/Ringtones/");
        if (!file.exists()) {
            file.mkdirs();
        }


        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Ringtones/";

        name = "classical" + (b + 1);

        File f = new File(path, name + ".mp3");

        Uri mUri = Uri.parse("android.resource://"
                + this.getPackageName() + "/raw/" + name);
        ContentResolver mCr = this.getContentResolver();

        AssetFileDescriptor soundFile;
        try {
            soundFile = mCr.openAssetFileDescriptor(mUri, "r");
        } catch (FileNotFoundException e) {


            soundFile = null;
        }

        try {
            byte[] readData = new byte[1024];
            FileInputStream fis = soundFile.createInputStream();
            FileOutputStream fos = new FileOutputStream(f);
            int i = fis.read(readData);

            while (i != -1) {
                fos.write(readData, 0, i);
                i = fis.read(readData);
            }

            fos.close();
        } catch (IOException io) {

        }
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, f.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, name);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
        values.put(MediaStore.MediaColumns.SIZE, f.length());
        values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        values.put(MediaStore.Audio.Media.IS_ALARM, true);
        values.put(MediaStore.Audio.Media.IS_MUSIC, true);

        Uri uri = MediaStore.Audio.Media.getContentUriForPath(f
                .getAbsolutePath());

        getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + f.getAbsolutePath() + "\"",
                null);

        Uri newUri = mCr.insert(uri, values);


        if (a) {


            try {
                RingtoneManager.setActualDefaultRingtoneUri(this,
                        RingtoneManager.TYPE_RINGTONE, newUri);
                Settings.System.putString(mCr, Settings.System.RINGTONE,
                        newUri.toString());
            } catch (Throwable t) {
            }

        } else {

            try {
                RingtoneManager.setActualDefaultRingtoneUri(this,
                        RingtoneManager.TYPE_NOTIFICATION, newUri);
                Settings.System.putString(mCr, Settings.System.NOTIFICATION_SOUND,
                        newUri.toString());
            } catch (Throwable t) {
            }

        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlaying();
    }

}
