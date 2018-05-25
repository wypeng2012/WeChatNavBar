package party.loveit.wechartnavbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import party.loveit.wechartnavbar.fragment.TabConversionFragment;
import party.loveit.wechartnavbar.fragment.TabMarketFragment;
import party.loveit.wechartnavbar.fragment.TabMeFragment;
import party.loveit.wechartnavbar.fragment.TabWalletFragment;
import party.loveit.wechartnavbarlibrary.NavModel;
import party.loveit.wechartnavbarlibrary.WeChatNavBar;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.nav_bar)
    WeChatNavBar mNavBar;


    private TabWalletFragment mWalletFragment;
    private TabMarketFragment mMarketFragment;
    private TabConversionFragment mConversionFragment;
    private TabMeFragment mMeFragment;

    private MainViewPagerAdapter mViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mWalletFragment = new TabWalletFragment();
        mMarketFragment = new TabMarketFragment();
        mConversionFragment = new TabConversionFragment();
        mMeFragment = new TabMeFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mWalletFragment);
        fragments.add(mMarketFragment);
        fragments.add(mConversionFragment);
        fragments.add(mMeFragment);



        mViewPager.setOffscreenPageLimit(3);
        mViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mViewPagerAdapter);

        List<NavModel> models = new ArrayList<>();
        models.add(new NavModel.Builder().iconNormalRes(R.drawable.tabwallet_normal)
                                         .iconSelectRes(R.drawable.tabwallet_pressed)
                                         .textValue("wallet")
                                         .bulid());
        models.add(new NavModel.Builder().iconNormalRes(R.drawable.tabexchange_normal)
                                         .iconSelectRes(R.drawable.tabexchange_pressed)
                                         .textValue("market")
                                         .bulid());
        models.add(new NavModel.Builder().iconNormalRes(R.drawable.tabconversion_normal)
                                         .iconSelectRes(R.drawable.tabconversion_pressed)
                                         .textValue("conversion")
                                         .bulid());
        models.add(new NavModel.Builder().iconNormalRes(R.drawable.tabmy_normal)
                                         .iconSelectRes(R.drawable.tabmy_pressed)
                                         .textValue("me")
                                         .bulid());
        mNavBar.setModels(models);
        mNavBar.setViewPager(mViewPager);

    }

}
