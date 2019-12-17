package com.example.demo;

/**
 *
 * @author
 * @since
 */


import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

@GenerationAnnotation.ClassPreamble(author = "huapisong", date = "2019-03-07", currentRevision = 6,
   lastModified = "4/12/2004",
           lastModifiedBy = "Jane Doe",
           // Note array notation
           reviewers = {"Alice", "Bob", "Cindy"})
public class GenerationAnnotation{

    //语法
    @Documented
    @interface ClassPreamble {
        String author();
        String date();
        int currentRevision() default 1;
        String lastModified() default "N/A";
        String lastModifiedBy() default "N/A";
        String [] reviewers(); // = {"Alice", "Bob", "Cindy"};
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MyAnnotation {
        String company() default "huapisong";
    }

    static class AnnotationTest {
        @MyAnnotation(company = "huapisong1")
        public void execute() {
            System.out.println("do something~");
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AnnotationTest at = new AnnotationTest();
        at.execute();
        // 获取AnnotationTest 的Class实例
        Class<AnnotationTest> c = AnnotationTest.class;
        // 获取需要处理的方法 Method 实例
        Method method = c.getMethod("execute", new Class[]{});

        //判断该方法是否包含MyAnnotation 注解
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            // 获取该方法的 MyAnnotation 注解实例
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            // 执行该方法
            method.invoke(at, new Object[]{});

            // 获取myAnnotation 的属性
            String company = myAnnotation.company();
            System.out.println(company);
        }
        // 获取方法上的所有注解
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

}
