package testutils;

public class Payloads {
    public static String getAddEmployeePayload(String employeeId) {
        return String.format("{\n" +
                "  \"firstName\": \"Ayman\",\n" +
                "  \"middleName\": \"Weal\",\n" +
                "  \"lastName\": \"Ali\",\n" +
                "  \"empPicture\": null,\n" +
                "  \"employeeId\": \"%s\"\n" +
                "}", employeeId);
    }

    public static String getUpdatePersonalDetailsPayload(String employeeId) {
        return String.format("{\n" +
                "  \"lastName\": \"QC\",\n" +
                "  \"firstName\": \"Mostafa\",\n" +
                "  \"middleName\": \"Weal\",\n" +
                "  \"employeeId\": \"%s\",\n" +
                "  \"otherId\": \"5\",\n" +
                "  \"drivingLicenseNo\": \"5845\",\n" +
                "  \"drivingLicenseExpiredDate\": \"2025-08-31\",\n" +
                "  \"gender\": \"1\",\n" +
                "  \"maritalStatus\": \"Single\",\n" +
                "  \"birthday\": \"1998-11-30\",\n" +
                "  \"nationalityId\": 55\n" +
                "}", employeeId);
    }
}
