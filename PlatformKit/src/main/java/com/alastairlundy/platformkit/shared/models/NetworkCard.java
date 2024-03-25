package com.alastairlundy.platformkit.shared.models;

public class NetworkCard {
    protected String name;
    protected String connectionName;
    protected boolean dhcpEnabled;
    protected String dhcpServer;
    protected String[] ipAddresses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public boolean isDhcpEnabled() {
        return dhcpEnabled;
    }

    public void setDhcpEnabled(boolean dhcpEnabled) {
        this.dhcpEnabled = dhcpEnabled;
    }

    public String getDhcpServer() {
        return dhcpServer;
    }

    public void setDhcpServer(String dhcpServer) {
        this.dhcpServer = dhcpServer;
    }

    public String[] getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(String[] ipAddresses) {
        this.ipAddresses = ipAddresses;
    }
}
