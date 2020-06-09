import bean.TextBean;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;

/**
 * @description: 反射
 * @author: 小猴子
 * @date: 2019-10-25 9:32
 */
public class TextReflect {


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        TextBean tb = new TextBean();
        tb.setName("齐天小圣");
        tb.setSize(27);
        int t = addObject(tb);

    }

    private static int addObject(Object o) throws IllegalAccessException, InstantiationException {
        Class clazz = o.getClass();
        Field[] field = clazz.getDeclaredFields();
        clazz.newInstance();
        for (Field field1 : field) {



        }

        return 1;
    }



}
