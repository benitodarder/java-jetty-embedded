package local.tin.examples.jetty.embedded.logging.web.filters;

/**
 *
 * @author benitodarder
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingFilter implements javax.servlet.Filter {

    private static final Logger LOGGER = Logger.getLogger("messageLogger");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            LOGGER.log(Level.WARNING, "LoggingFilter just supports HTTP requests");
        } else {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            LoggingHttpServletRequestWrapper requestWrapper = new LoggingHttpServletRequestWrapper(httpRequest);
            LoggingHttpServletResponseWrapper responseWrapper = new LoggingHttpServletResponseWrapper(httpResponse);
            String requestBody = requestWrapper.getContent();
            long t0 = System.currentTimeMillis();

            try {

                filterChain.doFilter(requestWrapper, responseWrapper);

                LOGGER.log(Level.INFO, "{0} {1} - {2}{3} - {4}ms\nRequest body:\n{5}\nResponse body:\n{6}\n==============================================",  new Object[]{requestWrapper.getMethod(), responseWrapper.getStatus(), requestWrapper.getRequestURL().toString(), requestWrapper.getQueryString() != null ? ("?" + requestWrapper.getQueryString()) : "", (System.currentTimeMillis() - t0), requestBody, responseWrapper.getContent()});

            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                LOGGER.log(Level.SEVERE, "{0} {1} - {2}{3} - {4}ms\nRequest body:\n{5}\nException message:\n{6}\n==============================================", new Object[]{requestWrapper.getMethod(), responseWrapper.getStatus(), requestWrapper.getRequestURL().toString(), requestWrapper.getQueryString() != null ? ("?" + requestWrapper.getQueryString()) : "", (System.currentTimeMillis() - t0), requestBody, sw.toString()});
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
