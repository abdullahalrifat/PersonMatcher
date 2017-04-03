package com.example.abdullah.personmatcher.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.abdullah.personmatcher.Activity.AlertDialogueBuilder;
import com.example.abdullah.personmatcher.MainActivity;
import com.example.abdullah.personmatcher.Menu.FindList;
import com.example.abdullah.personmatcher.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private static final String TAG_PID = "id";
    private static final String TAG_NAME = "Name";
    private static final String TAG_LOCATION = "Location";
    private static final String TAG_PHONE = "Phone";
    private static final String TAG_LEVEL = "Level";
    private static ArrayList<HashMap<String, ArrayList>> lists = new ArrayList<HashMap<String, ArrayList>>();
    public static String SelectedButton;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View RootView;
    private OnFragmentInteractionListener mListener;
    ListView listView ;
    public ListFragment() {
        // Required empty public constructor

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RootView=inflater.inflate(R.layout.fragment_list, container, false);
        loadList();
        // Inflate the layout for this fragment
        return RootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    ArrayList<HashMap<String, String>> values=new ArrayList<>();
    public void loadList()
    {
        SelectedButton=CatagoryFragment.SelectedButton;
        // Get ListView object from xml
         listView = (ListView) RootView.findViewById(R.id.list);
        FindList f=new FindList();
        this.lists= f.getLists();
        values.clear();

        for(int i=0;i<lists.size();i++)
        {
            HashMap<String, ArrayList> hash=new HashMap<String, ArrayList>();
            hash=lists.get(i);
            if(hash.containsKey(SelectedButton))
            {
                values=hash.get(SelectedButton);
            }
        }


        String[] v=new String[values.size()];
        for(int i=0;i<values.size();i++)
        {
            HashMap<String, String> tmpData = (HashMap<String,String>) values.get(i);

            v[i]=tmpData.get(TAG_NAME);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,v);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                HashMap<String, String> tmpData = (HashMap<String,String>) values.get(position);


                AlertDialogueBuilder al=new AlertDialogueBuilder();
                String show="Phone : "+tmpData.get(TAG_PHONE)+"\n"+"Level : "+tmpData.get(TAG_LEVEL);
                al.setTitle("Details...");
                al.DialogueBox(getActivity(),show);


            }
        });
    }
}
