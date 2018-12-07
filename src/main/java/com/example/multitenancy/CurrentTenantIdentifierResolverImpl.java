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

import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import com.example.util.TenantContextHolder;

/**
 * Hibernate needs to know which database to use i.e. which tenant to connect
 * to. This class provides a mechanism to provide the correct datasource at run
 * time.
 *
 * 这个类用于标识租户，由于在这个应用中租户id是存储在ThreadLocal中的
 * 所以这个类的作用就是从ThreadLocal取出租户id，让hibernate能够根据这个租户id连接到正确的数据库
 *
 * ThreadLocal 线程局部变量
 *
 * @see {@link com.example.util.TenantContextHolder}
 * @see {@link com.example.security.CustomAuthenticationFilter}
 * 
 * @author Sunit Katkar
 * @version 1.0
 * @since 1.0 (April 2018)
 */
@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT_ID = "tenant_1";

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.context.spi.CurrentTenantIdentifierResolver#
     * resolveCurrentTenantIdentifier()
     */

    /**
     * 从当前线程局部变量ThreadLocal中获取租户id
     * 租户id在{@link com.example.security.CustomAuthenticationFilter}被添加到线程局部变量中
     *
     * 如果当前的线程局部变量没有存入租户id，则使用默认的"tenant_1"作为租户id
     *
     * @return
     */
    @Override
    public String resolveCurrentTenantIdentifier() {
        // The tenant is stored in a ThreadLocal before the end user's login information
        // is submitted for spring security authentication mechanism. Refer to
        // CustomAuthenticationFilter
        String tenant = TenantContextHolder.getTenant();
        return StringUtils.isNotBlank(tenant) ? tenant : DEFAULT_TENANT_ID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.context.spi.CurrentTenantIdentifierResolver#
     * validateExistingCurrentSessions()
     */
    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}
