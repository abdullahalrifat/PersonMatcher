package com.example.abdullah.personmatcher.Menu;

import com.example.abdullah.personmatcher.DataBase.ReadList;
import com.example.abdullah.personmatcher.DataBase.ReadMenu;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abdullah on 2/28/17.
 */

public class FindList
{
    private static ArrayList< HashMap<String, ArrayList>> lists = new ArrayList<HashMap<String, ArrayList>>();
    String php;
    private static final String TAG_MENUNAME = "MenuName";
    private static final String TAG_FILENAME = "FileName";

    public static ArrayList<HashMap<String, ArrayList>> getLists() {
        return lists;
    }

    public void load()
    {
        FindFileNames files=new FindFileNames();
        files.Load();
        ArrayList fi=files.getResults();
        for(int i=0;i<fi.size();i++)
        {
            ReadList read=new ReadList();
            HashMap<String, String> tmpData = (HashMap<String,String>) fi.get(i);
            String Name=tmpData.get(TAG_FILENAME);
            read.setPHP(Name);
            read.load();
            ArrayList list=new ArrayList();
            list=read.getList();
            HashMap<String, ArrayList>tmp=new HashMap<String, ArrayList>();
            tmp.put(tmpData.get(TAG_MENUNAME), list);
            lists.add(tmp);
        }
    }

}
