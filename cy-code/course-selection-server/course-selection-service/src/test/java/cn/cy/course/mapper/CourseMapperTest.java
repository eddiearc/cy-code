//package cn.cy.course.mapper;
//
//import cn.cy.course.CourseServiceProvider;
//import cn.cy.course.pojo.Course;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//
///**
// * @author eddieVim
// * @微信公众号 埃迪的Code日记 / PositiveEddie
// * @blog https://blog.csdn.net/weixin_44129784
// * @create 2020/9/12 4:33 下午
// */
//@SpringBootTest(classes = CourseServiceProvider.class)
//class CourseMapperTest {
//
//    @Autowired
//    private CourseMapper courseMapper;
//
//    @Test
//    public void test() {
//        Course course = new Course();
//        course.setId("1");
//        course.setName("hello");
//        course.setOnline(1);
//        course.setTeacherId("123");
//        course.setTeacherName("qq");
//        course.setTerm(1);
//        course.setTimeAndPlace("诚毅学院");
//        course.setCategoryId(99);
//        course.setClassCount(12);
//        course.setCredit(2);
//
//
//        courseMapper.insert(course);
//
//        List<Course> courses = courseMapper.selectAll();
//
//        for (Course c : courses) {
//            System.out.println(c);
//        }
//    }
//}