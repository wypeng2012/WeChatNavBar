package party.loveit.wechartnavbar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by MRKING on 2016/9/23.
 */

public abstract class BaseFragment extends Fragment {
    protected String TAG = BaseFragment.class.getSimpleName();

    private Unbinder mUnbinder;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG = this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(),null);
        mUnbinder = ButterKnife.bind(this,view);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != null)
            mUnbinder.unbind();
    }
    protected abstract int getLayoutId();
    protected abstract void init();
}
