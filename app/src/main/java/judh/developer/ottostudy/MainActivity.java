package judh.developer.ottostudy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;

    FragmentAdapter fragmentAdapter;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        number = 0;

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class FragmentAdapter extends FragmentPagerAdapter {
        Fragment[] frags = new Fragment[]{
                new FirstFragment(),
                new SecondFragment()
        };
        String[] titles = new String[]{
                "First", "Second"
        };

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @OnClick(R.id.otto_btn)
    public void ottoBtn() {
        // post로 던지는 parameter는 기본적으로는 어떤 것이든 가능합니다.
        // 다만, 받는 쪽에서는 기본형 data type이 아닌 참조형 data type으로 받아야합니다.
        // 여기서는 int형으로 기본형 data type을 파라미터로 보냈습니다.
        BusProvider.getInstance().post(++number);
        Log.d("Otto", number + "");
    }
}
