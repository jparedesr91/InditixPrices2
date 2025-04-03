package com.inditex.price.infrastructure.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.inditex.price.application.usecases.FilterPriceUseCase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoggerTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private MethodSignature methodSignature;

    private final Logger loggerComponent = new Logger();

    @InjectMocks
    private FilterPriceUseCase priceService;

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
