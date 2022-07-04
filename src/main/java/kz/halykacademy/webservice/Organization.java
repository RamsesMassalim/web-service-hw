package kz.halykacademy.webservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Organization {
    private static long lastId = 0;
    private final long id;
    private String title;
    private String address;
    private LocalDate creationDate;

    public Organization(String title, String address, String creationDate) throws ParseException {
        this.id = lastId;
        this.title = title;
        this.address = address;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        this.creationDate = dateFormat.parse(creationDate).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        lastId++;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreationDate(String creationDate) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.creationDate = (LocalDate) formatter.parse(creationDate);
    }
}
