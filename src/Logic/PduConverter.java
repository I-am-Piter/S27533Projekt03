package Logic;
public class PduConverter {

    public static byte[] encodeMessageToPdu(int phoneNumber, String message) {
        String encodedPdu = encodePhoneNumber(phoneNumber) + encodeMessage(message);
        int pduLength = encodedPdu.length() / 2;
        byte[] pduBytes = new byte[pduLength];

        for (int i = 0; i < pduLength; i++) {
            String byteString = encodedPdu.substring(i * 2, (i * 2) + 2);
            pduBytes[i] = (byte) Integer.parseInt(byteString, 16);
        }

        return pduBytes;
    }
    public static int extractPhoneNumberFromPdu(byte[] pdu) {
        StringBuilder pduBuilder = new StringBuilder();

        for (byte b : pdu) {
            int intValue = (b >= 0) ? b : (256 + b);
            String byteString = Integer.toHexString(intValue);

            if (byteString.length() < 2) {
                pduBuilder.append('0');
            }

            pduBuilder.append(byteString);
        }

        String decodedPdu = pduBuilder.toString();
        int phoneNumberLength = Integer.parseInt(decodedPdu.substring(0, 2), 16);
        String encodedPhoneNumber = decodedPdu.substring(2, (2 + (phoneNumberLength * 2)));

        return decodePhoneNumber(encodedPhoneNumber);
    }

    private static String encodePhoneNumber(int phoneNumber) {
        String phoneNumberString = Integer.toString(phoneNumber);
        int phoneNumberLength = phoneNumberString.length();

        if (phoneNumberLength % 2 != 0) {
            phoneNumberString = "0" + phoneNumberString;
            phoneNumberLength++;
        }

        return String.format("%02X%s", phoneNumberLength / 2, phoneNumberString);
    }

    private static int decodePhoneNumber(String encodedPhoneNumber) {
        if (encodedPhoneNumber.charAt(0) == '0') {
            encodedPhoneNumber = encodedPhoneNumber.substring(1);
        }
        return Integer.parseInt(encodedPhoneNumber, 16);
    }

    private static String encodeMessage(String message) {
        StringBuilder encodedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            String hexValue = Integer.toHexString(c);

            if (hexValue.length() < 2) {
                encodedMessage.append('0');
            }

            encodedMessage.append(hexValue);
        }

        return encodedMessage.toString();
    }
}