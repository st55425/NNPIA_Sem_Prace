package repositoryTests

import com.example.semprace.entity.Reservable
import com.example.semprace.entity.ReservableType
import com.example.semprace.entity.ReservationItem
import com.example.semprace.repository.ReservationItemRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class ReservationItemRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    ReservationItemRepository reservationItemRepository;

    @Test
    void getAnonymizedReservationsTest(){
        ReservableType type = new ReservableType(isCourt: true);
        Reservable reservable = new Reservable(reservableType: type);
        creator.saveEntity(new ReservationItem(timeFrom: LocalDateTime.now().minusHours(1),
                timeTo: LocalDateTime.now(),reservable: reservable))
        List<ReservationItem> data = reservationItemRepository.getAnonymizedReservations();
        Assertions.assertEquals(1, data.size());
        Assertions.assertEquals(reservable, data.get(0).getReservable());
    }
}
