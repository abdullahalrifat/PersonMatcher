package com.example.abdullah.personmatcher.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.abdullah.personmatcher.DataBase.ReadMenu;
import com.example.abdullah.personmatcher.Menu.FindMenu;
import com.example.abdullah.personmatcher.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CatagoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CatagoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatagoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    LinearLayout linearLayout;
    public static String SelectedButton=null;
    private OnFragmentInteractionListener mListener;

    public CatagoryFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatagoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatagoryFragment newInstance(String param1, String param2) {
        CatagoryFragment fragment = new CatagoryFragment();
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
    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {

                SelectedButton = button.getText().toString().trim();
                // update the main content by replacing fragments
                Fragment fragment;
                fragment=new ListFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
            }
        };
    }

    public void onStart()
    {
        super.onStart();
        load();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_catagory, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
       // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);

        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);


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


    //my working area starts
    public static ArrayList menuItems = new ArrayList<>();
    private static final String TAG_PID = "id";
    private static final String TAG_NAME = "Name";
    public void load()
    {
        GetMenus();
    }
    public void GetMenus()
    {

                FindMenu find=new FindMenu();
                menuItems=find.getResults();

                for(int i=0;i<menuItems.size();i++)
                {
                    HashMap<String, String> tmpData = (HashMap<String,String>) menuItems.get(i);
                    String Name=tmpData.get(TAG_NAME);
                    Button btn = new Button(getActivity());
                    btn.setText(Name);
                    btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    btn.setOnClickListener(getOnClickDoSomething(btn));
                    linearLayout.addView(btn);
                }
    }




}
