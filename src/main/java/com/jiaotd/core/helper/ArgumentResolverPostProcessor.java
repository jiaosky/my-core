package com.jiaotd.core.helper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：将自定义的 UserArgumentResolver 参数分解器加入 RequestMappingHandlerAdapter 请求参数适配器中
 *
 * @author jiaotengda
 * Date:     2020/3/22
 */
@Component
public class ArgumentResolverPostProcessor implements BeanPostProcessor {

    /**
     * spring 初始化 Bean 操作
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            //requestMappingHandlerAdapter进行修改
            RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
            List<HandlerMethodArgumentResolver> argumentResolvers = adapter.getArgumentResolvers();
            //添加自定义参数处理器
            argumentResolvers = addArgumentResolvers(argumentResolvers);
            adapter.setArgumentResolvers(argumentResolvers);
        }
        return null;
    }

    private List<HandlerMethodArgumentResolver> addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();
        //将自定的添加到最前面
        resolvers.add(new UserArgumentResolver());
        //将原本的添加后面
        resolvers.addAll(argumentResolvers);
        return resolvers;
    }
}
