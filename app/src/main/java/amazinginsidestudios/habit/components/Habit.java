package amazinginsidestudios.habit.components;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.UUID;

/**
 * Created by Sangeeth Nandakumar on 07-12-2017.
 */

public class Habit {
    private Context context;
    private String name;
    private LogLevel logLevel;
    private String logTime;
    private String aim;
    private String catageory;
    private TargetLevel targetLevel;
    private String color;
    private int totalDays;
    private int completedDays;
    private boolean publicVisibility;
    private HabitTemplate habitTemplate;
    private String account;
    private String fingerprint;
    private String dateCreated;
    private String dateExpired;
    private String dateRemoved;
    private String device;
    private String appVersion;
    private boolean cloudSynced;
    private String dateSynced;
    private HabitState habitState;
    //Properties
    private boolean neverSync;
    private boolean discloseProgressToPublic;
    //Contents
    private String xmlData;

    private String generateFingerprint()
    {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    private String generateAppVersion()
    {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try
        {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        String version = info.versionName;
        return version;
    }

    private String generateTimestamp()
    {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        return ts;
    }

    public Habit(Context context)
    {
        this.context=context;
        fingerprint=generateFingerprint();
        appVersion=generateAppVersion();
        device= Build.MANUFACTURER +";"+Build.BRAND+";"+Build.MODEL;
        logLevel=LogLevel.DAILY;
        dateCreated=generateTimestamp();
        targetLevel=TargetLevel.LIMITED;
        completedDays=0;
        totalDays=0;
        publicVisibility=false;
        habitTemplate = HabitTemplate.YES_NO;
        cloudSynced=false;
        neverSync=false;
        discloseProgressToPublic=true;
        habitState=HabitState.ACTIVE;
        xmlData="<?xml version='1.0' encoding='UTF-8'?><habit></habit>";
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getCompletedDays() {
        return completedDays;
    }

    public void setCompletedDays(int completedDays) {
        this.completedDays = completedDays;
    }

    public boolean isPublicVisibility() {
        return publicVisibility;
    }

    public void setPublicVisibility(boolean publicVisibility) {
        this.publicVisibility = publicVisibility;
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

    public boolean isCloudSynced() {
        return cloudSynced;
    }

    public void setCloudSynced(boolean cloudSynced) {
        this.cloudSynced = cloudSynced;
    }

    public String getDateSynced() {
        return dateSynced;
    }

    public void setDateSynced(String dateSynced) {
        this.dateSynced = dateSynced;
    }

    public boolean isNeverSync() {
        return neverSync;
    }

    public void setNeverSync(boolean neverSync) {
        this.neverSync = neverSync;
    }

    public boolean isDiscloseProgressToPublic() {
        return discloseProgressToPublic;
    }

    public void setDiscloseProgressToPublic(boolean discloseProgressToPublic) {
        this.discloseProgressToPublic = discloseProgressToPublic;
    }

    public HabitState getHabitState() {
        return habitState;
    }

    public void setHabitState(HabitState habitState) {
        this.habitState = habitState;
    }

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }
}