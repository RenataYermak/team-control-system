package com.example.teamcontrol.mapper.managerMapper;

import com.example.teamcontrol.database.entity.Manager;
import com.example.teamcontrol.dto.managerDto.ManagerCreatEditDto;
import com.example.teamcontrol.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerCreateEditMapper implements Mapper<ManagerCreatEditDto, Manager> {

    @Override
    public Manager map(ManagerCreatEditDto object) {
        Manager manager = new Manager();
        copy(object, manager);

        return manager;
    }

    @Override
    public Manager map(ManagerCreatEditDto fromObject, Manager toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ManagerCreatEditDto object, Manager Manager) {
        Manager.setFirstname(object.getFirstname());
        Manager.setLastname(object.getLastname());
        Manager.setBirthDate(object.getBirthDate());
        Manager.setHireDate(object.getHireDate());
    }
}
