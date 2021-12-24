package com.harmonyos.restapiexample.viewmodel;

import com.harmonyos.restapiexample.MyApplication;
import com.harmonyos.restapiexample.network.model.ErrorData;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ohos.aafwk.abilityjet.activedata.ActiveData;

import static com.harmonyos.restapiexample.Constants.API_KEY;

public class NasaRoversViewModel extends BaseViewModel<NasaRoversViewState> {

    public NasaRoversViewModel() {
        super();
    }

    public void getNasaRovers() {
        super.subscribe(getAllNasaRovers());
    }

    //public ActiveData<Boolean> activeDataExample = new ActiveData<>();

    private Observable<NasaRoversViewState> getAllNasaRovers() {
        return MyApplication.createRetrofitClient()
                .getNasaRovers(API_KEY)
                .doOnError(Throwable::printStackTrace)
                .map(NasaRoversViewState.NasaRovers::new)
                .cast(NasaRoversViewState.class)
                .onErrorReturn(throwable -> {
                    ErrorData errorData = new ErrorData();
                    if (throwable.getMessage() != null)
                        errorData.setMessage(throwable.getMessage());
                    else
                        errorData.setMessage(" No internet! ");
                    return new NasaRoversViewState.Error(errorData);
                })
                .subscribeOn(Schedulers.io())
                .startWith(new NasaRoversViewState.Loading());
    }
}
