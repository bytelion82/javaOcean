
# 享元模式实战

> 享元模式（Flyweight Pattern）主要用于减少创建对象的数量，以减少内存占用和提高性能。
>这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
>享元模式尝试重用现有的同类对象，如果未找到匹配的对象，则创建新对象。
>我们将通过创建 5 个对象来画出 20 个分布于不同位置的圆来演示这种模式。由于只有 5 种可用的颜色，所以 color 属性被用来检查现有的 Circle 对象。
>
1. 主要使用依赖
```
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
            <version>2.6.0</version>
        </dependency>
```

#### 导入本地jar包（没有坐标）的方法
必须指定在 includeSystemScope 标签指定为 true，否者jar 包会无法运行
 ```
        <dependency>
            <groupId>com.arcsoft.face</groupId>
            <artifactId>arcsoft-sdk-face</artifactId>
            <version>3.0.0.0</version>
            <scope>system</scope>
            <systemPath>${basedir}/lib/arcsoft-sdk-face-3.0.0.0.jar</systemPath>
        </dependency>



            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <includeSystemScope>true</includeSystemScope>
                </configuration>
            </plugin>
```