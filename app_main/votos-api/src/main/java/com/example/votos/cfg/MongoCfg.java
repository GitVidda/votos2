package com.example.votos.cfg;

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

//Configuración personalizada para manejar conversiones de tipos de datos entre la aplicación y MongoDB.
@Configuration
public class MongoCfg {
    // Método de conversión personalizada para convertir tipos de datos usadas entre el proceso de almacenamiento
    // y recuperación de datos de Mongo.
    @Bean
    //Define las conversiones personalizadas para MongoDB utilizando objetos convertidores específicos.
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new DateToZonedDateTimeConverter(), // Convierte tipo Date a ZonedDateTime.
                new ZonedDateTimeToDateConverter(), // Convierte tipo ZonedDateTime a Date.
                new BigDecimalDecimal128Converter(),    // Convierte tipo BigDecimal a Decimal128.
                new Decimal128BigDecimalConverter() // Convierte Decimal128 a BigDecimal.
        ));
    }

    //Convertidor de Date a ZonedDateTime.
    class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

        @Override
        public ZonedDateTime convert(Date source) {
            // Convierte una instancia de Date a ZonedDateTime, ajustando la zona horaria al sistema por defecto.
            return source == null ? null : ofInstant(source.toInstant(), systemDefault());
        }
    }

     // Convertidor de ZonedDateTime a Date.
    class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {

        @Override
        public Date convert(ZonedDateTime source) {
            // Convierte una instancia de ZonedDateTime a Date, manteniendo la información de fecha y hora.
            return source == null ? null : Date.from(source.toInstant());
        }
    }


    //Convertidor de BigDecimal a Decimal128 (para escritura en MongoDB).
    @WritingConverter
    private static class BigDecimalDecimal128Converter implements Converter<BigDecimal, Decimal128> {

        @Override
        public Decimal128 convert(@NonNull BigDecimal source) {
            // Convierte un objeto BigDecimal a Decimal128 para su almacenamiento en MongoDB.
            return new Decimal128(source);
        }
    }

    //Convertidor de Decimal128 a BigDecimal (para lectura desde MongoDB).
    @ReadingConverter
    private static class Decimal128BigDecimalConverter implements Converter<Decimal128, BigDecimal> {

        @Override
        public BigDecimal convert(@NonNull Decimal128 source) {
            // Convierte un objeto Decimal128 almacenado en MongoDB a un BigDecimal para su uso en la aplicación.
            return source.bigDecimalValue();
        }

    }

}
