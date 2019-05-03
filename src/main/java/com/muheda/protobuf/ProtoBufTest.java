package com.muheda.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufTest {


    public static void main(String[] args) throws InvalidProtocolBufferException {

        DataInfo.Student student = DataInfo.Student.newBuilder().setName("张三").setId(20).setAddress("北京").build();

        //转换成字节数组
        byte[] studentToByteArray = student.toByteArray();



        DataInfo.Student student1 = DataInfo.Student.parseFrom(studentToByteArray);

        System.out.println(student1.getAddress());
        System.out.println(student1.getName());
        System.out.println(student1.getId());


    }


}
