package com.smileduster.vsboard.api.utils;

import com.fasterxml.uuid.impl.UUIDUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.util.ByteSource;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public final class RandomUtil {

    private static final RandomNumberGenerator RNG = new SecureRandomNumberGenerator();
    private static final Random RANDOM = new SecureRandom();

    public static ByteSource getSalt() {
        return RNG.nextBytes();
    }

    public static long getUserNo() {
        return (long) RANDOM.nextInt() + 0x80000000L;
    }

    public static byte[] getUUID() {
        return UUIDUtil.asByteArray(UUID.randomUUID());
    }

}
