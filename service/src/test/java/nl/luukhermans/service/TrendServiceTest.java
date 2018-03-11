package nl.luukhermans.service;

import nl.luukhermans.dao.TrendDao;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TrendServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private TrendDao trendDao;
    @InjectMocks
    private TrendService trendService;

    @Before
    public void SetUp() throws Exception {
        trendService = new TrendService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTrends_returnsTrends() {
        trendService.getAllTrends();

        verify(trendDao, times(1)).getAllTrends();
    }

    @Test
    public void upTrend_NullHashtag_GivesException() throws Exception {
        expectedException.expect(Exception.class);
        trendService.addOrUpTrend(null);
    }

    @Test
    public void downTrend_NullHashtag_GivesException() throws Exception {
        expectedException.expect(Exception.class);
        trendService.downOrDeleteTrend(null);
    }

}
