package System;

import com.aspose.email.system.DateTime;

import java.util.Date;

public class Task {

    String ID;
    String status;
    String description;
    String localization;
    Date entranceDate;
    Date startedDate;
    Date finishedDate;
    Date requiredDate;
    String responsibleCompany;
    boolean smsActive;
    boolean mailActive;
    String type;
    String comment;

    public Task(){
    }

    public String getID() {
        return ID;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getLocalization() {
        return localization;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public String getResponsibleCompany() {
        return responsibleCompany;
    }

    public boolean isSmsActive() {
        return smsActive;
    }

    public boolean isMailActive() {
        return mailActive;
    }

    public String getType() {
        return type;
    }

    public String getComment() {
        return comment;
    }
}
