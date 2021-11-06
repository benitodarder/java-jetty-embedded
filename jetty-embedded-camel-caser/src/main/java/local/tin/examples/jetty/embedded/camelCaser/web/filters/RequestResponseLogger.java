package local.tin.examples.jetty.embedded.camelCaser.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author benitodarder
 */
public class RequestResponseLogger implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseLogger.class);
    private final long createdAt;

    public RequestResponseLogger() {
            this.createdAt = System.currentTimeMillis();
    }
    
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("SimpleFilterToo initialized!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("SimpleFilterToo doFilter starts! Created at:  {}", createdAt);
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            LOGGER.warn("LoggingFilter just supports HTTP requests");
        } else {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            SimpleHttpServletRequestWrapper requestWrapper = new SimpleHttpServletRequestWrapper(httpRequest);
            SimpleHttServletResponseWrapper responseWrapper = new SimpleHttServletResponseWrapper(httpResponse);
            String requestBody = requestWrapper.getContent();
            long t0 = System.currentTimeMillis();

            try {

                filterChain.doFilter(requestWrapper, responseWrapper);

                LOGGER.info("\n==============================================\n{} {} - {}{} - {}ms\nRequest body:\n{}\nResponse body:\n{}\n==============================================", requestWrapper.getMethod(), responseWrapper.getStatus(), requestWrapper.getRequestURL().toString(), requestWrapper.getQueryString() != null ? ("?" + requestWrapper.getQueryString()) : "", (System.currentTimeMillis() - t0), requestBody, responseWrapper.getContent());
                httpResponse.getOutputStream().write(responseWrapper.getContentAsBytes());

            } catch (Exception e) {
                LOGGER.error("\n==============================================\n{} {} - {}{} - {}ms\nRequest body:\n{}\nException message:\n{}\n==============================================", requestWrapper.getMethod(), responseWrapper.getStatus(), requestWrapper.getRequestURL().toString(), requestWrapper.getQueryString() != null ? ("?" + requestWrapper.getQueryString()) : "", (System.currentTimeMillis() - t0), requestBody, e.getMessage());
                httpResponse.getOutputStream().write(e.getMessage().getBytes());
            }

        }
    }

    @Override
    public void destroy() {
        LOGGER.info("SimpleFilterToo destroyed!");
    }

}