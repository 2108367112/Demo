// ISettingsService.aidl
package com.wudaokou.hdm.sysapi.settings;

/**
 * 系统设置服务
 */
interface ISettingsService {
    /**
     * 设置系统状态栏是否可展开
     * @param expandable 是否可展开
     * @return 是否设置成功
     */
    boolean setStatusBarExpandable(boolean expandable);

    /**
     * 获取系统状态栏是否可展开
     * @return 是否可展开
     */
    boolean isStatusBarExpandable();

    /**
     * 设置是否可以USB调试
     * @param debuggable 是否可以调试
     * @return 操作是否成功
     */
    boolean setUSBDebuggable(boolean debuggable);

    /**
     * 查询是否可以USB调试
     * @return 是否可以USB调试
     */
    boolean isUSBDebuggable();

    /**
     * 设置是否启用应用安装白名单，一旦开启了应用安装白名单，系统就会禁止非白名单应用安装。
     * @param enable 是否启用
     * @return 设置是否成功
     */
    boolean setEnableInstallWhiteList(boolean enable);

    /**
     * 获取是否启用应用安装白名单
     * @return 是否启用
     */
    boolean isEnableInstallWhiteList();

    /**
     * 设置应用安装白名单
     * @param type 操作类型： 0 - 增加, 1 - 删除
     * @param whiteList 应用列表，key是应用的PackageName，
     *                           value是应用的签名值。签名值算法请参考《盒马设备定制规范》文档中的附录B
     * @return 是否执行成功
     */
    boolean setInstallWhiteList(int type,in Map whiteList);

    /**
     * 获取已设置的应用安装白名单列表
     * @return 返回应用安装白名单，没有开启安装白名单返回null，只要开启了就返回非null的Map
     */
    Map getInstallWhiteList();

    /**
     * 设置是否允许卸载应用
     * @param allowUninstall 是否允许卸载
     * @return 是否允许
     */
    boolean setAllowUninstall(boolean allowUninstall);

    /**
     * 获取系统是否允许卸载应用
     * @return 是否允许卸载
     */
    boolean isAllowUninstall();

    /**
     * 设置系统时间
     * @param timeInMills 自1970年以来的毫秒数
     * @param timeZone 可选，时区字符串，使用TimeZone.getTimeZone(timeZone)解析。为null不设置时区
     * @return
     */
    String setSystemTime(long timeInMills,String timeZone);

    /*
     * 清除屏幕锁
     * @return 是否清除成功
     */
    boolean clearScreenLock();
}
