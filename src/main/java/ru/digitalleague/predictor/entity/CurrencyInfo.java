package ru.digitalleague.predictor.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(fluent = true, chain = true)
public class CurrencyInfo {
    @CsvBindByName(column = "nominal")
    public BigDecimal nominal;

    @CsvBindByName(column = "data")
    public String data;

    @CsvBindByName(column = "curs")
    public BigDecimal curs;
    
    @CsvBindByName(column = "cdx")
    public String cdx;
}