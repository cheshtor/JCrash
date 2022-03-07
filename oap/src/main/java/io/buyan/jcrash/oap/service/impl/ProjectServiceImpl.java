package io.buyan.jcrash.oap.service.impl;

import com.alibaba.fastjson.JSON;
import io.buyan.jcrash.dubbo.scanner.structure.*;
import io.buyan.jcrash.oap.common.id.SnowflakeIDGenerator;
import io.buyan.jcrash.oap.dao.ProjectMapper;
import io.buyan.jcrash.oap.entity.*;
import io.buyan.jcrash.oap.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private SnowflakeIDGenerator idGenerator;

    @Resource
    private ProjectMapper projectMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public long createProject(Project project) {
        long projectId = idGenerator.getId();
        project.setId(projectId);
        projectMapper.insert(project);
        return projectId;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveScanResult(Result scanResult, Long projectId) {
        Map<String, String> globalError = scanResult.getGlobalError();
        Map<String, Map<String, String>> beanProperty = scanResult.getBeanProperty();
        List<JarStructure> jars = scanResult.getJars();

        List<DubboJar> dubboJars = new ArrayList<>();
        List<DubboJarError> dubboJarErrors = new ArrayList<>();
        List<DubboInterface> dubboInterfaces = new ArrayList<>();
        List<DubboMethod> dubboMethods = new ArrayList<>();
        List<DubboParam> dubboParams = new ArrayList<>();

        for (JarStructure jar : jars) {
            Long jarId = idGenerator.getId();
            DubboJar dubboJar = new DubboJar();
            dubboJar.setProjectId(projectId);
            dubboJar.setName(jar.getJarName());
            dubboJar.setScanVersion(System.currentTimeMillis());
            dubboJar.setId(jarId);
            dubboJars.add(dubboJar);
            // Jar 包解析错误
            Map<String, String> jarErrors = jar.getErrors();
            if (!jarErrors.isEmpty()) {
                for (Map.Entry<String, String> entry : jarErrors.entrySet()) {
                    DubboJarError jarError = new DubboJarError();
                    jarError.setId(idGenerator.getId());
                    jarError.setJarId(jarId);
                    jarError.setClassname(entry.getKey());
                    jarError.setMessage(entry.getValue());
                    dubboJarErrors.add(jarError);
                }
            }
            // Jar 包接口
            List<InterfaceStructure> interfaces = jar.getInterfaces();
            if (!interfaces.isEmpty()) {
                for (InterfaceStructure itf : interfaces) {
                    Long interfaceId = idGenerator.getId();
                    DubboInterface dubboInterface = new DubboInterface();
                    dubboInterface.setId(interfaceId);
                    dubboInterface.setJarId(jarId);
                    dubboInterface.setClassname(itf.getClassname());
                    dubboInterfaces.add(dubboInterface);
                    // 接口方法
                    List<MethodStructure> methods = itf.getMethods();
                    if (!methods.isEmpty()) {
                        for (MethodStructure method : methods) {
                            Long methodId = idGenerator.getId();
                            DubboMethod dubboMethod = new DubboMethod();
                            dubboMethod.setId(methodId);
                            dubboMethod.setInterfaceId(interfaceId);
                            dubboMethod.setMethodName(method.getMethodName());
                            dubboMethod.setReturnTypeJson(JSON.toJSONString(method.getReturnType()));
                            dubboMethod.setLiteralMethod(method.getLiteralMethod());
                            dubboMethod.setLiteralReturnType(method.getLiteralReturnType());
                            dubboMethods.add(dubboMethod);
                            // 方法参数
                            List<MethodParamStructure> params = method.getParams();
                            if (!params.isEmpty()) {
                                for (MethodParamStructure param : params) {
                                    DubboParam dubboParam = new DubboParam();
                                    dubboParam.setId(idGenerator.getId());
                                    dubboParam.setMethodId(methodId);
                                    dubboParam.setName(param.getName());
                                    dubboParam.setParamTypeJson(JSON.toJSONString(param.getTypeStructure()));
                                    dubboParam.setLiteralParam(param.getLiteralParam());
                                    dubboParams.add(dubboParam);
                                }
                            }
                        }
                    }
                }
            }
        }
        // 开始入库

    }
}
