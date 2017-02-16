package br.com.lodjinha.alodjinha.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class UtilJson {


    public static String toString(InputStream is) throws Exception{

        BufferedInputStream in = new BufferedInputStream(is);
        InputStreamReader r = new InputStreamReader(in, "UTF-8");
        StringWriter w = new StringWriter();
        int v = r.read();
        while (v != -1) {
            w.write(v);
            v = r.read();
        }

        return w.toString();

    }

}
