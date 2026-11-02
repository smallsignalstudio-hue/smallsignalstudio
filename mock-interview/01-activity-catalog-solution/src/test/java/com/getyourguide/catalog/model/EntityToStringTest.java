package com.getyourguide.catalog.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class EntityToStringTest {

    @Test
    void bidirectionalAssociationDoesNotRecurse() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Berlin Walks GmbH");
        Activity activity = new Activity();
        activity.setId(10L);
        activity.setTitle("Walking tour");
        activity.setSupplier(supplier);
        supplier.setActivities(List.of(activity));

        assertThatCode(supplier::toString).doesNotThrowAnyException();
        assertThatCode(activity::toString).doesNotThrowAnyException();
        assertThatCode(supplier::hashCode).doesNotThrowAnyException();
        assertThat(supplier.toString()).contains("Berlin Walks GmbH");
    }
}
