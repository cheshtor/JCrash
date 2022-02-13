package io.buyan.jcrash.oap.web;

import io.buyan.jcrash.dubbo.scanner.structure.Result;
import io.buyan.jcrash.oap.service.ProjectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@RestController
public class ResultController {

    @Resource
    private ProjectService projectService;

    @PostMapping(value = "/save")
    public boolean save(@RequestBody Result result) {
        return projectService.saveScanResult(result);
    }

}
