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

import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class does the job of selecting the correct database based on the tenant
 * id found by the {@link CurrentTenantIdentifierResolverImpl}
 *
 * 用于根据租户id获取数据库
 *
 * 或者获取所有数据库
 *
 * @author Sunit Katkar
 * @version 1.0
 * @since 1.0 (April 2018)
 */
@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Map<String, DataSource> dataSourcesMtApp;

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.engine.jdbc.connections.spi.
     * AbstractDataSourceBasedMultiTenantConnectionProviderImpl#selectAnyDataSource(
     * )
     */

    /**
     * 获取配置文件中的
     *
     * 第一个数据库？？？
     *
     * @return
     */
    @Override
    protected DataSource selectAnyDataSource() {
        return this.dataSourcesMtApp.values().iterator().next();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.engine.jdbc.connections.spi.
     * AbstractDataSourceBasedMultiTenantConnectionProviderImpl#selectDataSource(
     * java.lang.String)
     */
    /**
     * 获取指定租户id对应的数据库
     */
    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return this.dataSourcesMtApp.get(tenantIdentifier);
    }
}
