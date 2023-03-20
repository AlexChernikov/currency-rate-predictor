package ru.digitalleague.philosophyit.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
public class CurrencyInfo {
    @CsvBindByName(column = "nominal")
    public Double nominal;

    @CsvBindByName(column = "data")
    public String data;

    @CsvBindByName(column = "curs")
    public Double curs;
    
    @CsvBindByName(column = "cdx")
    public String cdx;
}