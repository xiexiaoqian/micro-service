package com.soft1851.content.center.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/15
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
