package com.tiddar.miniframework.example;

import com.tiddar.miniframework.entity.Test;
import com.tiddar.miniframework.orm.MiniORM;
import com.tiddar.miniframework.orm.MiniORMImpl;
import com.tiddar.miniframework.orm.OrmFactory;

public class OnlyOrmExample {

    public static void main(String[] args) {
        /**
         * 初始化所有的orm对象，扫描配置文件里miniframework.entity.package下的带有@Entity注解的类，
         * 将这些类装载在OrmFactory的静态map成员里
         */
        OrmFactory.init();
        /**
         * 获取某个实体类对应的orm接口的实现类实例,OrmFactory提供了获取接口的方法get(Class)
         * 传入想要获取接口的实体类的类型即可
         */
        MiniORM<Test> testMiniORM = OrmFactory.getOrm(Test.class);
        Test test = new Test();
        test.setTest("老张");
        testMiniORM.insert(test);


    }
}
