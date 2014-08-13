
package br.ufrpe.cardapioru.task;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;
import br.ufrpe.cardapioru.CardapioActivity;
import br.ufrpe.cardapioru.model.Cardapio;

import com.example.cardapio.R;

public class CardapioAsyncTask extends AsyncTask<Void, Void, Void> {

    private Context mContext;
    private static CardapioAsyncTask sTask;

    private ProgressDialog mUpdating;
    private ProgressDialog mCancelling;
    private String mJson;
    private Exception mException;

    public CardapioAsyncTask(Context context) {
        mContext = context;
        mUpdating = new ProgressDialog(mContext);
        mCancelling = new ProgressDialog(mContext);
    }

    public static CardapioAsyncTask newInstance(Context context) {
        sTask = new CardapioAsyncTask(context);
        return sTask;
    }

    @Override
    protected void onPreExecute() {
        mUpdating.setMessage(mContext.getResources().getString(R.string.updating));
        mUpdating.setCancelable(false);
        mUpdating.setButton(DialogInterface.BUTTON_NEGATIVE,
                mContext.getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sTask.cancel(true);
                        mCancelling.setMessage(mContext.getResources().getString(
                                R.string.cancelling));
                        mCancelling.setCancelable(false);
                        mCancelling.show();

                    }
                });
        mUpdating.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            HttpClient hc = new DefaultHttpClient();
            String URL = "https://script.google.com/macros/s/AKfycbzrQ9vx_alQ5yEvSFx4uMOURVNeJPKimn30UTp0PNYWIPA6_mQ/exec";
            HttpGet get = new HttpGet(URL);
            HttpResponse response = hc.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                mJson = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            mException = e;
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onCancelled() {
        if (mUpdating.isShowing() || mCancelling.isShowing()) {
            mUpdating.dismiss();
            mCancelling.dismiss();
        }
        Toast.makeText(mContext, R.string.cancelled, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mException != null) {
            if (mUpdating.isShowing()) {
                mUpdating.dismiss();
            }
            Toast.makeText(mContext, R.string.no_connection, Toast.LENGTH_SHORT).show();

        } else {
            Cardapio.get(mContext).writeToFile(mJson);
            Cardapio.get(mContext).loadFromJSON();

            if (mUpdating.isShowing()) {
                mUpdating.dismiss();
            }
            Toast.makeText(mContext, R.string.updated, Toast.LENGTH_SHORT).show();
            ((CardapioActivity) mContext).refreshView();
        }

    }

}
