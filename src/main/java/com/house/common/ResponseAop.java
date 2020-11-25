//package com.house.common;
//
//import java.util.Properties;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * 统一封装返回值和异常处理
// *
// */
//@Slf4j
//@Aspect
//@Order(5)
//@Component
//public class ResponseAop {
//
//    //获取程序当前路径下的文件
//    private static final Properties properties = ReadPropertiesUtil.getProperties(System.getProperty("user.dir")
//        + "/src/main/resources/response.properties");
//
//     // 切点(在包下的所有controller以及他的子包)
//    //@Pointcut("execution(public * com.house.controller.*.controller..*(..))")
//    @Pointcut("execution(public * com.house.controller..*(..))")
//    public void httpResponse() {
//    }
//
//
//    //环切
//    @Around("httpResponse()")
//    public ReturnVO handlerController(ProceedingJoinPoint proceedingJoinPoint) {
//        ReturnVO returnVO = new ReturnVO();
//        try {
//        	 //获取方法的执行结果
//            Object proceed = proceedingJoinPoint.proceed();
//            //如果方法的执行结果是ReturnVO，则将该对象直接返回
//            if (proceed instanceof ReturnVO) {
//                returnVO = (ReturnVO) proceed;
//            } else {
//            	//否则，就要封装到ReturnVO的data中
//                returnVO.setData(proceed);
//            }
//        }  catch (Throwable throwable) {
//        	 //如果出现了异常，调用异常处理方法将错误信息封装到ReturnVO中并返回
//            returnVO = handlerException(throwable);
//        }
//        return returnVO;
//    }
//
//
//    //异常处理
//    private ReturnVO handlerException(Throwable throwable) {
//        ReturnVO returnVO = new ReturnVO();
//        //这里需要注意，返回枚举类中的枚举在写的时候应该和异常的名称相对应，以便动态的获取异常代码和异常信息
//        //获取异常名称的方法
//        String errorName = throwable.toString();
//        errorName = errorName.substring(errorName.lastIndexOf(".") + 1);
//        //直接获取properties文件中的内容
//		 returnVO.setMessage(properties.getProperty(ReturnCode.valueOf(errorName).msg()));
//        returnVO.setCode(properties.getProperty(ReturnCode.valueOf(errorName).val()));
//        return returnVO;
//    }
//}
