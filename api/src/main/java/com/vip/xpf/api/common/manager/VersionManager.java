package com.vip.xpf.api.common.manager;

public class VersionManager {

  private VersionManager() {}

  private static final ThreadLocal<String> versionThreadLocal = new InheritableThreadLocal<>();

  public static void setVersion(String version) {
    VersionManager.versionThreadLocal.set(version);
  }

  public static String getVersion() {
    return VersionManager.versionThreadLocal.get();
  }

  public static void removeVersion() {
    VersionManager.versionThreadLocal.remove();
  }
}
