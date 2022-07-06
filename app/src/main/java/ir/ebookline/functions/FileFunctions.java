package ir.ebookline.functions;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileFunctions {

    Context con;


    public FileFunctions(Context c)
    {
        this.con =c;
    }

    public boolean fileIsExist(File file)
    {
        if(file == null)
            return false;

        if(file.isDirectory())
            return false;

        if(file.isFile() && file.exists())
            return true;
        else
            return false;
    }

    public boolean folderIsExist(File pathFolder)
    {
        if(pathFolder == null)
            return false;

        if(pathFolder.isFile())
            return false;

        if(pathFolder.isDirectory() && pathFolder.exists())
            return true;
        else
            return false;
    }

    public boolean createFolder(String folderName)
    {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + folderName);
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success)
        {
            return  true;
        } else {
            return false;
        }
    }

    public boolean createFile(String path,String name)
    {
        File file = new File(path ,name);
        if (!file.exists()) {
            file.mkdir();
            return true;
        } else {
            return false;
        }
    }

}
