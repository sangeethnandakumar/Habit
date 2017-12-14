package amazinginsidestudios.habit.components;

/**
 * Created by Sangeeth Nandakumar on 07-12-2017.
 */

public class Habit {
    public String name;
    public LogLevel logLevel;
    public String logTime;
    public String aim;
    public String catageory;
    public TargetLevel targetLevel;
    public Color color;
    public String totalDays;
    public String completedDays;
    public PublicVisibility publicVisibility;
    public HabitTemplate habitTemplate;
    public String account;
    public String fingerprint;
    public String dateCreated;
    public String dateExpired;
    public String dateRemoved;
    public String device;
    public String appVersion;
    public Booler cloudSynced;
    public String dateSynced;
    public HabitState habitState;
    //Properties
    public Booler neverSync;
    public Booler discloseProgressToPublic;
    //Contents
    public String xmlData;

    public Habit() {
    }


    public PublicVisibility getPublicVisibility() {
        return publicVisibility;
    }

    public void setPublicVisibility(PublicVisibility publicVisibility) {
        this.publicVisibility = publicVisibility;
    }

    public void setCloudSynced(Booler cloudSynced) {
        this.cloudSynced = cloudSynced;
    }

    public void setNeverSync(Booler neverSync) {
        this.neverSync = neverSync;
    }

    public void setDiscloseProgressToPublic(Booler discloseProgressToPublic) {
        this.discloseProgressToPublic = discloseProgressToPublic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getCatageory() {
        return catageory;
    }

    public void setCatageory(String catageory) {
        this.catageory = catageory;
    }

    public TargetLevel getTargetLevel() {
        return targetLevel;
    }

    public void setTargetLevel(TargetLevel targetLevel) {
        this.targetLevel = targetLevel;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }

    public String getCompletedDays() {
        return completedDays;
    }

    public void setCompletedDays(String completedDays) {
        this.completedDays = completedDays;
    }

    public PublicVisibility isPublicVisibility() {
        return publicVisibility;
    }

    public HabitTemplate getHabitTemplate() {
        return habitTemplate;
    }

    public void setHabitTemplate(HabitTemplate habitTemplate) {
        this.habitTemplate = habitTemplate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public String getDateRemoved() {
        return dateRemoved;
    }

    public void setDateRemoved(String dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Booler isCloudSynced() {
        return cloudSynced;
    }

    public String getDateSynced() {
        return dateSynced;
    }

    public void setDateSynced(String dateSynced) {
        this.dateSynced = dateSynced;
    }

    public HabitState getHabitState() {
        return habitState;
    }

    public void setHabitState(HabitState habitState) {
        this.habitState = habitState;
    }

    public Booler isNeverSync() {
        return neverSync;
    }

    public Booler isDiscloseProgressToPublic() {
        return discloseProgressToPublic;
    }

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }
}