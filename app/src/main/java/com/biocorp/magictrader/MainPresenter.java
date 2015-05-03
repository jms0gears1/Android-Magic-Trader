package com.biocorp.magictrader;

import android.app.Activity;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jms_m_000 on 5/2/2015.
 */
@SuppressWarnings("ALL")
public class MainPresenter implements MVPAbstractPresenter {

    private static final String API_ENDPOINT = "http://scry.me.uk";
    private static final String API_GET_CARD = "/api.php";

    MVPAbstractView activity;

    public MainPresenter(MVPAbstractView activity) {
        this.activity = activity;
    }

    @Override
    public void getData(String name) {
       getCard(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((data) -> activity.setData(data));
        activity.showLoadingImage();
    }

    private Observable<Card> getCard(String name) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(API_ENDPOINT)
                .build();

        if (adapter != null) {
            CardService service = adapter.create(CardService.class);
            return service.getCardInfo(name);
        }

        return null;
    }

    private interface CardService{
        @GET(API_GET_CARD)
        Observable<Card> getCardInfo(@Query("name") String cardName);
    }
}
