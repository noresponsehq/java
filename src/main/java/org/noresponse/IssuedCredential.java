package org.noresponse;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** Hash an API key your signup gate issued. Hand-written beside the generated client. */
public final class IssuedCredential {
    private IssuedCredential() {
    }

    /**
     * SHA-256 of an issued API key, as 64 lower-case hex characters. The key never leaves your
     * backend: send only this hash, as issued_credential.sha256 on the assessment request. The key
     * is hashed as its UTF-8 bytes exactly as given, with no trimming or normalisation.
     *
     * @throws IllegalArgumentException if the key is null or empty
     */
    public static String sha256(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("issued key must not be empty");
        }
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(key.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(64);
            for (byte b : digest) {
                hex.append(Character.forDigit((b >> 4) & 0xf, 16)).append(Character.forDigit(b & 0xf, 16));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
