/*
      PlatformKit Java

      Copyright (c) Alastair Lundy 2024

      This Source Code Form is subject to the terms of the Mozilla Public
      License, v. 2.0. If a copy of the MPL was not distributed with this
      file, You can obtain one at http://mozilla.org/MPL/2.0/.
      */

package com.alastairlundy.platformkit.platforms.linux;

import com.alastairlundy.platformkit.platforms.linux.enums.LinuxDistroBase;
import com.alastairlundy.platformkit.platforms.linux.models.LinuxOsRelease;
import com.alastairlundy.platformkit.shared.PlatformAnalyzer;

import com.alastairlundy.utilityjar.Version;
import com.alastairlundy.utilityjar.helpers.StringHelper;

import javax.naming.OperationNotSupportedException;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

/**
 *  A class to Detect Linux versions, Linux features, and find out more about a user's Linux installation.
 */
public class LinuxAnalyzer {

    /**
     * Detects what base Linux Distribution a Distro is based off of.
     * @return
     * @throws OperationNotSupportedException
     */
    public LinuxDistroBase getDistroBase() throws OperationNotSupportedException {
        if (PlatformAnalyzer.isLinux())
        {
            var osRel = getLinuxDistributionInformation();

            if (osRel.getIdentifierLike().toLowerCase().contains("debian"))
            {
                return LinuxDistroBase.Debian;
            }
            if (osRel.getIdentifierLike().toLowerCase().contains("ubuntu"))
            {
                return LinuxDistroBase.Ubuntu;
            }
            if (osRel.getIdentifierLike().toLowerCase().contains("arch"))
            {
                return LinuxDistroBase.Arch;
            }
            if (osRel.getIdentifierLike().toLowerCase().contains("manjaro"))
            {
                return LinuxDistroBase.Manjaro;
            }
            if (osRel.getIdentifierLike().toLowerCase().contains("fedora"))
            {
                return LinuxDistroBase.Fedora;
            }

            throw new OperationNotSupportedException();
        }
        else
        {
            throw new OperationNotSupportedException();
        }
    }

    /**
     * Detects Linux Distribution information and returns it.
     * @return
     * @throws OperationNotSupportedException
     */
    public LinuxOsRelease getLinuxDistributionInformation() throws OperationNotSupportedException {
        LinuxOsRelease osRelease = new LinuxOsRelease();

        if(PlatformAnalyzer.isLinux()){
            ArrayList<String> results = getOsReleaseStrings();

            char[] delimiter = { ' ', '\t', '\n', '\r', '"' };

            for(String string : results){
                string = StringHelper.remove(string, delimiter);

                if(string.toUpperCase().contains("NAME=") &&
                !string.toUpperCase().contains("CODE") &&
                        !string.toUpperCase().contains("PRETTY")
                ){
                   var name = string.replace("NAME=", "");

                   osRelease.setName(name);
                }
                else if(string.toUpperCase().contains("VERSION=")){

                   var version = string.replace("VERSION=", "");

                    if(string.toUpperCase().contains("LTS")){
                        osRelease.setLongTermSupportRelease(true);

                        version = version.replace("LTS", "");
                    }
                    else{
                        osRelease.setLongTermSupportRelease(false);
                    }

                    osRelease.setVersion(version);
                }
                else if(string.toUpperCase().contains("ID=")){
                    osRelease.setIdentifier(string.replace("ID=", ""));
                }
                else if(string.toUpperCase().contains("ID_LIKE=")){
                    osRelease.setIdentifierLike(string.replace("ID_LIKE=", ""));

                    if(osRelease.getIdentifierLike().toLowerCase().contains("ubuntu") &&
                    osRelease.getIdentifierLike().toLowerCase().contains("debian")){
                        osRelease.setIdentifierLike("ubuntu");
                    }
                }
                else if(string.toUpperCase().contains("PRETTY_NAME=")){
                    osRelease.setPrettyName(string.replace("PRETTY_NAME=", ""));
                }
                else if(string.toUpperCase().contains("VERSION_ID=")){
                    osRelease.setVersionId(string.replace("VERSION_ID=", ""));
                }
                else if(string.toUpperCase().contains("HOME_URL=")){
                    osRelease.setHomeUrl(string.replace("HOME_URL=", ""));
                }
                else if(string.toUpperCase().contains("SUPPORT_URL=")){
                    osRelease.setSupportUrl(string.replace("SUPPORT_URL=", ""));
                }
                else if(string.toUpperCase().contains("BUG_REPORT_URL=")){
                    osRelease.setBugReportUrl(string.replace("BUG_REPORT_URL=", ""));
                }
                else if(string.toUpperCase().contains("PRIVACY_POLICY_URL=")){
                    osRelease.setPrivacyPolicyUrl(string.replace("PRIVACY_POLICY_URL=", ""));
                }
                else if(string.toUpperCase().contains("VERSION_CODENAME=")){
                    osRelease.setVersionCodename(string.replace("VERSION_CODENAME=", ""));
                }
            }

            return osRelease;
        }
        throw new OperationNotSupportedException();
    }

    private static ArrayList<String> getOsReleaseStrings() {
        File osReleaseFile = new File("/etc/os-release");

        ArrayList<String> results = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(osReleaseFile);

            while(scanner.hasNext()){
                var line = scanner.nextLine();

                results.add(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

    /**
     * Detects the Linux Distribution Version as read from /etc/os-release and re-formats it into the format of System.Version object
     *  WARNING: DOES NOT PRESERVE the version if the full version is in a Year.Month.Bugfix format.
     * @return
     * @throws OperationNotSupportedException
     */
    public Version getLinuxDistributionVersion() throws OperationNotSupportedException {
            return Version.parse(getLinuxDistributionVersionAsString());
    }

    /**
     *  Detects the Linux Distribution Version as read from /etc/os-release.
     *  Preserves the version if the full version is in a Year.Month.Bugfix format.
     * @return
     * @throws OperationNotSupportedException
     */
    public String getLinuxDistributionVersionAsString() throws OperationNotSupportedException {
        if(PlatformAnalyzer.isLinux()){
            var osRelease = getLinuxDistributionInformation();

            var osName = osRelease.getName().toLowerCase();

            if(osName.contains("ubuntu") || osName.contains("pop") || osName.contains("buntu")){
                if(osRelease.getVersion().contains(".4.x") || osRelease.getVersion().endsWith(".4")){
                    osRelease.setVersion(osRelease.getVersion().replace(".4", ".04"));
                }
            }

            return osRelease.getVersion();
        }
        throw new OperationNotSupportedException();
    }


}
