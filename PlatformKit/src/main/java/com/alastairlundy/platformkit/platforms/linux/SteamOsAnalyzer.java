package com.alastairlundy.platformkit.platforms.linux;

import com.alastairlundy.platformkit.platforms.linux.enums.LinuxDistroBase;
import com.alastairlundy.platformkit.platforms.linux.enums.SteamOSMode;
import com.alastairlundy.platformkit.shared.PlatformAnalyzer;

import javax.naming.OperationNotSupportedException;

public class SteamOsAnalyzer extends LinuxAnalyzer{

    /**
     * Returns true for SteamOS3 based distributions.
     * @param includeHoloIsoAsSteamOs
     * @return
     * @throws OperationNotSupportedException
     */
    public boolean isSteamOS(boolean includeHoloIsoAsSteamOs) throws OperationNotSupportedException {
        if(PlatformAnalyzer.isLinux()){
            var distroInfo = getLinuxDistributionInformation();
            var distroBase = getDistroBase();

            if(distroBase.equals(LinuxDistroBase.Manjaro) || distroBase.equals(LinuxDistroBase.Arch)){
                if(distroInfo.getPrettyName().toLowerCase().contains("holo") && includeHoloIsoAsSteamOs){
                    return true;
                }
                else if(distroInfo.getPrettyName().toLowerCase().contains("holo") && !includeHoloIsoAsSteamOs){
                    return false;
                }
                else if(!distroInfo.getPrettyName().toLowerCase().contains("holo") && distroInfo.getPrettyName().toLowerCase().contains("steamos")){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param includeHoloIsoAsSteamOs
     * @return
     * @throws OperationNotSupportedException
     */
    public SteamOSMode getSteamOSMode(boolean includeHoloIsoAsSteamOs) throws OperationNotSupportedException {
        if(isSteamOS(includeHoloIsoAsSteamOs)){
            var distroBase = getDistroBase();

            var isSteamExcludingHolo = isSteamOS(false);

            if(distroBase.equals(LinuxDistroBase.Manjaro)){
                if(includeHoloIsoAsSteamOs || isSteamExcludingHolo){
                    return SteamOSMode.DesktopMode;
                }
                else{
                    return SteamOSMode.OsIsNotSteamOS;
                }
            }
            else if(distroBase.equals(LinuxDistroBase.Arch)){
                    if(includeHoloIsoAsSteamOs || isSteamExcludingHolo){
                        return SteamOSMode.GamingMode;
                    }
                   else{
                        return SteamOSMode.OsIsNotSteamOS;
                    }
            }
        }
        return SteamOSMode.OsIsNotSteamOS;
    }
}
