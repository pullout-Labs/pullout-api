package com.pullout.config

import org.springframework.beans.factory.config.PropertiesFactoryBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import java.io.IOException
import java.util.*
import javax.sql.DataSource

/**
 * Local Quartz.config Setting from quartz-local.properties
 *
 */
@Profile("!prod")
class QuartzLocalConfig(
    private val dataSource: DataSource,
    private val applicationContext: ApplicationContext,
    private val platformTransactionManager: PlatformTransactionManager

) {
    @Bean
    fun schedulerFactoryBean(): SchedulerFactoryBean {
        val schedulerFactoryBean = SchedulerFactoryBean()
        val autoWiringSpringBeanJobFactory = AutoWiringSpringBeanJobFactory()

        autoWiringSpringBeanJobFactory.setApplicationContext(applicationContext)
        schedulerFactoryBean.setJobFactory(autoWiringSpringBeanJobFactory)
        schedulerFactoryBean.setDataSource(dataSource)
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.isAutoStartup = true;
        schedulerFactoryBean.setTransactionManager(platformTransactionManager);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        return schedulerFactoryBean;
    }

    private fun quartzProperties(): Properties {
        val propertiesFactoryBean = PropertiesFactoryBean()
        propertiesFactoryBean.setLocation(ClassPathResource("quartz-local.properties"))
        var properties: Properties? = null
        try {
            propertiesFactoryBean.afterPropertiesSet()
            properties = propertiesFactoryBean.getObject()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return properties!!
    }

}