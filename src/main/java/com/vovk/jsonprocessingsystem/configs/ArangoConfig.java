package com.vovk.jsonprocessingsystem.configs;

import org.springframework.context.annotation.Configuration;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;

/**
 * @author Dmytro Vovk
 */

@Configuration
@EnableArangoRepositories(basePackages = { "com.vovk.jsonprocessingsystem" })
public class ArangoConfig extends AbstractArangoConfiguration {
    @Override
    public Builder arango() {
        return new ArangoDB.Builder().host("localhost", 8529).user("root").password("root");
    }

    @Override
    public String database() {
        return "jsonfiles";
    }
}
