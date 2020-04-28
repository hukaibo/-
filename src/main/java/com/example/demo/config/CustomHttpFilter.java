package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.owasp.encoder.Encode;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CustomHttpFilter extends PermissionsAuthorizationFilter {
    private static final String DEFAULT_PATH_SEPARATOR = "/";
    @Override
    protected boolean pathsMatch(String path, ServletRequest request) {
        String currentPath = getPathWithinApplication(request);
        System.out.println(path);
        System.out.println(currentPath);
        String[] array = path.split("::");
        String url = array[0];
        boolean isHttpMethodMatched = true;
        if (array.length > 1) {
            String methodInRequest = ((HttpServletRequest) request).getMethod().toUpperCase();
            String method = array[1];
            isHttpMethodMatched =method.equals(methodInRequest);
        }

        if (currentPath != null && !DEFAULT_PATH_SEPARATOR.equals(currentPath)
                && currentPath.endsWith(DEFAULT_PATH_SEPARATOR)) {
            currentPath = currentPath.substring(0, currentPath.length() - 1);
        }
        if (path != null && !DEFAULT_PATH_SEPARATOR.equals(path)
                && path.endsWith(DEFAULT_PATH_SEPARATOR)) {
            path = path.substring(0, path.length() - 1);
        }
        return pathsMatch(url, currentPath)&&isHttpMethodMatched;
    }
}
