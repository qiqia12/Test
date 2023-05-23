package com.qiqi.pojo;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @projectName: Test
 * @package: com.qiqi.pojo
 * @className: User
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/23 17:56
 * @version: 1.0
 */
@Data
@ToString
@AllArgsConstructor
public class User {
    public int age;
    public String id;
}
