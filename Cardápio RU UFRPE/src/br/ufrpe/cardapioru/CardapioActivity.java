
package br.ufrpe.cardapioru;



import br.ufrpe.cardapioru.model.Cardapio;
import br.ufrpe.cardapioru.task.CardapioAsyncTask;

import com.example.cardapio.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class CardapioActivity extends ActionBarActivity {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Cardapio.get(this).loadFromJSON();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab = actionBar.newTab()
                .setText(R.string.lunch)
                .setTabListener(new TabListener() {

                    @Override
                    public void onTabSelected(Tab tab,
                            FragmentTransaction ft) {
                        mCurrentFragment = PagerFragment.newInstance(PagerFragment.ALMOCO_FLAG);
                        ft.add(android.R.id.content, mCurrentFragment);

                    }

                    @Override
                    public void onTabUnselected(Tab tab,
                            FragmentTransaction ft) {
                        if (mCurrentFragment != null) {
                            ft.detach(mCurrentFragment);
                        }

                    }

                    @Override
                    public void onTabReselected(Tab tab,
                            FragmentTransaction ft) {

                    }
                });

        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText(R.string.dinner)
                .setTabListener(new TabListener() {

                    @Override
                    public void onTabSelected(Tab tab,
                            FragmentTransaction ft) {
                        mCurrentFragment = PagerFragment.newInstance(PagerFragment.JANTAR_FLAG);
                        ft.add(android.R.id.content, mCurrentFragment);

                    }

                    @Override
                    public void onTabUnselected(Tab tab,
                            FragmentTransaction ft) {
                        if (mCurrentFragment != null) {
                            ft.detach(mCurrentFragment);
                        }
                    }

                    @Override
                    public void onTabReselected(Tab tab,
                            FragmentTransaction ft) {

                    }
                });

        actionBar.addTab(tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cardapio_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.update_item) {
            update();
            return true;
        }
        else if (id == R.id.info_item) {
            info();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void update() {
        CardapioAsyncTask.newInstance(this).execute();
    }

    public void refreshView() {
        ((PagerFragment) mCurrentFragment).update();
    }

    public void info() {
        FragmentManager fm = getSupportFragmentManager();
        new InfoDialogFragment().show(fm, "infoDialog");
    }

}
