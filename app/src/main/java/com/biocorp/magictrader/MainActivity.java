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
import icepick.Icepick;
import icepick.Icicle;
import icepick.processor.IcepickProcessor;


public class MainActivity extends Activity implements MVPAbstractView {
    MainPresenter presenter;
    @InjectView(R.id.tvCardName)TextView tvCardName;
    @InjectView(R.id.tvManaCost)TextView tvCMC;
    @InjectView(R.id.tvCardText)TextView tvCardText;
    @InjectView(R.id.tvFlavorText)TextView tvFlavorText;
    @InjectView(R.id.tvPowerToughness)TextView tvPowerToughness;
    @InjectView(R.id.etCardName)EditText etCardName;
    @InjectView(R.id.btnFindCard)Button btnFindCard;

    @Icicle String cardName;
    @Icicle String manaCost;
    @Icicle String cardText;
    @Icicle String flavorText;
    @Icicle String powerToughness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (savedInstanceState != null) {
            this.setViewText();
        }

        presenter = new MainPresenter(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
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
        this.cardName = data.getName();
        this.manaCost = data.getMana_cost();
        this.cardText = data.getCard_text();
        this.flavorText = data.getFlavor_text();
        this.powerToughness = data.getPower_toughness();
        setViewText();
    }

    @Override
    public void showLoadingImage() {
        tvCardName.setText("loading card....");
    }

    public void setViewText(){
        this.tvCardName.setText(cardName==null?"":cardName);
        this.tvCMC.setText(manaCost==null?"":manaCost);
        this.tvCardText.setText(cardText==null?"":cardText);
        this.tvFlavorText.setText(flavorText==null?"":flavorText);
        this.tvPowerToughness.setText(powerToughness==null?"":powerToughness);
    }
}
