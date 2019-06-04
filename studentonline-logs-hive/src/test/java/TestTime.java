import com.nyist.hive.udf.util.TimeUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: skm
 * @Date: 2019/5/29 8:11
 * @Version scala-2.11.8 +jdk-1.8+spark-2.0.1
 */
public class TestTime {
    /**
     * 获取当前时间
     */
    @Test
    public  void getBeginTime(){
        Date date = new Date();
        System.out.println(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/01");
        try {

            String format = simpleDateFormat.format(date);
            System.out.println(simpleDateFormat.parse(simpleDateFormat.format(date)));
//            Date parse = simpleDateFormat.parse(simpleDateFormat.format(date));

//            System.out.println(parse);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Test
    public void test(){
        System.out.println(TimeUtil.getBeginDay(new Date()));
    }
}
