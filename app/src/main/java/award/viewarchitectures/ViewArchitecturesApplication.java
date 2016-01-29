package award.viewarchitectures;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import award.viewarchitectures.data.RequestManager;
import award.viewarchitectures.data.RetrofitHelper;
import rx.schedulers.Schedulers;


public class ViewArchitecturesApplication extends Application {

    private RefWatcher mRefWatcher;
    private RequestManager mRequestManager;

    @Override public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
        mRequestManager = new RequestManager(RetrofitHelper.CreateGithubApi(), Schedulers.io());
    }

    public static ViewArchitecturesApplication get(Context context) {
        return (ViewArchitecturesApplication) context.getApplicationContext();
    }

    public static RequestManager getRequestManager(Context context) {
        return ViewArchitecturesApplication.get(context).mRequestManager;
    }

    public static RefWatcher getRefWatcher(Context context) {
        return ViewArchitecturesApplication.get(context).mRefWatcher;
    }

}
