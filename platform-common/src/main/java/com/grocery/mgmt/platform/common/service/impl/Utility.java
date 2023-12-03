package com.grocery.mgmt.platform.common.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.net.http.HttpClient;

@Slf4j
@Service
public class Utility {

    public static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

}
