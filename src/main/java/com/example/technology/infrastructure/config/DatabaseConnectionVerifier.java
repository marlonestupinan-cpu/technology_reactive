package com.example.technology.infrastructure.config;

import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DatabaseConnectionVerifier implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConnectionVerifier.class);
    private final ConnectionFactory connectionFactory;

    public DatabaseConnectionVerifier(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void run(String... args) {
        Mono.from(connectionFactory.create())
                .flatMap(connection -> Mono.from(connection.close()))
                .doOnSuccess(v -> log.info("Conexión con la base de datos establecida y cerrada exitosamente."))
                .doOnError(error -> log.error("Error al conectar con la base de datos: {}", error.getMessage()))
                .block();
    }
}