package com.example.tokenapi.cfg;

import lombok.NonNull;
import org.bson.types.Decimal128;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

import static java.time.ZoneId.systemDefault;
import static java.time.ZonedDateTime.ofInstant;

@Configuration
public class MongoCfg {
    // Método de conversión personalizada para convertir tipos de datos usadas entre el proceso de almacenamiento
    // y recuperación de datos de Mongo.
    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new DateToZonedDateTimeConverter(), // Convierte tipo Date a ZonedDateTime.
                new ZonedDateTimeToDateConverter(), // Convierte tipo ZonedDateTime a Date.
                new BigDecimalDecimal128Converter(),    // Convierte tipo BigDecimal a Decimal128.
                new Decimal128BigDecimalConverter() // Convierte Decimal128 a BigDecimal.
        ));
    }

    class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

        @Override
        public ZonedDateTime convert(Date source) {
            return source == null ? null : ofInstant(source.toInstant(), systemDefault());
        }
    }

    class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {

        @Override
        public Date convert(ZonedDateTime source) {
            return source == null ? null : Date.from(source.toInstant());
        }
    }

    @WritingConverter
    private static class BigDecimalDecimal128Converter implements Converter<BigDecimal, Decimal128> {

        @Override
        public Decimal128 convert(@NonNull BigDecimal source) {
            return new Decimal128(source);
        }
    }

    @ReadingConverter
    private static class Decimal128BigDecimalConverter implements Converter<Decimal128, BigDecimal> {

        @Override
        public BigDecimal convert(@NonNull Decimal128 source) {
            return source.bigDecimalValue();
        }

    }

}
