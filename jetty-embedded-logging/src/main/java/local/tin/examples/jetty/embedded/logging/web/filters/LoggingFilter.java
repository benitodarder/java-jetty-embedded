package local.tin.examples.jetty.embedded.logging.web.filters;

/**
 *
 * @author benitodarder
 */
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class LoggingFilter implements javax.servlet.Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger("messageLogger");
    private static Marker marker;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            LOGGER.warn("LoggingFilter just supports HTTP requests");
        } else {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            LoggingHttpServletRequestWrapper requestWrapper = new LoggingHttpServletRequestWrapper(httpRequest);
            LoggingHttpServletResponseWrapper responseWrapper = new LoggingHttpServletResponseWrapper(httpResponse);
            String requestBody = requestWrapper.getContent();
            long t0 = System.currentTimeMillis();
            
            try {

                filterChain.doFilter(requestWrapper, responseWrapper);

                LOGGER.info("{} {} - {}{} - {}ms\nRequest body:\n{}\nResponse body:\n{}\n==============================================", requestWrapper.getMethod(), responseWrapper.getStatus(), requestWrapper.getRequestURL().toString(), requestWrapper.getQueryString() != null ? ("?" + requestWrapper.getQueryString()) : "", (System.currentTimeMillis() - t0), requestBody, responseWrapper.getContent());

            } catch (Exception e) {
                LOGGER.error("{} {} - {}{} - {}ms\nRequest body:\n{}\nException message:\n{}\n==============================================", requestWrapper.getMethod(),  responseWrapper.getStatus(), requestWrapper.getRequestURL().toString(), requestWrapper.getQueryString() != null ? ("?" + requestWrapper.getQueryString()) : "", (System.currentTimeMillis() - t0), requestBody, e.getMessage());
            }
            httpResponse.getOutputStream().write(responseWrapper.getContentAsBytes());

        }
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
