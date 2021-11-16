package com.taokoo.common.swagger3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Desc:swagger配置类SwaggerProperties
 * @author:Taokoo
 */
@Component
@ConfigurationProperties("swagger")//通过该属性可以获得application.yml中的swagger值
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerProperties {

    /**
     * 是否开启swagger
     */
    private Boolean enable;//对应application.yml中的swagger.enable

    /**
     * 版本信息
     */
    private String applicationVersion;//对应application.yml中的swagger.application.version

    /**
     * 标题
     */
    private String applicationTitle;//对应application.yml中的swagger.application-title

    /**
     * 描述信息
     */
    private String applicationDescription;//对应application.yml中的swagger.application-description

    /**
     * 后端代码地址
     */
    private String resourceUrl;//对应application.yml中的swagger.resource-url
}
