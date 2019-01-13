package b2w.com.br.olodjinha.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    public static String formatBigDecimalToCurrency(BigDecimal value) {
        value.setScale(2, RoundingMode.HALF_UP);
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(value.doubleValue());
    }
}
