package award.viewarchitectures.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import award.viewarchitectures.R;
import award.viewarchitectures.ViewArchitecturesApplication;
import award.viewarchitectures.data.RequestManager;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.presenter.interfaces.IssuePagePresenter;
import award.viewarchitectures.util.DataUtils;
import award.viewarchitectures.views.IssuePageView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class IssuePagePresenterImpl implements IssuePagePresenter {

    private IssuePageView mIssuePageViewInterface;
    private RequestManager mRequestManager;
    private Subscription mSubscription;
    private List<GithubIssue> mIssues;

    public IssuePagePresenterImpl(Context context) {
        mIssues = new ArrayList<>();
        mRequestManager = ViewArchitecturesApplication.getRequestManager(context);
    }

    @Override
    public void attachView(IssuePageView view) {
        this.mIssuePageViewInterface = view;
    }

    @Override
    public void detachView() {
        this.mIssuePageViewInterface = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    private void requestLoadIssues(Context context) {
        Resources res = context.getResources();
        mSubscription = mRequestManager.getIssues(
            res.getString(R.string.repo_owner), res.getString(R.string.repo_name), "")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(mRequestManager.getScheduler())
            .subscribe(new Subscriber<List<GithubIssue>>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    if(mIssuePageViewInterface != null) {
                        mIssuePageViewInterface.hideLoadingViews();
                        Log.e("MVVMGIF", "There was a problem loading the issues list " + e);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNext(List<GithubIssue> issues) {
                    if(mIssuePageViewInterface != null) {
                        mIssuePageViewInterface.hideLoadingViews();
                        mIssuePageViewInterface.showIssues(mIssues);
                    }
                    mIssues.clear();
                    mIssues.addAll(issues);
                }
            });
    }

    @Override
    public void loadIssues(Context context) {
        if (DataUtils.isNetworkAvailable(context)) {
            mIssuePageViewInterface.showOfflineLayout(false);
            requestLoadIssues(context);
        } else {
            mIssuePageViewInterface.showOfflineLayout(true);
        }
    }
}
