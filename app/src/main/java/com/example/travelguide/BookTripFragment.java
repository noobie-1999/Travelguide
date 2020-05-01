package com.example.travelguide;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.savvi.rangedatepicker.CalendarPickerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookTripFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookTripFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookTripFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookTripFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookTripFragment newInstance(String param1, String param2) {
        BookTripFragment fragment = new BookTripFragment();
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
        final View root =  inflater.inflate(R.layout.fragment_book_trip, container, false);

        final AutoCompleteTextView destination = root.findViewById(R.id.destination);
        String[] dest = new String[]{"Bangalore", "Pondicherry","Ooty","Chennai","Tirupati","Kerala"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, dest);
        destination.setAdapter(adapter);
        destination.setThreshold(1);
        /*Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DATE,100);
        final CalendarPickerView calendar = (CalendarPickerView) root.findViewById(R.id.calendar_view);
        calendar.init(start.getTime(), end.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault())).inMode(CalendarPickerView.SelectionMode.RANGE);
        calendar.setTypeface(Typeface.SANS_SERIF);
*/
        final TextView startDate = root.findViewById(R.id.startDate);
        final TextView endDate = root.findViewById(R.id.endDate);
        Button confirm = root.findViewById(R.id.confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start = startDate.getText().toString();
                String end = endDate.getText().toString();
                String dest = destination.getText().toString();
                Bundle args = new Bundle();
                args.putString("start", start);
                args.putString("end", end);
                args.putString("destination", dest);
                Navigation.findNavController(v).navigate(R.id.action_BookTripFragment_to_GalleryFragment, args);
            }
        });

        final Button selectDate = root.findViewById(R.id.selectDates);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.calendar_popup, null);
                final PopupWindow window = new PopupWindow(layout, 1000, 1400, true);

                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setOutsideTouchable(true);
                window.showAtLocation(layout, Gravity.CENTER, 0,0);
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                end.add(Calendar.DATE,100);
                final CalendarPickerView calendar = (CalendarPickerView) layout.findViewById(R.id.calendar_view);
                calendar.init(start.getTime(), end.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault())).inMode(CalendarPickerView.SelectionMode.RANGE);
                calendar.setTypeface(Typeface.SANS_SERIF);
                final Button confirm = layout.findViewById(R.id.confirm_button);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Date> selectedDate = calendar.getSelectedDates();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                        String start = sdf.format(selectedDate.get(0));
                        String end = sdf.format(selectedDate.get(selectedDate.size()-1));
                        startDate.setText(start);
                        endDate.setText(end);
                        window.dismiss();
                    }
                });
                final Button cancel = layout.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });
            }
        });

        return root;
    }
}
