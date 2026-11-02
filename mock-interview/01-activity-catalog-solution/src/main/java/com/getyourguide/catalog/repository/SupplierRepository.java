package com.getyourguide.catalog.repository;

import com.getyourguide.catalog.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

    List<Supplier> findAllByOrderByNameAsc();

    @Query(value = """
            select new com.getyourguide.catalog.repository.SupplierStatisticsRow(
                s.id, s.name, count(a.id), avg(a.rating))
            from Supplier s
            left join s.activities a
            group by s.id, s.name
            order by avg(a.rating) desc nulls last, count(a.id) desc, s.id asc
            """,
            countQuery = "select count(s) from Supplier s")
    Page<SupplierStatisticsRow> findSupplierStatistics(Pageable pageable);
}
