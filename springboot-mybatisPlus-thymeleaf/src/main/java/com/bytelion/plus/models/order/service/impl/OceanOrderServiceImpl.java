package com.bytelion.plus.models.order.service.impl;

import com.bytelion.plus.models.order.entity.OceanOrder;
import com.bytelion.plus.models.order.mapper.OceanOrderMapper;
import com.bytelion.plus.models.order.service.IOceanOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author 太傅
 * @since 2020-11-09
 */
@Service
public class OceanOrderServiceImpl extends ServiceImpl<OceanOrderMapper, OceanOrder> implements IOceanOrderService {

}
