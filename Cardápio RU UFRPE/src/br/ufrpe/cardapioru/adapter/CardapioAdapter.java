
package br.ufrpe.cardapioru.adapter;

import java.util.ArrayList;

import com.example.cardapio.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CardapioAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> mNomes;
    private ArrayList<String> mPratos;

    public CardapioAdapter(Context context, ArrayList<String> nomes, ArrayList<String> pratos) {
        super(context, R.layout.pratos_list_item, nomes);
        mContext = context;
        mNomes = nomes;
        mPratos = pratos;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.pratos_list_item, parent, false);
        String prato = mPratos.get(position);
        String nome = mNomes.get(position);

        TextView nomeTextView = (TextView) v.findViewById(R.id.nome);
        TextView pratoTextView = (TextView) v.findViewById(R.id.prato);

        nomeTextView.setText(nome);
        pratoTextView.setText(prato);

        return v;
    }

}
