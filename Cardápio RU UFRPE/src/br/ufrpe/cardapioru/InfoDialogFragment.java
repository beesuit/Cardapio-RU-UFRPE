
package br.ufrpe.cardapioru;

import com.example.cardapio.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class InfoDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.info_dialog, null);
        
        return new AlertDialog.Builder(getActivity())
        .setView(v)
        .setTitle(R.string.info)
        .create();
    }

}
