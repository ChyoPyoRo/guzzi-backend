package guzzi.project.DTO;

import java.sql.Timestamp;

public class userDto {
    private String ID;
    private String PASSWORD;
    private Timestamp CREATE_AT;
    private int id;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public Timestamp getCREATE_AT() {
        return CREATE_AT;
    }

    public void setCREATE_AT(Timestamp CREATE_AT) {
        this.CREATE_AT = CREATE_AT;
    }


}
