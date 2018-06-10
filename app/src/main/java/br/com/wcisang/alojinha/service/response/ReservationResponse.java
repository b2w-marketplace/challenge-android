package br.com.wcisang.alojinha.service.response;

/**
 * Created by WCisang on 09/06/2018.
 */
public class ReservationResponse {

    private static final String RESERVATION_SUCCESS = "success";

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return result != null && result.equalsIgnoreCase(RESERVATION_SUCCESS);
    }
}
