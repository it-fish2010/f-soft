package com.fish.core.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fish.core.common.service.impl.BaseServiceImpl;
import com.fish.core.sys.entity.SysLog;
import com.fish.core.sys.service.SysLogService;

@Transactional
@Service("sysLogService")
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {
}
