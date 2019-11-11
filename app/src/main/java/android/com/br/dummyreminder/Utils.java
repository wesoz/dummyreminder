package android.com.br.dummyreminder;

import java.time.ZonedDateTime;
import java.util.Date;

public class Utils {
    public static Date now() {
        return Date.from(ZonedDateTime.now().toInstant());
    }
}
