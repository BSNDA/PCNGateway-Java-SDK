package com.bsnbase.sdk.entity.config;

import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;

public class PublicConfig {

    private static final String PubK_SM = "-----BEGIN PUBLIC KEY-----\n" +
            "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEIlh1C0iWAdcKnM/yAaZZT/42NVzT\n" +
            "Vyr31H9MDhHbPkp+/B3gsp5iZOr6OTAGO9jpN10/YMIrxt2IMg5auIEvMA==\n" +
            "-----END PUBLIC KEY-----\n";



    private static final String PubK_R1 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIC+zCCAqGgAwIBAgIUARhAfFSyhzcx9q4LdiYKl2UHo1YwCgYIKoZIzj0EAwIw\n" +
            "TjELMAkGA1UEBhMCQ04xEDAOBgNVBAgTB0JlaWppbmcxDDAKBgNVBAoTA0JTTjEP\n" +
            "MA0GA1UECxMGY2xpZW50MQ4wDAYDVQQDEwVic25jYTAgFw0xOTA5MjYxMDI0MDBa\n" +
            "GA8yMDk5MDkwNTAyMDQwMFowgZYxCzAJBgNVBAYTAkNOMREwDwYDVQQIEwhDaGFu\n" +
            "Z3NoYTEOMAwGA1UEChMFQ21QYXkxPTALBgNVBAsTBHVzZXIwEgYDVQQLEwtob25n\n" +
            "emFvbm9kZTAOBgNVBAsTB2JzbmJhc2UwCgYDVQQLEwNjb20xJTAjBgNVBAMMHG5v\n" +
            "ZGVAaG9uZ3phb25vZGUuYnNuYmFzZS5jb20wWTATBgcqhkjOPQIBBggqhkjOPQMB\n" +
            "BwNCAAQ/X2w5+pJoZXNCO81T4xMR+TxmFoYk6eG1kYML8HBPrUT6QflxtDXYsE9h\n" +
            "SzVAovq5DHww3vD8Xft/mxwsAXyuo4IBEDCCAQwwDgYDVR0PAQH/BAQDAgeAMAwG\n" +
            "A1UdEwEB/wQCMAAwHQYDVR0OBBYEFDPVPRqPANJavkNOg/WhPkUkH6wqMB8GA1Ud\n" +
            "IwQYMBaAFJuwcYba1G07p1ySkpzyes8L79OPMCUGA1UdEQQeMByCGmNhLmhvbmd6\n" +
            "YW9ub2RlLmJzbmJhc2UuY29tMIGEBggqAwQFBgcIAQR4eyJhdHRycyI6eyJoZi5B\n" +
            "ZmZpbGlhdGlvbiI6Imhvbmd6YW9ub2RlLmJzbmJhc2UuY29tIiwiaGYuRW5yb2xs\n" +
            "bWVudElEIjoibm9kZUBob25nemFvbm9kZS5ic25iYXNlLmNvbSIsImhmLlR5cGUi\n" +
            "OiJ1c2VyIn19MAoGCCqGSM49BAMCA0gAMEUCIQD7FBAQJsgS0uhaL4mjJgILdFfY\n" +
            "RKXvNutyKz/MqJ54RQIgNS67sSUCbOZRx1rWDqYEcBF1zypEFik25fNgY3zk5gM=\n" +
            "-----END CERTIFICATE-----\n";

    private static final String Pubk_K1 = "-----BEGIN PUBLIC KEY-----\n" +
            "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAETXyl2gs9ChpfEIpJqgNj6ob6GrNp5Zax\n" +
            "TRpgP7zigIEEwAUEQNL8cQoMS+yzPXTFrWlZubE4GFWFoi/Nxk2jdA==\n" +
            "-----END PUBLIC KEY-----\n";

