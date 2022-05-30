package com.project.springbootecommerce.config;

import com.project.springbootecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {
    @Value("${allowed.origins}")
    private String[] allowedOrigins;
    EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unSupportedActions = {HttpMethod.PUT,HttpMethod.DELETE,HttpMethod.POST,HttpMethod.PATCH};
        disableHttpMethods(Product.class,config, unSupportedActions);
        disableHttpMethods(ProductCategory.class,config, unSupportedActions);
        disableHttpMethods(Country.class,config, unSupportedActions);
        disableHttpMethods(State.class,config, unSupportedActions);
        disableHttpMethods(Order.class,config, unSupportedActions);
        cors.addMapping(config.getBasePath()+"/**").allowedOrigins(allowedOrigins);

        exposeIds(config);
    }

    private void disableHttpMethods(Class theClass,RepositoryRestConfiguration config, HttpMethod[] unSupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unSupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unSupportedActions));
    }

    public void exposeIds(RepositoryRestConfiguration config){
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();
        for(EntityType entityType : entities){
            entityClasses.add(entityType.getJavaType());
        }
        Class[] domainTypes = entityClasses.toArray(entityClasses.toArray(new Class[0]));
        config.exposeIdsFor(domainTypes);
    }
}
