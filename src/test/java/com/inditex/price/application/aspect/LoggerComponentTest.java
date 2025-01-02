package com.inditex.price.application.aspect;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.inditex.price.domain.services.PriceService;
import com.inditex.price.domain.services.impl.PriceServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoggerComponentTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private MethodSignature methodSignature;

    private final LoggerComponent loggerComponent = new LoggerComponent();

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void testErrorAspectLog() throws Throwable {
        Mockito.lenient().when(proceedingJoinPoint.getTarget()).thenReturn(priceService);
        Mockito.lenient().when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getMethod()).thenReturn(getClass().getDeclaredMethod("someMethod"));
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{"TESTING"});
        when(proceedingJoinPoint.proceed()).thenReturn(new Object());
        Object object = loggerComponent.logAroundExec(proceedingJoinPoint);
        assertNotNull(object);
    }

    private Object someMethod() {
        return new Object();
    }
}
