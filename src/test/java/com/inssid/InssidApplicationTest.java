package com.inssid;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class InssidApplicationTest {

	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void contextLoads() {
		assertNull(null);
	}

	
}
