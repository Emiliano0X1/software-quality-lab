package mx.edu.cetys.software_quality_lab.poc;

import mx.edu.cetys.software_quality_lab.validators.EmailValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoListTests {

    @Mock
    List<String> mockList;

    @Mock
    EmailValidatorService emailValidatorServiceMock;

    @Test
    void shouldReturn_CustomSizeWhenMock(){
        //Arrange : Is already done for my mocklist

        //Define Mocks Behaviors
        when(mockList.size()).thenReturn(999_999_999)
                .thenReturn(1).thenReturn(67)
                .thenThrow(new RuntimeException())
                .thenReturn(0);
        //Act
        //var size = mockList.size();
        //Assert
        assertEquals(999_999_999,mockList.size());
        assertEquals(1,mockList.size());
        assertEquals(67,mockList.size());
        assertThrows(RuntimeException.class, () -> mockList.size());
        assertEquals(0,mockList.size());
    }

    @Test
    void shouldMock_ListGetWithParameters(){
        //Define mock behavior
        when(mockList.get(0))
                .thenReturn("Hello")
                .thenReturn("Aaron")
                .thenReturn("Cetys");
        when(mockList.get(1)).thenReturn("World");


        assertEquals("Hello",mockList.get(0));
        assertEquals("World",mockList.get(1));
        assertEquals("Aaron",mockList.get(0));
        assertEquals("Cetys",mockList.get(0));

    }

    @Test
    void mock_EmailValidator_withArgumentMatchers(){
        //Define mock behavior
        when(emailValidatorServiceMock.isValid(anyString()))
                .thenReturn(true);
        when(emailValidatorServiceMock.isValid(isNull()))
                .thenReturn(false)
                .thenReturn(true);


        assertTrue(emailValidatorServiceMock.isValid("Heello"));
        assertFalse(emailValidatorServiceMock.isValid(null));
        assertTrue(emailValidatorServiceMock.isValid(null));
        assertTrue(emailValidatorServiceMock.isValid(null));
       // assertTrue(emailValidatorServiceMock.isValid(""));

    }







}
