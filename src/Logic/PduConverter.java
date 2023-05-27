package Logic;

public class PduConverter {
    public static byte[] encodeMessageToPdu(int phoneNumber, String message) {
        String phoneNumberString = String.valueOf(phoneNumber);
        String paddedPhoneNumber = addPaddingToPhoneNumber(phoneNumberString);
        String encodedPhoneNumber = encodePhoneNumber(paddedPhoneNumber);
        String encodedMessage = encodeMessage(message);
        int phoneNumberLength = encodedPhoneNumber.length() / 2;

        StringBuilder pduBuilder = new StringBuilder();
        pduBuilder.append("11"); // Message Type Indicator
        pduBuilder.append(String.format("%02X", phoneNumberLength)); // Phone Number Length
        pduBuilder.append(encodedPhoneNumber); // Encoded Phone Number
        pduBuilder.append("00"); // Protocol Identifier
        pduBuilder.append(encodedMessage); // Encoded Message

        return hexStringToByteArray(pduBuilder.toString());
    }

    public static String decodeMessageFromPdu(byte[] pdu) {
        String pduHex = byteArrayToHexString(pdu);

        int phoneNumberLength = Integer.parseInt(pduHex.substring(2, 4), 16);
        int phoneNumberEndIndex = 4 + (phoneNumberLength * 2);

        String encodedPhoneNumber = pduHex.substring(4, phoneNumberEndIndex);
        String decodedPhoneNumber = decodePhoneNumber(encodedPhoneNumber);

        int messageStartIndex = phoneNumberEndIndex + 2;
        String encodedMessage = pduHex.substring(messageStartIndex);
        String decodedMessage = decodeMessage(encodedMessage);

        return decodedMessage;
    }

    public static int extractPhoneNumberFromPdu(byte[] pdu) {
        String pduHex = byteArrayToHexString(pdu);

        int phoneNumberLength = Integer.parseInt(pduHex.substring(2, 4), 16);
        int phoneNumberEndIndex = 4 + (phoneNumberLength * 2);

        String encodedPhoneNumber = pduHex.substring(4, phoneNumberEndIndex);
        String decodedPhoneNumber = decodePhoneNumber(encodedPhoneNumber);

        return Integer.parseInt(decodedPhoneNumber);
    }

    private static String addPaddingToPhoneNumber(String phoneNumber) {
        StringBuilder paddedPhoneNumber = new StringBuilder(phoneNumber);
        while (paddedPhoneNumber.length() < 10) {
            paddedPhoneNumber.insert(0, "0");
        }
        return paddedPhoneNumber.toString();
    }

    private static String encodePhoneNumber(String phoneNumber) {
        StringBuilder encodedPhoneNumber = new StringBuilder();

        for (int i = 0; i < phoneNumber.length(); i += 2) {
            String digit1 = String.valueOf(phoneNumber.charAt(i));
            String digit2 = String.valueOf(phoneNumber.charAt(i + 1));
            String encodedDigit = digit2 + digit1;
            encodedPhoneNumber.append(encodedDigit);
        }

        return encodedPhoneNumber.toString();
    }

    private static String encodeMessage(String message) {
        StringBuilder encodedMessage = new StringBuilder();

        for (char c : message.toCharArray()) {
            String hex = String.format("%04X", (int) c);
            encodedMessage.append(hex);
        }

        return encodedMessage.toString();
    }

    private static String decodePhoneNumber(String encodedPhoneNumber) {
        StringBuilder decodedPhoneNumber = new StringBuilder();

        for (int i = 0; i < encodedPhoneNumber.length(); i += 2) {
            String digit1 = String.valueOf(encodedPhoneNumber.charAt(i + 1));
            String digit2 = String.valueOf(encodedPhoneNumber.charAt(i));
            String decodedDigit = digit1 + digit2;
            decodedPhoneNumber.append(decodedDigit);
        }

        return decodedPhoneNumber.toString();
    }

    private static String decodeMessage(String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();

        for (int i = 0; i < encodedMessage.length(); i += 4) {
            String hex = encodedMessage.substring(i, i + 4);
            int decimal = Integer.parseInt(hex, 16);
            char character = (char) decimal;
            decodedMessage.append(character);
        }

        return decodedMessage.toString();
    }

    private static byte[] hexStringToByteArray(String input) {
        int len = input.length() / 2;
        byte[] data = new byte[len];
        for (int i = 0; i < len; i++) {
            data[i] = (byte) ((Character.digit(input.charAt(2 * i), 16) << 4)
                    + Character.digit(input.charAt(2 * i + 1), 16));
        }
        return data;
    }

    private static String byteArrayToHexString(byte[] input) {
        StringBuilder hex = new StringBuilder();
        for (byte b : input) {
            hex.append(String.format("%02X", b));
        }
        return hex.toString();
    }
}









