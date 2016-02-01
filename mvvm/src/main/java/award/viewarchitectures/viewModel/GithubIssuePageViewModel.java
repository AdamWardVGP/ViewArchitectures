package award.viewarchitectures.viewModel;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import java.util.List;

import award.viewarchitectures.R;
import award.viewarchitectures.ViewArchitecturesApplication;
import award.viewarchitectures.data.RequestManager;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.util.DataUtils;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by adamward on 2/1/16.
 */
public class GithubIssuePageViewModel {


    private Context mContext;
    private Subscription mSubscription;
    private RequestManager mRequestManager;
    private IssuePageVMListener mListener;

    public ObservableInt mLoadingVisibility;
    public ObservableInt mErrorVisibility;
    public ObservableInt mRecyclerVisibility;

    public GithubIssuePageViewModel(Context context, IssuePageVMListener listener) {
        mContext = context;
        mRequestManager = ViewArchitecturesApplication.getRequestManager(context);
        mLoadingVisibility = new ObservableInt(View.VISIBLE);
        mErrorVisibility = new ObservableInt(View.GONE);
        mRecyclerVisibility = new ObservableInt(View.VISIBLE);

        mListener = listener;
    }

    public void destroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
        mSubscription = null;
        mContext = null;
        mListener = null;
    }

    private void requestIssuesIfOnline() {
        mLoadingVisibility.set(View.VISIBLE);

        Resources res = mContext.getResources();
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
                        mErrorVisibility.set(View.VISIBLE);
                        mRecyclerVisibility.set(View.GONE);
                        hideLoadingViews();
                        Log.e("MVVMGIF", "There was a problem loading the issues list " + e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<GithubIssue> issues) {
                        mListener.onRepositoriesChanged(issues);
                        hideLoadingViews();
                    }
                });
    }

    public void requestIssues() {
        if (DataUtils.isNetworkAvailable(mContext)) {
            showOfflineLayout(false);
            requestIssuesIfOnline();
        } else {
            showOfflineLayout(true);
        }
    }

    public void requestIssues(View view) {
        requestIssues();
    }

    private void hideLoadingViews() {
        mLoadingVisibility.set(View.GONE);
        mListener.onFinishRefreshing();
    }

    private void showOfflineLayout(boolean isOffline) {
        mErrorVisibility.set(isOffline ? View.VISIBLE : View.GONE);
        mRecyclerVisibility.set(isOffline ? View.GONE : View.VISIBLE);
    }

    public interface IssuePageVMListener {
        void onRepositoriesChanged(List<GithubIssue> issues);

        void onFinishRefreshing();
    }

}
