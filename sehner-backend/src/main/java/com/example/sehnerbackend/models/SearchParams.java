package com.example.sehnerbackend.models;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class SearchParams {
    private String searchBy;
    private String searchValue;
    private String sortBy;
    private boolean ascending = true;
    private BigDecimal minRevenue;
    private BigDecimal maxRevenue;

}
