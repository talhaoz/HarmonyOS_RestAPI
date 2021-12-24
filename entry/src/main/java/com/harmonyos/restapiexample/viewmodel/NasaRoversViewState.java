package com.harmonyos.restapiexample.viewmodel;


import com.harmonyos.restapiexample.network.model.ErrorData;
import com.harmonyos.restapiexample.network.model.NasaRoversModel;

public class NasaRoversViewState extends BaseViewState {
    public static class Loading extends NasaRoversViewState {
    }

    public static class Error extends NasaRoversViewState {
        private ErrorData message;

        public Error(ErrorData message) {
            this.message = message;
        }

        public ErrorData getMessage() {
            return message;
        }

        public void setMessage(ErrorData message) {
            this.message = message;
        }
    }

    public static class NasaRovers extends NasaRoversViewState {
        private NasaRoversModel nasaRoversResponse;

        public NasaRovers(NasaRoversModel nasaRoversResponseP) {
            this.nasaRoversResponse = nasaRoversResponseP;
        }

        public NasaRoversModel getNasaRoversResponse() {
            return nasaRoversResponse;
        }
    }
}
