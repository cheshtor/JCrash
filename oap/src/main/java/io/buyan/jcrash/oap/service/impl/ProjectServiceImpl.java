package io.buyan.jcrash.oap.service.impl;

import com.alibaba.fastjson.JSON;
import io.buyan.jcrash.dubbo.scanner.structure.*;
import io.buyan.jcrash.oap.dao.*;
import io.buyan.jcrash.oap.entity.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import io.buyan.jcrash.oap.service.ProjectService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Service
public class ProjectServiceImpl implements ProjectService{

    @Resource
    private ProjectDao projectDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return projectDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Project record) {
        return projectDao.insert(record);
    }

    @Override
    public int insertSelective(Project record) {
        return projectDao.insertSelective(record);
    }

    @Override
    public Project selectByPrimaryKey(Long id) {
        return projectDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Project record) {
        return projectDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        return projectDao.updateByPrimaryKey(record);
    }

    @Resource
    private DubboJarDao dubboJarDao;

    @Resource
    private DubboJarErrorDao dubboJarErrorDao;

    @Resource
    private DubboInterfaceDao dubboInterfaceDao;

    @Resource
    private DubboMethodDao dubboMethodDao;

    @Resource
    private DubboParamDao dubboParamDao;

    @Resource
    private CustomBeanDao customBeanDao;

    @Resource
    private CustomBeanFieldDao customBeanFieldDao;

    @Resource
    private ProjectGlobalErrorDao projectGlobalErrorDao;

    @Override
    @Transactional
    public boolean saveScanResult(Result result) {
        Map<String, String> globalError = result.getGlobalError();
        Map<String, Map<String, String>> beanProperty = result.getBeanProperty();
        List<JarStructure> jars = result.getJars();

        long projectId = 1L;

        if (!globalError.isEmpty()) {
            List<ProjectGlobalError> errors = new ArrayList<>();
            globalError.forEach((itemName, message) -> {
                ProjectGlobalError error = new ProjectGlobalError();
                error.setProjectId(projectId);
                error.setItemName(itemName);
                error.setMessage(message);
                errors.add(error);
            });
            projectGlobalErrorDao.insert(errors);
        }

        for (JarStructure jar : jars) {
            // 保存 Jar
            DubboJar dubboJar = new DubboJar();
            dubboJar.setProjectId(projectId);
            dubboJar.setName(jar.getJarName());
            dubboJarDao.insert(dubboJar);
            Long jarId = dubboJar.getId();
            // 保存 Jar 解析错误信息
            Map<String, String> errors = jar.getErrors();
            if (!errors.isEmpty()) {
                List<DubboJarError> dubboJarErrors = new ArrayList<>();
                errors.forEach((classname, message) -> {
                    DubboJarError error = new DubboJarError();
                    error.setJarId(jarId);
                    error.setClassname(classname);
                    error.setMessage(message);
                    dubboJarErrors.add(error);
                });
                dubboJarErrorDao.insert(dubboJarErrors);
            }
            // 保存接口
            List<InterfaceStructure> interfaces = jar.getInterfaces();
            if (!interfaces.isEmpty()) {
                for (InterfaceStructure itf : interfaces) {
                    DubboInterface dubboInterface = new DubboInterface();
                    dubboInterface.setJarId(jarId);
                    dubboInterface.setClassname(itf.getClassname());
                    dubboInterfaceDao.insert(dubboInterface);
                    Long interfaceId = dubboInterface.getId();
                    // 保存方法
                    List<MethodStructure> methods = itf.getMethods();
                    if (!methods.isEmpty()) {
                        for (MethodStructure method : methods) {
                            DubboMethod dubboMethod = new DubboMethod();
                            dubboMethod.setInterfaceId(interfaceId);
                            dubboMethod.setMethodName(method.getMethodName());
                            dubboMethod.setReturnTypeJson(JSON.toJSONString(method.getReturnType()));
                            dubboMethod.setLiteralReturnType(method.getLiteralReturnType());
                            dubboMethod.setLiteralMethod(method.getLiteralMethod());
                            dubboMethodDao.insert(dubboMethod);
                            Long methodId = dubboMethod.getId();
                            // 保存方法参数
                            List<MethodParamStructure> params = method.getParams();
                            if (!params.isEmpty()) {
                                List<DubboParam> dubboParams = new ArrayList<>();
                                for (MethodParamStructure param : params) {
                                    DubboParam dubboParam = new DubboParam();
                                    dubboParam.setMethodId(methodId);
                                    dubboParam.setName(param.getName());
                                    dubboParam.setParamTypeJson(JSON.toJSONString(param.getTypeStructure()));
                                    dubboParam.setLiteralParam(param.getLiteralParam());
                                    dubboParams.add(dubboParam);
                                }
                                dubboParamDao.insert(dubboParams);
                            }
                        }
                    }
                }
            }
        }
        // 保存自定义类型
        if (!beanProperty.isEmpty()) {
            beanProperty.forEach((classname, properties) -> {
                CustomBean customBean = new CustomBean();
                customBean.setProjectId(projectId);
                customBean.setClassname(classname);
                customBeanDao.insert(customBean);
                Long customBeanId = customBean.getId();
                if (!properties.isEmpty()) {
                    List<CustomBeanField> customBeanFields = new ArrayList<>();
                    properties.forEach((fieldName, fieldType) -> {
                        CustomBeanField field = new CustomBeanField();
                        field.setBeanId(customBeanId);
                        field.setFieldName(fieldName);
                        field.setFieldType(fieldType);
                        customBeanFields.add(field);
                    });
                    customBeanFieldDao.insert(customBeanFields);
                }
            });
        }
        return true;
    }
}
