package com.youngfog.back_csgo_spy.controller;

import com.youngfog.back_csgo_spy.domain.ResponseResult;
import com.youngfog.back_csgo_spy.mapper.NameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {
    @Autowired
    private NameMapper nameMapper;

    @GetMapping("/getNames")
    public ResponseResult getNames() {
        return new ResponseResult(200,"成功获取所有可用的昵称",nameMapper.getAll());
    }
}
