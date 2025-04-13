package com.github.matielojg.salesorder.core.gateway;

public interface LoggerGateway {
    void debug(String message);
    void debug(String message, Object... args);
    void info(String message);
    void error(String message, Throwable throwable);
}
