package ru.skillfactorydemo.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skillfactorydemo.tgbot.entity.MyObj;

import java.lang.annotation.Native;
import java.util.Collection;

@Repository
public interface MyObjRepository extends JpaRepository<MyObj, Long>{
    }
