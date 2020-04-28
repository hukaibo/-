package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.owasp.encoder.Encode;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {
    private static final String DEFAULT_PATH_SEPARATOR = "/";

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (!filterChainManager.hasChains()) {
            return null;
        }

        String currentPath = getPathWithinApplication(request);
        log.debug("currentPath in CustomPathMatchingFilterChainResolver:{}", currentPath);
        System.out.println("当前路径"+currentPath);

        // in spring web, the requestURI "/resource/menus" ---- "resource/menus/" bose can access the resource
        // but the pathPattern match "/resource/menus" can not match "resource/menus/"
        // user can use requestURI + "/" to simply bypassed chain filter, to bypassed shiro protect
        if (currentPath != null && !DEFAULT_PATH_SEPARATOR.equals(currentPath)
                && currentPath.endsWith(DEFAULT_PATH_SEPARATOR)) {
            currentPath = currentPath.substring(0, currentPath.length() - 1);
        }


        //the 'chain names' in this implementation are actually path patterns defined by the user.  We just use them
        //as the chain name for the FilterChainManager's requirements
        for (String pathPattern : filterChainManager.getChainNames()) {
            System.out.println("路径模式"+pathPattern);

            if (pathPattern != null && !DEFAULT_PATH_SEPARATOR.equals(pathPattern)
                    && pathPattern.endsWith(DEFAULT_PATH_SEPARATOR)) {
                pathPattern = pathPattern.substring(0, pathPattern.length() - 1);
            }

            // If the path does match, then pass on to the subclass implementation for specific checks:
            if (isHttpRequestMatched(pathPattern,currentPath,request)) {

                return filterChainManager.proxy(originalChain, pathPattern);
            }
        }

        return null;
    }

    /**
     * Compare whether the http request matched.
     * 1.compare request url
     * 2.if has http method mark,compare http method.
     * @param chain the specific chain.
     * @param currentPath the current path当前路径
     * @param request the http request.
     * @return the flag indicates whether http request matched.
     */
    private boolean isHttpRequestMatched(String chain,String currentPath, ServletRequest request) {
        String[] array = chain.split("::");
        String url = array[0];
        boolean isHttpMethodMatched = true;
        if (array.length > 1) {
            String methodInRequest = ((HttpServletRequest) request).getMethod().toUpperCase();
            String method = array[1];
            isHttpMethodMatched =method.equals(methodInRequest);
        }
        return pathMatches(url, currentPath)&&isHttpMethodMatched;

    }
}
