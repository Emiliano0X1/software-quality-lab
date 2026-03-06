package mx.edu.cetys.software_quality_lab.poc;


import org.junit.jupiter.api.*;

import java.util.logging.Logger;

@DisplayName("LifeCycle and Display Name annotation")
public class LifeCycleTest {
    private static final Logger LOG = Logger.getLogger(LifeCycleTest.class.getName());

    @BeforeAll
    static void beforeAll(){
        LOG.info("Before All Tests");
    }

    @BeforeEach
    void beforeEach(){
        LOG.info("Before Each Tests");
    }

    @Test
    @DisplayName("Test 1 : DAY ONE")
    void test1(){
        LOG.info("This is a test 1");
    }
    @Test
    @DisplayName("Test 2 : TEST TWO")
    void test2(){
        LOG.info("This is a test 2");
    }

    @AfterEach
    void afterEach(){
        LOG.info("After Each Tests");
    }

    @AfterAll
    static void afterAll(){
        LOG.info("After All Tests");
    }
}
