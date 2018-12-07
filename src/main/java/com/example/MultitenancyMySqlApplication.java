/*
 * Copyright 2018 onwards - Sunit Katkar (sunitkatkar@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <code>@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })</code>
 * 如果在配置文件中包含了JPA相关属性，spring boot运行时将会自动配置数据库
 * 但是在多租户程序中，程序启动时并不会连接上一个默认的数据库
 * 只有在用户携带租户标识符登录时，才会连接上对应的数据库
 * 所以通过exclude排除类{@link DataSourceAutoConfiguration}，让spring boot不进行自动数据库配置
 *
 *
 * <code>@SpringBootApplication</code>
 * 由于关闭了自动进行数据库配置，所以通过此注释进行JPA存储库扫描
 *
 * @author Sunit Katkar
 * @version 1.0
 * @since 1.0 (April 2018)
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableJpaRepositories("com.example.repository")
public class MultitenancyMySqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultitenancyMySqlApplication.class, args);
    }
}
