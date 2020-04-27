package com.manage.annoation.handler;


import com.manage.annoation.Authentication;
import com.manage.entity.UserDO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class AuthenticationHandler {

    @Pointcut(value = "execution(* com.manage.controller..*.*(..))")
    public void executeService() {

    }

    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        UserDO userDO = getUserDO();
        MethodSignature joinObject = (MethodSignature) joinPoint.getSignature();
        Method method = joinObject.getMethod();
        if (hasAnnotationOnMethod(method, Authentication.class)) {
            if (userDO == null) {
                return HttpStatus.NOT_FOUND;
            }
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    private UserDO getUserDO() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        HttpSession session = httpServletRequest.getSession();
        UserDO userDO = (UserDO) session.getAttribute("user");
        return userDO;
    }


    /**
     * 判断方法上是否有注解
     *
     * @param method
     * @param authenticationClass
     * @return
     */
    private boolean hasAnnotationOnMethod(Method method, Class authenticationClass) {
        Annotation annotation = method.getAnnotation(authenticationClass);
        if (annotation == null) {
            return false;
        }
        return true;
    }


}
