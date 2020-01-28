package com.tida.manual.toooltutoriol;/**
 * Created by nicajonh on 2019/7/26.
 * Description ${TEXT}
 */
/**
 * @ClassName AppleTest
 * @Description TODO
 * @Author nicajonh
 * @Date 2019/7/26 21:20
 * @Version 1.0
 **/

import java.util.List;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MatchingStrategy;


public class AppleTest {
    private static Apple apple;
    private static List<Apple> apples;

    public static void main(String[] args) {
        apple = new Apple();
        apple.setName("black");
        apple.setCreateAge("22");
        apple.setId("1");
        apples = new ArrayList<>(16);
        Apple apple1 = new Apple("1", "red", "21");
        Apple apple2 = new Apple("2", "blue", "22");
        Apple apple3 = new Apple("3", "yellow", "23");
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(customField());
        AppleDto appleDto = modelMapper.map(apple, AppleDto.class);
        List<AppleDto> userDTOS = modelMapper.map(apples,new TypeToken<List<AppleDto>>(){}.getType());

        System.out.println(userDTOS.toString());
    }

    private static PropertyMap customField() {
        /**
         * 自定义映射规则
         */
        return new PropertyMap<Apple, AppleDto>() {
            @Override
            protected void configure() {
                /**使用自定义转换规则*/
                map(source.getCreateAge(), destination.getCreate_age());
            }
        };
    }
}
