package org.ethereum.seth.core.math;

final public class HexPrefix {
    //1 byte is 8 bits
    //1 nibble is 4 bits
    private final static byte NIBBLE_SIZE = 4;

    static public byte[] encode(final byte[] input, boolean t) {
        final boolean isEven = (input.length % 2) == 0;
        final byte ft = t ? (byte) 1 : (byte) 0;
        final byte[] result = new byte[input.length / 2 + 1];
        if (isEven) {
            result[0] = (byte)(ft << NIBBLE_SIZE);
            for (int i = 0; i < result.length - 1; i++) {
                result[i+1] = (byte) ((input[i*2] << NIBBLE_SIZE) + input[i*2+1]);
            }
        } else {
            result[0] = (byte)(((ft + 1) << NIBBLE_SIZE) + input[0]);
            for (int i = 0; i < result.length - 1; i++) {
                result[i+1] = (byte) ((input[i*2+1] << NIBBLE_SIZE) + input[i*2+2]);
            }
        }
        return result;
    }
}
