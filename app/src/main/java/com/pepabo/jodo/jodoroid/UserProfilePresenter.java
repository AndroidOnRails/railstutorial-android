package com.pepabo.jodo.jodoroid;

import com.pepabo.jodo.jodoroid.models.APIService;
import com.pepabo.jodo.jodoroid.models.Micropost;
import com.pepabo.jodo.jodoroid.models.User;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class UserProfilePresenter extends RefreshPresenter<User> {
    APIService mAPIService;
    long mUserId;

    public UserProfilePresenter(APIService apiService, ExpirationManager expirationManager, long userId) {
        super(expirationManager);
        mAPIService = apiService;
        mUserId = userId;
    }

    @Override
    protected Observable<User> getObservable(int page) {
        return mAPIService.fetchUser(mUserId, page);
    }

    @Override
    public boolean isLast(User user) {
        return user.getMicroposts() == null || user.getMicroposts().size() == 0;
    }
}
