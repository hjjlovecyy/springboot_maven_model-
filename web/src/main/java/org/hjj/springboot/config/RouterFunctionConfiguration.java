package org.hjj.springboot.config;

import org.hjj.springboot.domain.User;
import org.hjj.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * Created by hejiajun
 * On 2019/7/25
 */
@Configuration
public class RouterFunctionConfiguration {
    private final UserRepository userRepository;

    @Autowired
    public RouterFunctionConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 传统 Servlet：
     * 请求接口：ServletRequest 或者 HttpServletRequest
     * 响应接口：ServletResponse 或者 HttpServletResponse
     * Spring5.0 重新定义了服务请求和响应接口：
     * 请求接口：ServerRequest
     * 响应接口：ServerResponse
     * 定义 Get 请求，并且返回所有的用户对象，URI：/user/find/all
     *
     * Flux 是 0-N 个对象集合
     * Mono 是 0-1 个对象集合
     * Reactive 中的 Flux 或者 Mono 它是异步处理（非阻塞）
     * 集合对象基本上是同步处理（阻塞）
     *
     * Flux、Mono 是 Publisher（发布者）
     */
    @Bean
    public RouterFunction<ServerResponse> userFindAll() {
        return RouterFunctions.route(RequestPredicates.GET("/user/find/all"),
                request -> {
                    // 返回所有的用户对象
                    Collection<User> users = userRepository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux, User.class);
                });
    }
}
