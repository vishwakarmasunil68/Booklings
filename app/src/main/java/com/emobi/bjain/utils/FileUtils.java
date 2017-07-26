package com.emobi.bjain.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by sunil on 01-05-2017.
 */

public class FileUtils {
    public static String BASE_FILE_PATH= Environment.getExternalStorageDirectory().toString()+ File.separator+"booklings";

    public static void CreateBaseDir(){
        File f=new File(BASE_FILE_PATH);
        if(!f.exists()){
            f.mkdirs();
        }
    }
}
