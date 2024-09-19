package com.porvenir;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

import java.util.Collections;

import static com.porvenir.util.Constant.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DummyMock {
    public static HttpHeaders buildHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HEADER_SERVICE_X_RQUID, Collections.singletonList("58565443423"));
        headers.put(HEADER_SERVICE_X_CHANNEL, Collections.singletonList("AHL"));
        headers.put(HEADER_SERVICE_X_COMPANYID, Collections.singletonList("0098"));
        headers.put(HEADER_SERVICE_X_IDENTSERIALNUM, Collections.singletonList("1026562580"));
        headers.put(HEADER_SERVICE_X_GOVISSUEIDENTTYPE, Collections.singletonList("CC"));
        headers.put(HEADER_SERVICE_X_IPADDR, Collections.singletonList("10.34.12.249"));

        return headers;
    }
}

