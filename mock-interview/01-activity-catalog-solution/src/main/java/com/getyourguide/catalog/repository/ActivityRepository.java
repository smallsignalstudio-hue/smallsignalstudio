package com.getyourguide.catalog.repository;

import com.getyourguide.catalog.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = "select a from Activity a left join fetch a.supplier",
            countQuery = "select count(a) from Activity a")
    Page<Activity> findPageWithSupplier(Pageable pageable);

    @Query("select a from Activity a left join fetch a.supplier where a.id = :id")
    Optional<Activity> findWithSupplierById(@Param("id") Long id);

    /**
     * {@code pattern} must already be lower-cased and have {@code \ % _} escaped with a backslash.
     */
    @Query("""
            select a from Activity a
            left join fetch a.supplier s
            where lower(a.title) like :pattern escape '\\'
               or lower(s.name) like :pattern escape '\\'
            order by a.rating desc nulls last, a.id asc
            """)
    List<Activity> searchByTitleOrSupplierName(@Param("pattern") String pattern, Pageable pageable);

    @Query("""
            select new com.getyourguide.catalog.repository.CheapestPriceRow(a.supplier.id, a.currency, min(a.price))
            from Activity a
            where a.supplier.id in :supplierIds
            group by a.supplier.id, a.currency
            order by a.supplier.id, a.currency
            """)
    List<CheapestPriceRow> findCheapestPrices(@Param("supplierIds") Collection<Long> supplierIds);
}
