/*
      PlatformKit Java

      Copyright (c) Alastair Lundy 2024

      This Source Code Form is subject to the terms of the Mozilla Public
      License, v. 2.0. If a copy of the MPL was not distributed with this
      file, You can obtain one at http://mozilla.org/MPL/2.0/.
      */

package com.alastairlundy.platformkit.platforms.linux.models;

public class LinuxOsRelease {
    protected Boolean isLongTermSupportRelease;
    protected String name;
    protected String version;
    protected String identifier;
    protected String identifierLike;
    protected String prettyName;
    protected String versionId;

    protected String homeUrl;
    protected String supportUrl;
    protected String bugReportUrl;
    protected String privacyPolicyUrl;

    protected String versionCodename;


    public Boolean getLongTermSupportRelease() {
        return isLongTermSupportRelease;
    }

    public void setLongTermSupportRelease(Boolean longTermSupportRelease) {
        isLongTermSupportRelease = longTermSupportRelease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifierLike() {
        return identifierLike;
    }

    public void setIdentifierLike(String identifierLike) {
        this.identifierLike = identifierLike;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }

    public String getBugReportUrl() {
        return bugReportUrl;
    }

    public void setBugReportUrl(String bugReportUrl) {
        this.bugReportUrl = bugReportUrl;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public String getVersionCodename() {
        return versionCodename;
    }

    public void setVersionCodename(String versionCodename) {
        this.versionCodename = versionCodename;
    }
}
