package com.hisense.demo;

import android.os.RemoteException;

import com.wudaokou.hdm.sysapi.settings.ISettingsService;

import java.util.Map;

/**
 * Created by Administrator on 2019/1/5.
 */

public class SysSettings extends ISettingsService.Stub {
    @Override
    public boolean setStatusBarExpandable(boolean expandable) throws RemoteException {
        return false;
    }

    @Override
    public boolean isStatusBarExpandable() throws RemoteException {
        return false;
    }

    @Override
    public boolean setUSBDebuggable(boolean debuggable) throws RemoteException {
        return false;
    }

    @Override
    public boolean isUSBDebuggable() throws RemoteException {
        return false;
    }

    @Override
    public boolean setEnableInstallWhiteList(boolean enable) throws RemoteException {
        return false;
    }

    @Override
    public boolean isEnableInstallWhiteList() throws RemoteException {
        return false;
    }

    @Override
    public boolean setInstallWhiteList(int type, Map whiteList) throws RemoteException {
        return false;
    }

    @Override
    public Map getInstallWhiteList() throws RemoteException {
        return null;
    }

    @Override
    public boolean setAllowUninstall(boolean allowUninstall) throws RemoteException {
        return false;
    }

    @Override
    public boolean isAllowUninstall() throws RemoteException {
        return false;
    }

    @Override
    public String setSystemTime(long timeInMills, String timeZone) throws RemoteException {
        return null;
    }

    @Override
    public boolean clearScreenLock() throws RemoteException {
        return false;
    }
}
