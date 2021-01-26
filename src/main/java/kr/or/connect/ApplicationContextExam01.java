package kr.or.connect;

import kr.or.connect.beans.UserBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam01 {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            "classpath:applicationContext.xml");
        System.out.println("초기화 완료");

        UserBean userBean = (UserBean) applicationContext.getBean("userBean");
        userBean.setName("jeong");

        System.out.println(userBean.getName());

        //싱글톤 패턴
        UserBean userBean2 = (UserBean) applicationContext.getBean("userBean");
        if (userBean == userBean2) {
            System.out.println("singleton pattern");
        }

        System.out.println(userBean2.getAge());
    }
}
