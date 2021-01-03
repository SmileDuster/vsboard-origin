package com.smileduster.vsboard.api.tools;

import org.apache.shiro.util.ByteSource;

public interface Generator {

    ByteSource getSalt();

    long getUserNo();

    byte[] getUUID();

}
