package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.dubbo.scanner.structure.Result;
import io.buyan.jcrash.oap.entity.Project;
    /**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
public interface ProjectService{


    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    boolean saveScanResult(Result result);

}
