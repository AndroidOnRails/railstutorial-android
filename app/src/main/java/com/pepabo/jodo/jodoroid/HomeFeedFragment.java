package com.pepabo.jodo.jodoroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.pepabo.jodo.jodoroid.models.APIService;
import com.pepabo.jodo.jodoroid.models.Micropost;

import java.util.List;

public class HomeFeedFragment extends MicropostListFragment
        implements RefreshableView<List<Micropost>> {

    APIService mAPIService;
    HomeFeedPresenter mPresenter;

    public static HomeFeedFragment newInstance() {
        HomeFeedFragment fragment = new HomeFeedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFeedFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mAPIService = ((JodoroidApplication) activity.getApplication()).getAPIService();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter = new HomeFeedPresenter(this, mAPIService);
        mPresenter.refresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();

        mPresenter.refresh();
    }

    @Override
    public void onNextModel(List<Micropost> microposts) {
        setMicroposts(microposts);
    }

    @Override
    public void onLoadError(Throwable e) {
        Toast.makeText(getActivity(),
                getString(R.string.toast_load_failure),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoreModel(List<Micropost> microposts) {
        if (microposts.size() == 0) mPresenter.noMorePagination();
        addMicroposts(microposts);
    }

    @Override
    protected void onLoadNextPage() {
        mPresenter.onLoadNextPage();
    }
}
