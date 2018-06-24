package sembarang.maenrx;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * @author hendrawd on 24/06/18
 */
public class RecommendedAndroidWayActivity extends BaseActivity {

    private GetStringsTask getStringsTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getStringsTask = new GetStringsTask(this);
        getStringsTask.execute();
    }

    @Override
    protected void onDestroy() {
        getStringsTask.cancel(true);
        super.onDestroy();
    }

    private static class GetStringsTask extends AsyncTask<Void, String[], String[]> {

        // harus membuat Task ini sebagai static dan weak reference untuk menghindari activity leak
        private WeakReference<RecommendedAndroidWayActivity> androidNativeWayActivityWeakReference;

        private GetStringsTask(RecommendedAndroidWayActivity recommendedAndroidWayActivity) {
            androidNativeWayActivityWeakReference = new WeakReference<>(recommendedAndroidWayActivity);
        }

        @Override
        protected String[] doInBackground(Void... voids) {
            return new String[]{"one", "two", "three", "four", "five"};
        }

        @Override
        protected void onPostExecute(String[] strings) {
            RecommendedAndroidWayActivity recommendedAndroidWayActivity =
                    androidNativeWayActivityWeakReference.get();
            if (recommendedAndroidWayActivity != null) {
                for (String string : strings) {
                    recommendedAndroidWayActivity.textView.append(String.format("\n%s", string));
                }
            }
        }
    }
}
