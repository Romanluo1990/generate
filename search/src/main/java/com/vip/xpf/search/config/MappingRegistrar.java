package com.vip.xpf.search.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.Objects;
import java.util.Set;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/3
 * @Description:
 */
public class MappingRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware, BeanClassLoaderAware {

	private ClassLoader classLoader;

	private BeanFactory beanFactory;


	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Document.class));
		Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents("com.vip.xpf.search.indexmodel");
		ElasticsearchTemplate elasticsearchTemplate = beanFactory.getBean(ElasticsearchTemplate.class);
		candidateComponents.stream().
				map(BeanDefinition::getBeanClassName).
				map(clazz -> {
					try {
						return classLoader.loadClass(clazz);
					} catch (Exception e) {
						return null;
					}
				}).
				filter(Objects::nonNull).
				forEach(elasticsearchTemplate::putMapping);

	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
}
