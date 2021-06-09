package repositoryTests

import com.example.semprace.Creator
import com.example.semprace.entity.Reservable
import com.example.semprace.entity.ReservableType
import com.example.semprace.repository.ReservableRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired

class ReservableRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    ReservableRepository repository

    @Test
    void findAllCourtsTest(){
        ReservableType rt = Creator.save(new ReservableType(isCourt: true)) as ReservableType;
        Creator.save(new Reservable(name: "Court 1", reservableType: rt, available: true))
        Creator.save(new Reservable(name: "Court 2", reservableType: rt, available: true))
        Creator.save(new Reservable(name: "Balls"))

        List<Reservable> courts = repository.findAllCourts();
        Assertions.assertEquals(2, courts.size())
        Assertions.assertEquals("Court 1", courts.get(0).getName())
    }
}
