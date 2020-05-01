package com.example.travelguide;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectPackageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectPackageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectPackageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectPackageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectPackageFragment newInstance(String param1, String param2) {
        SelectPackageFragment fragment = new SelectPackageFragment();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_select_package, container, false);

        final String destination = getArguments().getString("destination");
        final String start = getArguments().getString("start");
        final String end = getArguments().getString("end");

        TextView mDestination = root.findViewById(R.id.destination);
        TextView mStartDate = root.findViewById(R.id.startDate);
        TextView mEndDate = root.findViewById(R.id.endDate);

        mDestination.setText(destination);
        mStartDate.setText(start);
        mEndDate.setText(end);

        final CardView silverPackage = root.findViewById(R.id.silver_package);
        final CardView goldPackage = root.findViewById(R.id.gold_package);
        final CardView platinumPackage = root.findViewById(R.id.platinum_package);

        silverPackage.setElevation(dipToPixels(getActivity(), 5));
        goldPackage.setElevation(dipToPixels(getActivity(), 5));
        platinumPackage.setElevation(dipToPixels(getActivity(), 5));

        silverPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silverPackage.setElevation(dipToPixels(getActivity(), 40));
                goldPackage.setElevation(dipToPixels(getActivity(),5));
                platinumPackage.setElevation(dipToPixels(getActivity(), 5));
            }
        });

        goldPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silverPackage.setElevation(dipToPixels(getActivity(), 05));
                goldPackage.setElevation(dipToPixels(getActivity(),40));
                platinumPackage.setElevation(dipToPixels(getActivity(), 05));
            }
        });

        platinumPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silverPackage.setElevation(dipToPixels(getActivity(), 05));
                goldPackage.setElevation(dipToPixels(getActivity(),05));
                platinumPackage.setElevation(dipToPixels(getActivity(), 40));

            }
        });

        Button confirmation = root.findViewById(R.id.book_now);
        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arr[] = new String[]{"Silver","Gold","Platinum"};
                int index = 0;
                if(silverPackage.getElevation()>goldPackage.getElevation() && silverPackage.getElevation()>platinumPackage.getElevation()){
                    index = 0;
                }
                else if(goldPackage.getElevation()>silverPackage.getElevation() && goldPackage.getElevation()>platinumPackage.getElevation()){
                    index = 1;
                }
                else
                    index = 2;
                Bundle args = new Bundle();
                args.putString("destination", destination);
                args.putString("start",start);
                args.putString("end", end);
                args.putString("package",arr[index]);
                Navigation.findNavController(v).navigate(R.id.action_SelectPacakgeFragment_to_Confirmation_Fragment, args);
            }
        });

        return root;
    }

    public static float dipToPixels(Context context, float dipValue){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,  dipValue, metrics);
    }


}
