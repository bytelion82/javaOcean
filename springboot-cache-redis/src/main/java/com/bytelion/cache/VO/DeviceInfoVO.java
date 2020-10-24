package com.bytelion.cache.VO;

import com.bytelion.cache.entity.DeviceInfo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 太傅
 * @date 2020/10/17 : 16:14
 * #description
 */
@Data
@Accessors(chain = true)
public class DeviceInfoVO extends DeviceInfo {
    private static final long serialVersionUID = 4157747338182434244L;

    private String ok;
}
