package com.thomas;

import com.thomas.repository.HelloRepository;
import com.thomas.services.HelloService;
import com.thomas.services.HelloServiceImpl;
import org.mockito.ArgumentMatchers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HelloServiceMockTest {

    @Mock
    private HelloRepository helloRepository;

    @InjectMocks
    private HelloService helloService = new HelloServiceImpl();

    @Test
    void testArgumentMatcherReturnedValueWithMockito() {
    	//Arrange
    	when(helloRepository.returnParam(ArgumentMatchers.eq("myString1"))).thenReturn("mocked string for parameter myString1");
    	when(helloRepository.returnParam(ArgumentMatchers.eq("myString2"))).thenReturn("mocked string for parameter myString2");
    	//Act
    	String actualValue1 = helloService.get("myString1");
    	String actualValue2 = helloService.get("myString2");
    	//Assert
    	String expectedValue1 = "mocked string for parameter myString1";
    	String expectedValue2 = "mocked string for parameter myString2";
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
    }
    
    @Test
    void testNumberOfCallsWithMockito() {
    	//Arrange
    	when(helloRepository.returnParam(ArgumentMatchers.eq("myString1"))).thenReturn("mocked string for parameter myString1");
    	when(helloRepository.returnParam(ArgumentMatchers.eq("myString2"))).thenReturn("mocked string for parameter myString2");
    	//Act
    	helloService.get("myString1");
    	helloService.get("myString2");
    	helloService.get("myString2");
    	//Assert
        Mockito.verify(helloRepository, Mockito.times(1)).returnParam("myString1");
        Mockito.verify(helloRepository, Mockito.times(2)).returnParam("myString2");
    }
}