package com.clement.persistance;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static jakarta.persistence.spi.PersistenceUnitTransactionType.RESOURCE_LOCAL;

public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {
    @Override
    public String getPersistenceUnitName() {
        return "my-persistence";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public String getScopeAnnotationName() {
        return null;
    }

    @Override
    public List<String> getQualifierAnnotationNames() {
        return null;
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("admin");
        dataSource.setPassword("password");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5431/hibernate");
        return dataSource;

    }

    @Override
    public DataSource getNonJtaDataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setUsername("admin");
//        dataSource.setPassword("password");
//        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5431/hibernate");
//        return dataSource;
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        List<String> l = new ArrayList<>();
//        l.add("com.clement.entity.Product");
//        l.add("com.clement.entity.oneToOne.Country");
//        l.add("com.clement.entity.oneToOne.CapitalCity");
        l.add("com.clement.entity.oneToMany.Post");
        l.add("com.clement.entity.oneToMany.Comment");
//        l.add("com.clement.entity.inheritance.Book");
//        l.add("com.clement.entity.inheritance.Product");
//        l.add("com.clement.entity.inheritance.Electronic");

        return l;
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer classTransformer) {
    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
