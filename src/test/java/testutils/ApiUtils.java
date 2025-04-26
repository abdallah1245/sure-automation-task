package testutils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {
    private static RequestSpecification requestSpec;

    public static RequestSpecification getRequestSpec(String cookie) {
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/")
                    .addHeader("Content-Type", "application/json")
                    .addCookie("orangehrm", cookie)
                    .build();
        }
        return requestSpec;
    }
}
