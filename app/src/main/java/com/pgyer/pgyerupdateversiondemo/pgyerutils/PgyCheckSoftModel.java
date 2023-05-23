package com.pgyer.pgyerupdateversiondemo.pgyerutils;

/**
 * Created by liuqiang 2021-01-21 .
 */
public class PgyCheckSoftModel {
    /**
     *    private int buildBuildVersion;//蒲公英生成的用于区分历史版本的build号
     *     private String forceUpdateVersion;//强制更新版本号（未设置强置更新默认为空）
     *     private String forceUpdateVersionNo;//强制更新的版本编号
     *     private boolean needForceUpdate;//	是否强制更新
     *     private boolean buildHaveNewVersion;//是否有新版本
     *     private String downloadURL;//应用安装地址
     *     private String buildVersionNo;//上传包的版本编号，默认为1 (即编译的版本号，一般来说，编译一次会变动一次这个版本号, 在 Android 上叫 Version Code。对于 iOS 来说，是字符串类型；对于 Android 来说是一个整数。例如：1001，28等。)
     *     private String buildVersion;//版本号, 默认为1.0 (是应用向用户宣传时候用到的标识，例如：1.1、8.2.1等。)
     *     private String buildShortcutUrl;//	应用短链接
     *     private String buildUpdateDescription;//	应用更新说明
     *     private String buildDescription;//	应用介绍
     *     private String buildFileSize;//	应用apk大小 （单位是 字节）
     *     private String buildIcon;//	应用Icon
     *     private String buildName;//	应用名称
     */
    private int buildBuildVersion;//蒲公英生成的用于区分历史版本的build号
    private String forceUpdateVersion;//强制更新版本号（未设置强置更新默认为空）
    private String forceUpdateVersionNo;//强制更新的版本编号
    private boolean needForceUpdate;//	是否强制更新
    private boolean buildHaveNewVersion;//是否有新版本
    private String downloadURL;//应用安装地址
    private String buildVersionNo;//上传包的版本编号，默认为1 (即编译的版本号，一般来说，编译一次会变动一次这个版本号, 在 Android 上叫 Version Code。对于 iOS 来说，是字符串类型；对于 Android 来说是一个整数。例如：1001，28等。)
    private String buildVersion;//版本号, 默认为1.0 (是应用向用户宣传时候用到的标识，例如：1.1、8.2.1等。)
    private String buildShortcutUrl;//	应用短链接
    private String buildUpdateDescription;//	应用更新说明
    private String buildDescription;//  应用介绍
    private String buildFileSize;// apk大小 （单位 B字节）
    private String buildIcon;// app Icon
    private String buildName;// app Name


    public int getBuildBuildVersion() {
        return buildBuildVersion;
    }

    public void setBuildBuildVersion(int buildBuildVersion) {
        this.buildBuildVersion = buildBuildVersion;
    }

    public String getForceUpdateVersion() {
        return forceUpdateVersion;
    }

    public void setForceUpdateVersion(String forceUpdateVersion) {
        this.forceUpdateVersion = forceUpdateVersion;
    }

    public String getForceUpdateVersionNo() {
        return forceUpdateVersionNo;
    }

    public void setForceUpdateVersionNo(String forceUpdateVersionNo) {
        this.forceUpdateVersionNo = forceUpdateVersionNo;
    }

    public boolean isNeedForceUpdate() {
        return needForceUpdate;
    }

    public void setNeedForceUpdate(boolean needForceUpdate) {
        this.needForceUpdate = needForceUpdate;
    }

    public boolean isBuildHaveNewVersion() {
        return buildHaveNewVersion;
    }

    public void setBuildHaveNewVersion(boolean buildHaveNewVersion) {
        this.buildHaveNewVersion = buildHaveNewVersion;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getBuildVersionNo() {
        return buildVersionNo;
    }

    public void setBuildVersionNo(String buildVersionNo) {
        this.buildVersionNo = buildVersionNo;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getBuildShortcutUrl() {
        return buildShortcutUrl;
    }

    public void setBuildShortcutUrl(String buildShortcutUrl) {
        this.buildShortcutUrl = buildShortcutUrl;
    }

    public String getBuildUpdateDescription() {
        return buildUpdateDescription;
    }

    public void setBuildUpdateDescription(String buildUpdateDescription) {
        this.buildUpdateDescription = buildUpdateDescription;
    }

    public String getBuildFileSize() {
        return buildFileSize;
    }

    public void setBuildFileSize(String buildFileSize) {
        this.buildFileSize = buildFileSize;
    }

    public String getBuildDescription() {
        return buildDescription;
    }

    public void setBuildDescription(String buildDescription) {
        this.buildDescription = buildDescription;
    }

    public String getBuildIcon() {
        return buildIcon;
    }

    public void setBuildIcon(String buildIcon) {
        this.buildIcon = buildIcon;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    @Override
    public String toString() {
        return "CheckSoftModel{" +
                "buildBuildVersion=" + buildBuildVersion +
                ", forceUpdateVersion='" + forceUpdateVersion + '\'' +
                ", forceUpdateVersionNo='" + forceUpdateVersionNo + '\'' +
                ", needForceUpdate=" + needForceUpdate +
                ", buildHaveNewVersion=" + buildHaveNewVersion +
                ", downloadURL='" + downloadURL + '\'' +
                ", buildVersionNo='" + buildVersionNo + '\'' +
                ", buildVersion='" + buildVersion + '\'' +
                ", buildShortcutUrl='" + buildShortcutUrl + '\'' +
                ", buildUpdateDescription='" + buildUpdateDescription + '\'' +
                ", buildDescription='" + buildDescription + '\'' +
                ", buildFileSize='" + buildFileSize + '\'' +
                ", buildIcon='" + buildIcon + '\'' +
                ", buildName='" + buildName + '\'' +
                '}';
    }
}
