package com.example.abdullah.personmatcher.Fragment;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.abdullah.personmatcher.Activity.AlertDialogueBuilder;
import com.example.abdullah.personmatcher.Menu.FindList;
import com.example.abdullah.personmatcher.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapShowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapShowFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG_PID = "id";
    private static final String TAG_NAME = "Name";
    private static final String TAG_LOCATION = "Location";
    private static final String TAG_PHONE = "Phone";
    private static final String TAG_LEVEL = "Level";
    private static ArrayList<HashMap<String, ArrayList>> lists = new ArrayList<HashMap<String, ArrayList>>();
    public static String SelectedButton;

    GoogleMap mGoogleMap;
    MapView mMapView;

    View RootView;

    ListView listView ;
    private OnFragmentInteractionListener mListener;

    public MapShowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapShowFragment newInstance(String param1, String param2) {
        MapShowFragment fragment = new MapShowFragment();
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
        RootView=inflater.inflate(R.layout.fragment_map_show, container, false);

        // Inflate the layout for this fragment
        return RootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);

        mMapView=(MapView)RootView.findViewById(R.id.mapView);
        if(mMapView!=null)
        {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

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



    ArrayList<HashMap<String, String>> values=new ArrayList<>();
    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        mGoogleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //googleMap.setMyLocationEnabled(true);
        CameraPosition Liberty=null;

        SelectedButton=MapFragment.SelectedButton;

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

        for(int i=0;i<values.size();i++)
        {
            HashMap<String, String> tmpData = (HashMap<String,String>) values.get(i);

            String location=tmpData.get(TAG_LOCATION);
            String name=tmpData.get(TAG_NAME);
            String phn=tmpData.get(TAG_PHONE);
            Double latitude,longitude;
            StringTokenizer tokenizer = new StringTokenizer(location);

            latitude=Double.parseDouble(tokenizer.nextToken());
            longitude=Double.parseDouble(tokenizer.nextToken());


            googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title(name).snippet(phn));
            if(i==0)
            {
                 Liberty= CameraPosition.builder().target(new LatLng(latitude,longitude)).zoom(16).bearing(0).tilt(45).build();

            }

        }


        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
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


    public void loadList()
    {


    }
}