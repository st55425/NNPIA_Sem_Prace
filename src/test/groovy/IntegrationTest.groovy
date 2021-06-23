import com.example.semprace.Creator
import com.example.semprace.NnpiaSemPraceApplication
import com.example.semprace.entity.Reservable
import com.example.semprace.entity.ReservableType
import com.example.semprace.entity.Reservation
import com.example.semprace.repository.ReservationRepository
import com.example.semprace.service.ReservableTypeService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@ContextConfiguration(classes = NnpiaSemPraceApplication.class)
@AutoConfigureMockMvc
@Import(Creator.class)
public class IntegrationTest {

    @Autowired
    Creator creator;

    @Autowired
    ReservableTypeService reservableTypeService;

    @Autowired
    ReservationRepository reservationRepository;


    @Test
    void deleteReservableType() {
        ReservableType type = creator.saveEntity(new ReservableType(name: "tenis")) as ReservableType
        Reservable reservable = creator.saveEntity(new Reservable(reservableType: type)) as Reservable
        Reservation reservation = creator.saveEntity(new Reservation(reservable: reservable)) as Reservation
        reservableTypeService.deleteById(type.id);
        List<Reservation> list = reservationRepository.findAll();
        Assertions.assertEquals(0, list.size());
    }


}