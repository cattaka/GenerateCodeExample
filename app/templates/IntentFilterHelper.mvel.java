package net.cattaka.generatecodeexample.generated;

import android.net.Uri;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cattaka on 16/05/22.
 */
public enum IntentFilterHelper {
    @foreach{entry:intentFilters}
    @{entry.name}("@{entry.scheme}", "@{entry.host}", "@{entry.pathPattern}", @{entry.idIndex}),@comment{
    }@end{}
    ;
    private final String scheme;
    private final String host;
    private final String pathPattern;
    private final int idIndex;

    IntentFilterHelper(String scheme, String host, String pathPattern, int idIndex) {
        this.scheme = scheme;
        this.host = host;
        this.pathPattern = pathPattern;
        this.idIndex = idIndex;
    }

    public static IntentFilterHelper findHelper(Uri uri) {
        for (IntentFilterHelper helper:values()) {
            if (helper.matches(uri)) {
                return helper;
            }
        }
        return null;
    }

    public boolean matches(Uri uri) {
        return uri != null
                && scheme.equals(uri.getScheme())
                && host.equals(uri.getHost())
                && uri.getPath().matches(pathPattern);
    }

    public String pickKey(Uri uri) {
        Matcher matcher = Pattern.compile(pathPattern).matcher(uri.getPath());
        if (matcher.find()) {
            return matcher.group(idIndex);
        } else {
            return null;
        }
    }
}
