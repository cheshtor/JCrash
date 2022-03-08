package io.buyan.jcrash.oap.service.impl;

import com.alibaba.fastjson.JSON;
import io.buyan.jcrash.dubbo.scanner.structure.*;
import io.buyan.jcrash.oap.common.id.SnowflakeIDGenerator;
import io.buyan.jcrash.oap.dao.*;
import io.buyan.jcrash.oap.entity.*;
import io.buyan.jcrash.oap.service.ScanResultService;
import io.buyan.jcrash.oap.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 扫描结果服务实现
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/8
 */
@Slf4j
@Service
public class ScanResultServiceImpl implements ScanResultService {

    @Resource
    private SnowflakeIDGenerator idGenerator;

    @Resource
    private ProjectGlobalErrorMapper projectGlobalErrorMapper;

    @Resource
    private CustomBeanMapper customBeanMapper;

    @Resource
    private CustomBeanFieldMapper customBeanFieldMapper;

    @Resource
    private DubboJarMapper dubboJarMapper;

    @Resource
    private DubboJarErrorMapper dubboJarErrorMapper;

    @Resource
    private DubboInterfaceMapper dubboInterfaceMapper;

    @Resource
    private DubboMethodMapper dubboMethodMapper;

    @Resource
    private DubboParamMapper dubboParamMapper;

    @Override
    public void saveScanResult(Result scanResult, Long projectId) {
        List<DubboJar> dubboJars = new ArrayList<>();
        List<DubboJarError> dubboJarErrors = new ArrayList<>();
        List<DubboInterface> dubboInterfaces = new ArrayList<>();
        List<DubboMethod> dubboMethods = new ArrayList<>();
        List<DubboParam> dubboParams = new ArrayList<>();

        List<JarStructure> jars = scanResult.getJars();
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
            collectJarError(dubboJarErrors, jarId, jarErrors);
            // Jar 包接口
            List<InterfaceStructure> interfaces = jar.getInterfaces();
            collectInterface(dubboInterfaces, dubboMethods, dubboParams, jarId, interfaces);
        }
        // 入库
        saveResult(dubboJars, dubboJarErrors, dubboInterfaces, dubboMethods, dubboParams);

        // 保存全局扫描异常
        Map<String, String> globalError = scanResult.getGlobalError();
        saveGlobalError(projectId, globalError);
        // 保存自定义类型信息
        Map<String, Map<String, String>> beanProperty = scanResult.getBeanProperty();
        saveCustomBeanInfo(projectId, beanProperty);
    }

    /**
     * 保存自定义类型详情
     * @param projectId 项目 ID
     * @param beanProperty 自定义类型详情
     */
    private void saveCustomBeanInfo(Long projectId, Map<String, Map<String, String>> beanProperty) {
        if (!beanProperty.isEmpty()) {
            List<CustomBean> customBeans = new ArrayList<>();
            List<CustomBeanField> customBeanFields = new ArrayList<>();
            for (Map.Entry<String, Map<String, String>> entry : beanProperty.entrySet()) {
                Long beanId = idGenerator.getId();
                CustomBean customBean = new CustomBean();
                customBean.setId(beanId);
                customBean.setProjectId(projectId);
                customBean.setClassname(entry.getKey());
                customBeans.add(customBean);

                for (Map.Entry<String, String> fieldEntry : entry.getValue().entrySet()) {
                    CustomBeanField customBeanField = new CustomBeanField();
                    customBeanField.setId(idGenerator.getId());
                    customBeanField.setBeanId(beanId);
                    customBeanField.setFieldName(fieldEntry.getKey());
                    customBeanField.setFieldType(fieldEntry.getValue());
                    customBeanFields.add(customBeanField);
                }
            }
            if (!customBeans.isEmpty()) {
                List<List<CustomBean>> slices = CollectionUtils.slice(customBeans);
                for (List<CustomBean> slice : slices) {
                    customBeanMapper.batchInsert(slice);
                }
            }
            if (!customBeanFields.isEmpty()) {
                List<List<CustomBeanField>> slices = CollectionUtils.slice(customBeanFields);
                for (List<CustomBeanField> slice : slices) {
                    customBeanFieldMapper.batchInsert(slice);
                }
            }
        }
    }

    /**
     * 保存全局扫描错误
     * @param projectId 项目 ID
     * @param globalError 全局错误
     */
    private void saveGlobalError(Long projectId, Map<String, String> globalError) {
        if (!globalError.isEmpty()) {
            List<ProjectGlobalError> projectGlobalErrors = new ArrayList<>();
            for (Map.Entry<String, String> entry : globalError.entrySet()) {
                ProjectGlobalError projectGlobalError = new ProjectGlobalError();
                projectGlobalError.setProjectId(projectId);
                projectGlobalError.setItemName(entry.getKey());
                projectGlobalError.setMessage(entry.getValue());
                projectGlobalErrors.add(projectGlobalError);
            }
            List<List<ProjectGlobalError>> slices = CollectionUtils.slice(projectGlobalErrors);
            for (List<ProjectGlobalError> slice : slices) {
                projectGlobalErrorMapper.batchInsert(slice);
            }
        }
    }

    /**
     * 保存扫描结果
     * @param dubboJars Jar 包
     * @param dubboJarErrors Jar 包扫描错误
     * @param dubboInterfaces 接口
     * @param dubboMethods 方法
     * @param dubboParams 方法参数
     */
    private void saveResult(List<DubboJar> dubboJars, List<DubboJarError> dubboJarErrors, List<DubboInterface> dubboInterfaces, List<DubboMethod> dubboMethods, List<DubboParam> dubboParams) {
        if (!dubboJars.isEmpty()) {
            dubboJarMapper.batchInsert(dubboJars);
        }
        if (!dubboJarErrors.isEmpty()) {
            dubboJarErrorMapper.batchInsert(dubboJarErrors);
        }
        if (!dubboInterfaces.isEmpty()) {
            List<List<DubboInterface>> slices = CollectionUtils.slice(dubboInterfaces);
            for (List<DubboInterface> slice : slices) {
                dubboInterfaceMapper.batchInsert(slice);
            }
        }
        if (!dubboMethods.isEmpty()) {
            List<List<DubboMethod>> slices = CollectionUtils.slice(dubboMethods);
            for (List<DubboMethod> slice : slices) {
                dubboMethodMapper.batchInsert(slice);
            }
        }
        if (!dubboParams.isEmpty()) {
            List<List<DubboParam>> slices = CollectionUtils.slice(dubboParams);
            for (List<DubboParam> slice : slices) {
                dubboParamMapper.batchInsert(slice);
            }
        }
    }

    /**
     * 从扫描结果中解析 Dubbo 接口
     * @param dubboInterfaces 接口集合
     * @param dubboMethods 方法集合
     * @param dubboParams 方法参数集合
     * @param jarId Jar 包 ID
     * @param interfaces 扫描出的接口结构
     */
    private void collectInterface(List<DubboInterface> dubboInterfaces, List<DubboMethod> dubboMethods, List<DubboParam> dubboParams, Long jarId, List<InterfaceStructure> interfaces) {
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

    /**
     * 解析 Jar 包扫描错误
     * @param dubboJarErrors 扫描错误集合
     * @param jarId Jar 包 ID
     * @param jarErrors Jar 包扫描异常结构
     */
    private void collectJarError(List<DubboJarError> dubboJarErrors, Long jarId, Map<String, String> jarErrors) {
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
    }
}
