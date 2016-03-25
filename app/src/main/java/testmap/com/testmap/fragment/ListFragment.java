package testmap.com.testmap.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import testmap.com.testmap.MainActivity;
import testmap.com.testmap.R;

@EFragment(R.layout.f_list)
public class ListFragment extends Fragment {

    MainActivity activity;

    @ViewById(R.id.rvMapPointList)
    RecyclerView rvMapPointList;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        this.activity = (MainActivity) getActivity();
        final LinearLayoutManager layoutManager =
                new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        rvMapPointList.setLayoutManager(layoutManager);
        rvMapPointList.setAdapter(activity.mapPointRecyclerVIewAdapter);
        super.onActivityCreated(savedInstanceState);
    }

}
