package com.example;

import com.example.model.customer;
import com.example.model.home;
import com.example.repository.ConcertRepository;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<home> getAllConcerts() {
        //获取表home所有数据
        return concertRepository.findAll();
    }

    public List<String> getAllShowNames() {
        //获取所有的演出名称
        List<home> homes = concertRepository.findAll();
        return homes.stream()
                .map(home::getShowName)
                .collect(Collectors.toList());
    }

    public void saveConcert(home home) {
        //保存表home所有数据
        concertRepository.save(home);
    }

    public void deleteConcertById(Long id) {
        //根据id删除数据
        concertRepository.deleteById(id);
    }


    public home findTicketById(Long id) {
        // 通过 id 查找对应的票品数据
        return concertRepository.findById(id).orElse(null);
    }

    public void updateTicket(home updatedTicket) {
        // 保存修改后的数据
        concertRepository.save(updatedTicket);
    }

    public void sellTicket(Long homeId,customer customer) {
        // 获取相应的 home 对象
        home home = concertRepository.findById(homeId).orElse(null);

        if (home != null) {
            // 点击确认出售后，已售+数量，未售-数量
            int quantity = customer.getQuantity();
            home.setSoldTickets(home.getSoldTickets() + quantity);
            home.setRemainingTickets(home.getRemainingTickets() - quantity);

            // 保存更新后的 home 对象
            concertRepository.save(home);

            // 保存表 customer 的数据
            customer.setHome(home);
            customerRepository.save(customer);

        }

    }


    public List<customer> getCustomerInformation() {
        //获取表customer的所有数据
        return customerRepository.findAll();
    }





}
