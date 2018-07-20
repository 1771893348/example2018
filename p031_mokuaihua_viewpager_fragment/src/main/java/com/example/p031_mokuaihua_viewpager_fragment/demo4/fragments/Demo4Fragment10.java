package com.example.p031_mokuaihua_viewpager_fragment.demo4.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.base.BaseIndexNetFragment;
import com.example.p031_mokuaihua_viewpager_fragment.demo4.Demo4Activity;

/**
 * Created by shining on 2017/8/14.
 */

public class Demo4Fragment10 extends BaseIndexNetFragment {

    private TextView tv1;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
    }

    @Override
    public void call(Object value) {
        String ids = (String) value;
        tv1.setText(ids);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo4_fragment10;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        tv1= (TextView) rootView.findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendToFragment("demo4的fragment1页面");
                ((Demo4Activity) mContext).changeView(1);
            }
        });
    }

    /**
     * 页面传值操作部分
     *
     * @param id1
     */
    private void SendToFragment(String id1) {
        //举例
//        IndexFoodFragmentUpdateIds iff = new IndexFoodFragmentUpdateIds();
//        iff.setFood_definition_id(id1);
//        iff.setFood_name(id2);
        if (getActivity() != null && getActivity() instanceof Demo4Activity) {
            ((Demo4Activity) getActivity()).callFragment(id1, Demo4Fragment20.class.getName());
        }
    }
}
