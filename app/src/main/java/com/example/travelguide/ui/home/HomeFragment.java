package com.example.travelguide.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.example.travelguide.BookTripFragment;
import com.example.travelguide.R;
import com.example.travelguide.SlidingImageAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager slider = root.findViewById(R.id.viewPager);
        DotsIndicator dotsIndicator = root.findViewById(R.id.dotsIndicator);
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.sliding1);
        images.add(R.drawable.sliding2);
        images.add(R.drawable.sliding3);
        images.add(R.drawable.sliding4);
        SlidingImageAdapter adapter = new SlidingImageAdapter(getActivity(), images);
        slider.setAdapter(adapter);
        dotsIndicator.setViewPager(slider);

        Button bookNow = root.findViewById(R.id.book_now);
        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                v.startAnimation(animation);
                Bundle args = new Bundle();
                args.putString("message", "This message is passed from the home fragment.");
                Navigation.findNavController(v).navigate(R.id.action_HomeFragment_to_BookTripFragment, args);
            }
        });

        return root;
    }
}
