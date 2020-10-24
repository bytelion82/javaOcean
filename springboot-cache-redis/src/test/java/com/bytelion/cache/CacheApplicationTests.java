package com.bytelion.cache;

import com.bytelion.cache.service.DeviceInfoService;
import com.bytelion.cache.service.PlateformUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CacheApplicationTests {

    @Autowired
    DeviceInfoService deviceInfoService;

    @Autowired
    PlateformUserService plateformUserService;



}
