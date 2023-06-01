package com.example.sehnerbackend.repositories;

import com.example.sehnerbackend.entities.Company;
import com.example.sehnerbackend.models.SearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompaniesRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    @Query("SELECT COUNT(c) FROM Company c")
    long countEntries();

    @Query(value = "SELECT * FROM companies c " +
            "WHERE " +
            "CASE :#{#params.searchBy} " +
            "WHEN 'id' THEN c.id LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'name' THEN c.name LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'website' THEN c.website LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'category' THEN c.category LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'foundedOn' THEN CAST(c.founded_on AS TEXT) LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "ELSE 1=1 END " +
            "AND c.revenue BETWEEN :#{#params.minRevenue} AND :#{#params.maxRevenue} " +
            "ORDER BY " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'name' THEN CAST(c.name AS text) " +
            "WHEN 'website' THEN CAST(c.website AS text) " +
            "WHEN 'category' THEN CAST(c.category AS text) " +
            "WHEN 'foundedOn' THEN CAST(c.founded_on AS text) END NULLS LAST, " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'revenue' THEN c.revenue END NULLS LAST, " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'id' THEN CAST(c.id AS NUMERIC) END ",
            nativeQuery = true)
    Page<Company> findByDynamicCriteriaWithRange(@Param("params") SearchParams params, Pageable pageable);

    @Query(value = "SELECT * FROM companies c " +
            "WHERE " +
            "CASE :#{#params.searchBy} " +
            "WHEN 'id' THEN c.id LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'name' THEN c.name LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'website' THEN c.website LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'category' THEN c.category LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'foundedOn' THEN CAST(c.founded_on AS TEXT) LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "ELSE 1=1 END " +
            "ORDER BY " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'name' THEN CAST(c.name AS text) " +
            "WHEN 'website' THEN CAST(c.website AS text) " +
            "WHEN 'category' THEN CAST(c.category AS text) " +
            "WHEN 'foundedOn' THEN CAST(c.founded_on AS text) END NULLS LAST, " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'revenue' THEN c.revenue END NULLS LAST, " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'id' THEN CAST(c.id AS NUMERIC) END ",
            nativeQuery = true)
    Page<Company> findByDynamicCriteria(@Param("params") SearchParams params, Pageable pageable);

    @Query(value = "SELECT * FROM companies c " +
            "WHERE " +
            "CASE :#{#params.searchBy} " +
            "WHEN 'id' THEN c.id LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'name' THEN c.name LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'website' THEN c.website LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'category' THEN c.category LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'foundedOn' THEN CAST(c.founded_on AS TEXT) LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "ELSE 1=1 END " +
            "ORDER BY " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'name' THEN CAST(c.name AS text) " +
            "WHEN 'website' THEN CAST(c.website AS text) " +
            "WHEN 'category' THEN CAST(c.category AS text) " +
            "WHEN 'foundedOn' THEN CAST(c.founded_on AS text) END DESC NULLS LAST, " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'revenue' THEN c.revenue END DESC NULLS LAST, " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'id' THEN CAST(c.id AS NUMERIC) END DESC",
            nativeQuery = true)
    Page<Company> findByDynamicCriteriaDescending(@Param("params") SearchParams params, Pageable pageable);

    @Query(value = "SELECT * FROM companies c " +
            "WHERE " +
            "CASE :#{#params.searchBy} " +
            "WHEN 'id' THEN c.id LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'name' THEN c.name LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'website' THEN c.website LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'category' THEN c.category LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "WHEN 'foundedOn' THEN CAST(c.founded_on AS TEXT) LIKE CONCAT('%', :#{#params.searchValue}, '%') " +
            "ELSE 1=1 END " +
            "AND c.revenue BETWEEN :#{#params.minRevenue} AND :#{#params.maxRevenue} " +
            "ORDER BY " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'name' THEN CAST(c.name AS text) " +
            "WHEN 'website' THEN CAST(c.website AS text) " +
            "WHEN 'category' THEN CAST(c.category AS text) " +
            "WHEN 'foundedOn' THEN CAST(c.founded_on AS text) END DESC NULLS LAST, " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'revenue' THEN c.revenue END DESC NULLS LAST, " +
            "CASE :#{#params.sortBy} "+
            "WHEN 'id' THEN CAST(c.id AS NUMERIC) END DESC",
            nativeQuery = true)
    Page<Company> findByDynamicCriteriaWithRangeDescending(@Param("params") SearchParams params, Pageable pageable);
}
