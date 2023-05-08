package ru.kata.spring.boot_security.demo.configs;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Component
public class StaticResourceResolver implements ResourceResolver {

    private final Resource index = new ClassPathResource("/static/index.html");

    @Override
    public Resource resolveResource(HttpServletRequest request, String requestPath, List<? extends Resource> locations,
                                    ResourceResolverChain chain) {

        return resolve(requestPath, locations);
    }

    @Override
    public String resolveUrlPath(String resourcePath, List<? extends Resource> locations, ResourceResolverChain chain) {

        Resource resolvedResource = resolve(resourcePath, locations);
        if (resolvedResource == null) {
            return null;
        }
        try {
            return resolvedResource.getURL().toString();
        }
        catch (IOException e) {
            return resolvedResource.getFilename();
        }
    }

    private Resource resolve(String requestPath, List<? extends Resource> locations) {

        if (requestPath == null) {
            return null;
        }
        for (Resource location : locations) {
            try {
                Resource resource = location.createRelative(requestPath);
                if (resource.exists() && resource.isReadable()) {
                    return resource;
                }
            }
            catch (IOException ignored) {
            }
        }
        return null;
    }
}

