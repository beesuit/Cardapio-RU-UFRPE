
package br.ufrpe.cardapioru;

import java.util.ArrayList;

import br.ufrpe.cardapioru.adapter.CardapioAdapter;
import br.ufrpe.cardapioru.model.Cardapio;


import android.os.Bundle;
import android.support.v4.app.ListFragment;

public class PratosListFragment extends ListFragment {
    private static final String FLAG = "flag";
    private static final String POSITION = "position";
    
    public static final int ALMOCO_FLAG = 0;
    public static final int JANTAR_FLAG = 1;
    
    private Cardapio mSingleton;
    private ArrayList<String> mNomes;
    private ArrayList<String> mPratos;
    
    public static PratosListFragment newInstance(int flag, int pos){
        Bundle args = new Bundle();
        args.putInt(FLAG, flag);
        args.putInt(POSITION, pos);
        
        PratosListFragment fragment = new PratosListFragment();
        fragment.setArguments(args);
        
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mSingleton = Cardapio.get(getActivity());
        
        int flag = getArguments().getInt(FLAG);
        int pos = getArguments().getInt(POSITION);
        
        
        switch(flag){
        case ALMOCO_FLAG:
            mNomes = mSingleton.getAlmocoNomes();
            mPratos = mSingleton.getAlmocoPratos(pos);
            break;
        case JANTAR_FLAG:
            mNomes = mSingleton.getJantarNomes();
            mPratos = mSingleton.getJantarPratos(pos);
            break;
        default:
            mNomes = new ArrayList<String>();
            mPratos = new ArrayList<String>();
        }

        CardapioAdapter adapter = new CardapioAdapter(getActivity(), mNomes, mPratos);

        setListAdapter(adapter);
    }

}
