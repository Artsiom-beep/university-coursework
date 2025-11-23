package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HuffmanEncoderTest {

    @Test
    void testSimpleEncoding() {
        HuffmanEncoder encoder = new HuffmanEncoder();
        String encoded = encoder.encode("aaaabbc");

        assertNotNull(encoded);
        assertFalse(encoded.isEmpty());
    }
}