
package br.ufrpe.cardapioru;

import java.util.ArrayList;

import br.ufrpe.cardapioru.model.Cardapio;

import com.example.cardapio.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerFragment extends Fragment {
    public static final int ALMOCO_FLAG = 0;
    public static final int JANTAR_FLAG = 1;

    private static final String FLAG = "flag";

    private ArrayList<String> mDiasSemana;
    private int mFlag;
    private ViewPager mPager;

    public static PagerFragment newInstance(int flag) {
        Bundle args = new Bundle();
        args.putInt(FLAG, flag);

        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDiasSemana = new ArrayList<String>();
        mDiasSemana.add(getResources().getString(R.string.monday));
        mDiasSemana.add(getResources().getString(R.string.tuesday));
        mDiasSemana.add(getResources().getString(R.string.wednesday));
        mDiasSemana.add(getResources().getString(R.string.thursday));
        mDiasSemana.add(getResources().getString(R.string.friday));

        mFlag = getArguments().getInt(FLAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager,
                parent, false);
        mPager = (ViewPager) v.findViewById(R.id.pager);
        ViewPager pager = (ViewPager) v.findViewById(R.id.pager);

        FragmentManager fm = this.getChildFragmentManager();
        pager.setAdapter(new FragmentStatePagerAdapter(fm) {

            @Override
            public Fragment getItem(int pos) {
                return PratosListFragment.newInstance(mFlag, pos);
            }

            @Override
            public int getCount() {
                return Cardapio.get(getActivity()).getDias().size();
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public CharSequence getPageTitle(int pos) {
                return mDiasSemana.get(pos);
            }

        });

        return v;
    }

    public void update() {
        mPager.getAdapter().notifyDataSetChanged();
    }

}
