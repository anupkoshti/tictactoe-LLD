package org.pm.game;

import java.util.concurrent.TimeUnit;

public class User {
    String id;

    long lastActiveTime;

    public boolean isActiveAfter(int threshold, TimeUnit timeUnit) {
        return System.currentTimeMillis() - lastActiveTime >= TimeUnit.DAYS.toMillis(threshold);
    }
}
