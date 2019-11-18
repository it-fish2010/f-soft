package com.fsoft.core.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.core.common.service.impl.BaseServiceImpl;
import com.fsoft.core.sys.entity.SysLog;
import com.fsoft.core.sys.service.SysLogService;

@Transactional
@Service("sysLogService")
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {
}
