package com.java8.designpatterninaction.structure;

import com.java8.designpatterninaction.structure.bridge.Phone;
import com.java8.designpatterninaction.structure.bridge.impl.FolderPhone;
import com.java8.designpatterninaction.structure.bridge.impl.Huawei;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BridgePatternTest {

    @Test
    void testPhoneCall(){
        Phone huaweiMate20 = new FolderPhone(new Huawei());
        huaweiMate20.call();
    }

}
