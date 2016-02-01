package award.viewarchitectures.fragments;

import android.support.v4.app.Fragment;

import com.squareup.leakcanary.RefWatcher;

import award.viewarchitectures.ViewArchitecturesApplication;

/**
 * all fragments in the app should extend this fragment to allow tracking of memory leaks
 */
public class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ViewArchitecturesApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
