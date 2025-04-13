package com.github.matielojg.salesorder.adapter.gateway;

import com.github.matielojg.salesorder.core.gateway.LoggerGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Slf4jLoggerGateway implements LoggerGateway {

    private static final Logger log = LoggerFactory.getLogger(Slf4jLoggerGateway.class);

    @Override
    public void debug(String message) {
        log.debug(message);
    }

    @Override
    public void debug(String message, Object... args) {
        log.debug(message, args);
    }

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        log.error(message, throwable);
    }
}
