package com.example.repository;

import com.example.model.home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertRepository extends JpaRepository<home, Long> {
    // 可以添加一些自定义的查询方法

    List<home> findByPerformers(String performers);
    // 这是一个自定义的查询方法，根据主要演职人员查询演唱会信息
}
