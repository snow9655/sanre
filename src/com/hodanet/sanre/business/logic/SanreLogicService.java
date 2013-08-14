package com.hodanet.sanre.business.logic;

import com.hodanet.sanre.business.model.TemperatureModel;
import com.hodanet.sanre.business.service.SanreSQLiteService;

public class SanreLogicService {

    private static SanreLogicService sanreLogicService;

    public static SanreLogicService getInstance() {
        if (sanreLogicService == null) {
            sanreLogicService = new SanreLogicService();
        }
        return sanreLogicService;
    }

    private SanreSQLiteService sanreSQLiteService = SanreSQLiteService.getInstance();

    /**
     * ��Ӽ�¼
     * @param temp
     */
    public void AddTempeareture(TemperatureModel temp) {
        sanreSQLiteService.addToTemperature(temp);
    }

    /**
     * ��ȡ��¼
     * @return
     */
    public TemperatureModel getTemperature() {
        return sanreSQLiteService.getTemperature();
    }
}
