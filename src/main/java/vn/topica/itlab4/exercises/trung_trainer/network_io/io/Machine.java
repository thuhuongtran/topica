package vn.topica.itlab4.exercises.trung_trainer.network_io.io;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Machine {
    private String code;
    private String name;
    private String owner;
    private Date inputDate;
    private int warrantyYear;
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public Machine() {
    }

    public Machine(String code, String name, String owner, String date, int warrantyYear) {
        this.inputDate = stringToDate(date);
        this.code = code;
        this.name = name;
        this.owner = owner;
        this.warrantyYear = warrantyYear;
    }

    private String getDateString() {
        return df.format(this.inputDate);
    }

    protected Date stringToDate(String date) {
        Date inputDate = null;
        try {
            inputDate = df.parse(date);
        } catch (ParseException e) {
            System.out.println("Fail when format date.");
            e.printStackTrace();
        }
        return inputDate;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(String date) {
        try {
            this.inputDate = df.parse(date);
        } catch (ParseException e) {
            System.out.println("Fail when format date.");
            e.printStackTrace();
        }
    }

    public int getWarrantyYear() {
        return warrantyYear;
    }

    public void setWarrantyYear(int warrantyYear) {
        this.warrantyYear = warrantyYear;
    }

    @Override
    public String toString() {
        return (new StringBuffer())
                .append(String.format("%s, ",getCode()))
                .append(String.format("%s, ",getName()))
                .append(String.format("%s, ",getOwner()))
                .append(String.format("%s, ",getDateString()))
                .append(String.format("%d",getWarrantyYear()))
                .toString();
    }
}
