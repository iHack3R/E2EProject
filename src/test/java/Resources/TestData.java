package Resources;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "testDataForLogin")
    public static Object[][] testDataForLogin() {
        Object[][] testData = new Object[2][3];

        //Dataset #1
        testData[0][0] = "test@gmail.com";
        testData[0][1] = "1234567890";
        testData[0][2] = "Unregistered User";

        //Dataset #2
        testData[1][0] = "udit247@gmail.com";
        testData[1][1] = "g_GzkSrEVxkYH8G";
        testData[1][2] = "Registered User";
        return testData;
    }
}