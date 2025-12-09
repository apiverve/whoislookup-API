// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     WHOISLookupData data = Converter.fromJsonString(jsonString);

package com.apiverve.whoislookup.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static WHOISLookupData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(WHOISLookupData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(WHOISLookupData.class);
        writer = mapper.writerFor(WHOISLookupData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// WHOISLookupData.java

package com.apiverve.whoislookup.data;

import com.fasterxml.jackson.annotation.*;
import java.time.OffsetDateTime;

public class WHOISLookupData {
    private String[] domainStatus;
    private String domainName;
    private String registryDomainID;
    private String registrarWHOISServer;
    private String registrarURL;
    private OffsetDateTime updatedDate;
    private OffsetDateTime createdDate;
    private OffsetDateTime expiryDate;
    private String registrar;
    private String registrarIANAID;
    private String registrarAbuseContactEmail;
    private String registrarAbuseContactPhone;
    private String dNSSEC;

    @JsonProperty("domainStatus")
    public String[] getDomainStatus() { return domainStatus; }
    @JsonProperty("domainStatus")
    public void setDomainStatus(String[] value) { this.domainStatus = value; }

    @JsonProperty("domainName")
    public String getDomainName() { return domainName; }
    @JsonProperty("domainName")
    public void setDomainName(String value) { this.domainName = value; }

    @JsonProperty("registryDomainID")
    public String getRegistryDomainID() { return registryDomainID; }
    @JsonProperty("registryDomainID")
    public void setRegistryDomainID(String value) { this.registryDomainID = value; }

    @JsonProperty("registrarWHOISServer")
    public String getRegistrarWHOISServer() { return registrarWHOISServer; }
    @JsonProperty("registrarWHOISServer")
    public void setRegistrarWHOISServer(String value) { this.registrarWHOISServer = value; }

    @JsonProperty("registrarURL")
    public String getRegistrarURL() { return registrarURL; }
    @JsonProperty("registrarURL")
    public void setRegistrarURL(String value) { this.registrarURL = value; }

    @JsonProperty("updatedDate")
    public OffsetDateTime getUpdatedDate() { return updatedDate; }
    @JsonProperty("updatedDate")
    public void setUpdatedDate(OffsetDateTime value) { this.updatedDate = value; }

    @JsonProperty("createdDate")
    public OffsetDateTime getCreatedDate() { return createdDate; }
    @JsonProperty("createdDate")
    public void setCreatedDate(OffsetDateTime value) { this.createdDate = value; }

    @JsonProperty("expiryDate")
    public OffsetDateTime getExpiryDate() { return expiryDate; }
    @JsonProperty("expiryDate")
    public void setExpiryDate(OffsetDateTime value) { this.expiryDate = value; }

    @JsonProperty("registrar")
    public String getRegistrar() { return registrar; }
    @JsonProperty("registrar")
    public void setRegistrar(String value) { this.registrar = value; }

    @JsonProperty("registrarIANAID")
    public String getRegistrarIANAID() { return registrarIANAID; }
    @JsonProperty("registrarIANAID")
    public void setRegistrarIANAID(String value) { this.registrarIANAID = value; }

    @JsonProperty("registrarAbuseContactEmail")
    public String getRegistrarAbuseContactEmail() { return registrarAbuseContactEmail; }
    @JsonProperty("registrarAbuseContactEmail")
    public void setRegistrarAbuseContactEmail(String value) { this.registrarAbuseContactEmail = value; }

    @JsonProperty("registrarAbuseContactPhone")
    public String getRegistrarAbuseContactPhone() { return registrarAbuseContactPhone; }
    @JsonProperty("registrarAbuseContactPhone")
    public void setRegistrarAbuseContactPhone(String value) { this.registrarAbuseContactPhone = value; }

    @JsonProperty("dNSSEC")
    public String getDNSSEC() { return dNSSEC; }
    @JsonProperty("dNSSEC")
    public void setDNSSEC(String value) { this.dNSSEC = value; }
}