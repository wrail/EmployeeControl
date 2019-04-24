package com.wrial.demo.compenet;

import com.sun.corba.se.spi.orbutil.closure.Closure;
import com.sun.corba.se.spi.resolver.LocalResolver;
import org.apache.tomcat.jni.Local;
import org.omg.CORBA.Object;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Set;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //如果由自定义的就使用自定义的，否则使用默认的
        Locale locale = Locale.getDefault();
        String l = request.getParameter("l");
        if (!StringUtils.isEmpty(l)){
            String[] s = l.split("_");//语言代码和国家代码
            locale = new Locale(s[0],s[1]);
        }
        return locale;

    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
