package com.aegis.aegis;

import com.aegis.aegis.controller.UserController;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AegisApplicationTests {

    @Test
    public void testTestCall() {
        System.out.println("Testing testCall()...");
        assertNotNull(new AegisApplication().testCall());
    }
}
