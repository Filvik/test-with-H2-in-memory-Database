package ru.skillfactorydemo.tgbot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.skillfactorydemo.tgbot.config.MyJpaConfig;
import ru.skillfactorydemo.tgbot.entity.MyObj;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

// указываем, какой файл конфигурации подключать, какой лоадер использовать, включаем транзакционность
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { MyJpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class MyObjRepositoryTest {

    // некий dao-сервис с нашим объектом, просто абстрактно, для примера
    @Resource
    private MyObjRepository myObjRepository;

    @Test
    public void givenObj_whenSave_thenGetOk() {
        // создали объект, чем-то его заполнили
        MyObj myObj = new MyObj(123L,1, "hello");

        System.out.println();
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println();

        // сохранили в базу
        myObjRepository.save(myObj);
        // нашли объект в базе
        MyObj myObjFound = myObjRepository.findById(123L).get();
        // проверили, что это действительно он
        assertEquals("hello", myObjFound.getHello());

    }
}