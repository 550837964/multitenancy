/**
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

package com.example.multitenancy;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This class reads the <code>multitenancy.mtapp</code> node from
 * <code>application.yml</code> file and populates a list of
 * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceProperties}
 * objects, with each instance containing the data source details about the
 * database like url, username, password etc
 *
 * 在配置文件application.yml中定义了租户数据库的数据源
 * 租户数据库（们）应当能够被应用程序代码使用，所以在这里进行注入
 *
 * 这个类使用了@ConfigurationProperties备注
 * 用于从配置文件application.yml中，解析multitenancy.mtapp节点下的属性
 *
 * 这个节点下的属性，即连接的两个（或者多个）数据库的链接用户名密码等
 *
 * 注入这个类之后，可以直接通过这个类获取所有的租户数据
 *
 * 租户数据不只是id，只有因为租户id是额外添加的所以需要专门增加一个get
 *
 * 其他的getter & setter 在类{@link DataSourceProperties}继承的
 * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceProperties}中已经写好了
 *
 * @author Sunit Katkar
 * @version 1.0
 * @since 1.0 (April 2018)
 */
@Configuration
@ConfigurationProperties("multitenancy.mtapp")
public class MultitenancyProperties {

    private List<DataSourceProperties> dataSourcesProps;

    public List<DataSourceProperties> getDataSources() {
        return this.dataSourcesProps;
    }

    public void setDataSources(List<DataSourceProperties> dataSourcesProps) {
        this.dataSourcesProps = dataSourcesProps;
    }

    public static class DataSourceProperties extends org.springframework.boot.autoconfigure.jdbc.DataSourceProperties {

        private String tenantId;

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }
    }
}
