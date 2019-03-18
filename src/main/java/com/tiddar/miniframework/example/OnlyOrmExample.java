package com.tiddar.miniframework.example;

import com.tiddar.miniframework.BootStrap;
import com.tiddar.miniframework.entity.Test;
import com.tiddar.miniframework.factory.OrmFactory;
import com.tiddar.miniframework.orm.*;

import java.util.List;

public class OnlyOrmExample {

    public static void main(String[] args) {
        /**
         * 初始化所有的orm对象，扫描配置文件里miniframework.entity.package下的带有@Entity注解的类，
         * 将这些类装载在OrmFactory的静态map成员里
         */
        BootStrap.enableOrm();
        /**
         * 获取某个实体类对应的orm接口的实现类实例,OrmFactory提供了获取接口的方法get(Class)
         * 传入想要获取接口的实体类的类型即可
         */
        MiniORM<Test> testMiniORM = OrmFactory.getOrm(Test.class);
        Test test = new Test();
        test.setTest("老张");
        testMiniORM.insert(test);

        /**
         * 查询举例
         */
        System.out.println(testMiniORM.queryOne(new Param[]{new Param().field("test").like().value("%老%")}));

        List<Test> tests = testMiniORM.queryList(new Param[]{new Param().field("test").like().value("%老%")});
        for (int i = 0; i < tests.size(); i++) {

            System.out.println("list查询结果的第" + i + "条:" + tests.get(i));
        }


        int num = 0;
        int a = 1;
        int total = testMiniORM.total(new Param[]{new Param().field("test").like().value("%老%")});
        System.out.println("total:" + total);
        int pageCount =total%2==0?total/2:(total+1)/2;
        for (int i = 0; i < pageCount; i++) {
            Page<Test> testPage = testMiniORM.queryPage(new Param[]{new Param().field("test").like().value("%老%")}, ++num, 2);
            for (int i1 = 0; i1 < testPage.currentObjs.size(); i1++) {
                System.out.println("第" + i + "页第" + a + "个对象:" + testPage.currentObjs.get(i1));
                a++;
            }

        }

        /**
         *
         */

    }
}
