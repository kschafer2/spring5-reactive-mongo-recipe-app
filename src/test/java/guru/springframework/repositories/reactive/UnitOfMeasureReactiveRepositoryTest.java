package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    public static final String UNIT = "Unit";
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(UNIT);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        Long count = unitOfMeasureReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), unitOfMeasureReactiveRepository.count().block());
    }

    @Test
    public void findByDescriptionTest() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(UNIT);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        UnitOfMeasure fetchedUOM = unitOfMeasureReactiveRepository.findByDescription(UNIT).block();

        assertNotNull(fetchedUOM.getId());
        assertEquals(UNIT, fetchedUOM.getDescription());
    }

}