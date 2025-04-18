package com.github.matielojg.salesorder.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class TraceIdLoggingFilter extends OncePerRequestFilter {

    private static final String TRACE_ID_KEY = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String traceId = UUID.randomUUID().toString();
            MDC.put(TRACE_ID_KEY, traceId);

            // Você pode também adicionar o traceId como header da response, se quiser
            response.setHeader(TRACE_ID_KEY, traceId);

            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(TRACE_ID_KEY); // evita vazamento entre threads
        }
    }
}
