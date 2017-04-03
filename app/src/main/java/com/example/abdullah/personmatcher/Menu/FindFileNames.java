package com.example.abdullah.personmatcher.Menu;

import com.example.abdullah.personmatcher.DataBase.ReadMenu;
import com.example.abdullah.personmatcher.DataBase.ReadPhpFiles;

import java.util.ArrayList;

/**
 * Created by abdullah on 2/28/17.
 */

public class FindFileNames
{
    private static ArrayList files = new ArrayList<>();

    public ArrayList getResults() {
        return files;
    }

    public void Load() throws Exception
    {
        ReadPhpFiles read=new ReadPhpFiles();
        read.load();
        files=read.getList();
    }
}
