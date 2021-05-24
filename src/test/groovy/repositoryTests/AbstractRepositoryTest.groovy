package repositoryTests

import com.example.semprace.Creator
import com.example.semprace.NnpiaSemPraceApplication
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = NnpiaSemPraceApplication.class)
@Import(Creator.class)
abstract class AbstractRepositoryTest {

    @Autowired
    public Creator creator;


}
