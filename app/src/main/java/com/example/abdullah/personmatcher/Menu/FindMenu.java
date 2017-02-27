package com.example.abdullah.personmatcher.Menu;

import com.example.abdullah.personmatcher.DataBase.ReadMenu;

import java.util.ArrayList;

/**
 * Created by abdullah on 2/27/17.
 */

public class FindMenu
{
    private static ArrayList results = new ArrayList<>();

    public ArrayList getResults() {
        return results;
    }

    public void Load()
    {
        ReadMenu read=new ReadMenu();
        read.load();
        results=read.getList();
    }
}
