package judh.developer.ottostudy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondFragment extends Fragment {
    @BindView(R.id.second_fragment_text) TextView secondFragmentText;

    public SecondFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }

    // MainActivity에서 기본형 data type으로 보냈지만
    // int가 아닌 Wrapper class인 Integer로 파라미터를 지정하여 참조형 data type입니다.
    // 여기서 Integer를 int로 바꿔서 실행해보면
    // MainActivity에서 보낸 데이터를 받지 못하는 것을 확인할 수 있습니다.
    @Subscribe
    public void getPost(Integer number) {
        Log.d("Second", number + "");
        secondFragmentText.setText(number + "");
    }
}
