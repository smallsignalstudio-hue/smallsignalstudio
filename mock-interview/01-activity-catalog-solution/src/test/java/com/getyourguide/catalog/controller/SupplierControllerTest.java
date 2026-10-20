package com.getyourguide.catalog.controller;

import com.getyourguide.catalog.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class SupplierControllerTest {

    @Autowired
    private SupplierController supplierController;

    @Test
    void contextLoads() {
        assertThat(supplierController).isNotNull();
    }
}
