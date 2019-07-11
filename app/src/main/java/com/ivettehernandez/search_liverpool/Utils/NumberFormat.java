package com.ivettehernandez.search_liverpool.Utils;

import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by ivettelovegood on 2019-07-10.
 */

public class NumberFormat {

    public String formatNumberMoney(String formatInitial){
        String formatFinal;

        Double formatInitialDouble = Double.parseDouble(formatInitial);

        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        formatFinal = formatter.format(formatInitialDouble);
        return String.valueOf(formatFinal);
    }
}
