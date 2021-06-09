package repositoryTests

import com.example.semprace.Creator
import com.example.semprace.dto.ReservationAnonymizedDto
import com.example.semprace.entity.Reservable
import com.example.semprace.entity.ReservableType
import com.example.semprace.entity.Reservation
import com.example.semprace.repository.ReservationRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class ReservationRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    ReservationRepository repository;

    @Test
    void findAllByReservableTest(){
        ReservableType rt = Creator.save(new ReservableType(isCourt: true)) as ReservableType;
        Reservable res = Creator.save(new Reservable(name: "Court 1", reservableType: rt, available: true)) as Reservable
        Creator.save(new Reservation(
                timeFrom: LocalDateTime.of(2021, 6,10,16,0),
                timeTo:  LocalDateTime.of(2021, 6,10,16,0),
                reservable: res
        ))
        Creator.save(new Reservation(
                timeFrom: LocalDateTime.of(2021, 6,10,18,0),
                timeTo:  LocalDateTime.of(2021, 6,10,19,0),
                reservable: res
        ))
        Creator.save(new Reservation());
        List<Reservation> reservations = repository.findAllByReservable(res.id);
        Assertions.assertEquals(2, reservations.size())
        Assertions.assertEquals(LocalDateTime.of(2021, 6,10,16,0), reservations.get(0).getTimeFrom())
    }
}