    private static final String PubK_TEST_R1 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIC+zCCAqGgAwIBAgIUARhAfFSyhzcx9q4LdiYKl2UHo1YwCgYIKoZIzj0EAwIw\n" +
            "TjELMAkGA1UEBhMCQ04xEDAOBgNVBAgTB0JlaWppbmcxDDAKBgNVBAoTA0JTTjEP\n" +
            "MA0GA1UECxMGY2xpZW50MQ4wDAYDVQQDEwVic25jYTAgFw0xOTA5MjYxMDI0MDBa\n" +
            "GA8yMDk5MDkwNTAyMDQwMFowgZYxCzAJBgNVBAYTAkNOMREwDwYDVQQIEwhDaGFu\n" +
            "Z3NoYTEOMAwGA1UEChMFQ21QYXkxPTALBgNVBAsTBHVzZXIwEgYDVQQLEwtob25n\n" +
            "emFvbm9kZTAOBgNVBAsTB2JzbmJhc2UwCgYDVQQLEwNjb20xJTAjBgNVBAMMHG5v\n" +
            "ZGVAaG9uZ3phb25vZGUuYnNuYmFzZS5jb20wWTATBgcqhkjOPQIBBggqhkjOPQMB\n" +
            "BwNCAAQ/X2w5+pJoZXNCO81T4xMR+TxmFoYk6eG1kYML8HBPrUT6QflxtDXYsE9h\n" +
            "SzVAovq5DHww3vD8Xft/mxwsAXyuo4IBEDCCAQwwDgYDVR0PAQH/BAQDAgeAMAwG\n" +
            "A1UdEwEB/wQCMAAwHQYDVR0OBBYEFDPVPRqPANJavkNOg/WhPkUkH6wqMB8GA1Ud\n" +
            "IwQYMBaAFJuwcYba1G07p1ySkpzyes8L79OPMCUGA1UdEQQeMByCGmNhLmhvbmd6\n" +
            "YW9ub2RlLmJzbmJhc2UuY29tMIGEBggqAwQFBgcIAQR4eyJhdHRycyI6eyJoZi5B\n" +
            "ZmZpbGlhdGlvbiI6Imhvbmd6YW9ub2RlLmJzbmJhc2UuY29tIiwiaGYuRW5yb2xs\n" +
            "bWVudElEIjoibm9kZUBob25nemFvbm9kZS5ic25iYXNlLmNvbSIsImhmLlR5cGUi\n" +
            "OiJ1c2VyIn19MAoGCCqGSM49BAMCA0gAMEUCIQD7FBAQJsgS0uhaL4mjJgILdFfY\n" +
            "RKXvNutyKz/MqJ54RQIgNS67sSUCbOZRx1rWDqYEcBF1zypEFik25fNgY3zk5gM=\n" +
            "-----END CERTIFICATE-----\n";
    private static final String PubK_TEST_SM = "-----BEGIN PUBLIC KEY-----\n" +
            "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAECwJ5ftuqndO9H3ks1hD8cB6IA9lx\n" +
            "/b0Z2hnFZ77rgRm9Q4lY1aqIhkM63Lh6X7uwPsoRC1xkS0PMp5x/jnRWcw==\n" +
            "-----END PUBLIC KEY-----\n";

    private static final String PubK_TEST_K1 = "-----BEGIN PUBLIC KEY-----\n" +
            "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEh4WlY4pCv814i3WY5aRhtR3PoiIXOM1I\n" +
            "5xBGylyQTedo6DzJUdLfYZSZLs4py70D8FJtNICMVQCfezA7whHzUw==\n" +
            "-----END PUBLIC KEY-----\n";



    public static String getPublicKey(AlgorithmTypeEnum algorithmTypeEnum) {
        boolean isTest=false;
        switch (algorithmTypeEnum) {
            case AppAlgorithmType_SM2:
                return isTest?PubK_TEST_SM:PubK_SM;
            case AppAlgorithmType_R1:
                return isTest?PubK_TEST_R1:PubK_R1;
            case AppAlgorithmType_K1:
                return isTest?PubK_TEST_K1:Pubk_K1;
            default:
        }
        return "";
    }
}
