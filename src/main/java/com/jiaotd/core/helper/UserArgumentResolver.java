package com.jiaotd.core.helper;

import com.jiaotd.core.bean.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 描述：参数分解器
 *
 * @author jiaotengda
 * Date:     2020/3/22
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 参数类型是User的执行 #resolveArgument 方法
     *
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == User.class;
    }

    /**
     * 赋值
     *
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        User user = (User) nativeWebRequest.getAttribute("user", RequestAttributes.SCOPE_REQUEST);
        return user;
    }
}
