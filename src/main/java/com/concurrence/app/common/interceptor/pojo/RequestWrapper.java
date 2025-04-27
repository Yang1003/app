package com.concurrence.app.common.interceptor.pojo;


import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenqw
 * @date 2022/12/20 15:46
 */
@Slf4j
public class RequestWrapper extends HttpServletRequestWrapper {

    private String mBody;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        mBody = getBody(request);
    }

    private String getBody(HttpServletRequest request) {
        try {
            return StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }

    public String getBody() {
        return mBody;
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream bais = new ByteArrayInputStream(mBody.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() {
                return bais.read();
            }

        };
    }
}
