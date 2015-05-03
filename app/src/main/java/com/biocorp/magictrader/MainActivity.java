package com.biocorp.magictrader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity implements MVPAbstractView {

    MainPresenter presenter;

    @InjectView(R.id.tvCardName)TextView tvCardName;
    @InjectView(R.id.tvManaCost)TextView tvCMC;
    @InjectView(R.id.tvCardText)TextView tvCardText;
    @InjectView(R.id.tvFlavorText)TextView tvFlavorText;
    @InjectView(R.id.tvPowerToughness)TextView tvPowerToughness;
    @InjectView(R.id.etCardName)EditText etCardName;
    @InjectView(R.id.btnFindCard)Button btnFindCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        presenter = new MainPresenter(this);
    }

    @OnClick(R.id.btnFindCard)
    public void findCard() {
        if (etCardName != null && !etCardName.getText().toString().equals("")) {
            if (presenter != null) {
                presenter.getData(etCardName.getText().toString());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setData(Card data) {
        tvCardName.setText(data.getName());
        tvCMC.setText(data.getMana_cost());
        tvCardText.setText(data.getCard_text());
        tvFlavorText.setText(data.getFlavor_text());
        tvPowerToughness.setText(data.getPower_toughness());
    }

    @Override
    public void showLoadingImage() {
        tvCardName.setText("loading card....");
    }
}
