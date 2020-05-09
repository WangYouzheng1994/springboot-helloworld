package com.wyz.springboothelloworld.util.convert;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class ConventerFacade {
    // private BigDecimalUtil bigDecimalUtil;
    // private StringUtils
    public String getString(Object str) {
        String result = "";
        result = (str == null ? "" : String.valueOf(str));
        return result;
    }
    void getInteger() {

    }
    void getBigDecimal() {

    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("2.5500010");
        System.out.println(bigDecimal.stripTrailingZeros());
    }
}
