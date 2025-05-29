package umc.study.config.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.PageHandler;
import umc.study.validation.annotation.CheckPage;

@Component
public class CheckPageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPage.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String paramName = parameter.getParameterName();
        String pageStr = webRequest.getParameter(paramName);

        Integer page;
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException | NullPointerException e) {
            throw new PageHandler(ErrorStatus.PAGE_OUT_OF_RANGE);
        }

        if (page <= 0) {
            throw new PageHandler(ErrorStatus.PAGE_OUT_OF_RANGE);
        }

        return page - 1;
    }
}

