package com.smileduster.vsboard.api.tools;

import com.fasterxml.uuid.impl.UUIDUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.util.ByteSource;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public final class DefaultGenerator implements Generator {

    private static final RandomNumberGenerator RNG = new SecureRandomNumberGenerator();
    private static final Random RANDOM = new SecureRandom();

    public ByteSource getSalt() {
        return RNG.nextBytes();
    }

    public long getUserNo() {
        return (long) RANDOM.nextInt() + 0x80000000L;
    }

    public byte[] getUUID() {
        return UUIDUtil.asByteArray(UUID.randomUUID());
    }

}
