package freitas.dias.lodjinha.controller;


import android.util.Log;

import java.util.Date;

import freitas.dias.lodjinha.api.Api;
import freitas.dias.lodjinha.util.DateUtils;
import freitas.dias.lodjinha.util.EventBusUtil;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class BaseController {

    private static final String BASE_URL = "https://alodjinha.herokuapp.com/";
    private static Retrofit retrofit = null;
    private static final String LOG = BaseController.class.getSimpleName();
    private static final String LOG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSZ";

    private Date dateStart;
    private Date dateEnd;

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static Api apiService(){
        return getClient().create(Api.class);
    }

    protected void post(Object post) {

        dateEnd = new Date(System.currentTimeMillis());
        String formattedDate = DateUtils.formatDate(LOG_DATE_FORMAT, dateEnd);
        Log.d(LOG, "API - Classe Resposta = " + post.getClass().getSimpleName());
        printDifference(dateStart, dateEnd);
        Log.d(LOG, "API - FIM = " + formattedDate);
        EventBusUtil.INSTANCE.post(post);
    }

    private void printDifference(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return;
        }

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        Log.d(LOG, "API - Duracao = " +
                elapsedDays + " days " +
                elapsedHours + " hours " +
                elapsedMinutes + " minutes " +
                elapsedSeconds + " seconds " +
                different + " milliseconds ");
    }
}
