package br.com.compasso.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.compasso.converter.mocks.MockClient;
import br.com.compasso.data.model.Client;
import br.com.compasso.data.v1.vo.ClientVO;

public class DozerConverterTest {
	
    MockClient inputObject;

    @Before
    public void setUp() {
        inputObject = new MockClient();
    }

    @Test
    public void parseEntityToVOTest() {
        ClientVO output = DozerConverter.parseObject(inputObject.mockEntity(), ClientVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getKey());
        Assert.assertEquals("Full Name Test0", output.getFullName());
        Assert.assertEquals(32, output.getAge());
        Assert.assertEquals("Male", output.getGender());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<ClientVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), ClientVO.class);
        ClientVO outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
        Assert.assertEquals("Full Name Test0", outputZero.getFullName());
        Assert.assertEquals("Male", outputZero.getGender());
        
        ClientVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
        Assert.assertEquals("Full Name Test7", outputSeven.getFullName());
        Assert.assertEquals("Female", outputSeven.getGender());
        
        ClientVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        Assert.assertEquals("Full Name Test12", outputTwelve.getFullName());
        Assert.assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parseVOToEntityTest() {
    	Client output = DozerConverter.parseObject(inputObject.mockVO(), Client.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Full Name Test0", output.getFullName());
        Assert.assertEquals("Male", output.getGender());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Client> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Client.class);
        Client outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("Full Name Test0", outputZero.getFullName());
        Assert.assertEquals("Male", outputZero.getGender());
        
        Client outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("Full Name Test7", outputSeven.getFullName());
        Assert.assertEquals("Female", outputSeven.getGender());
        
        Client outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("Full Name Test12", outputTwelve.getFullName());
        Assert.assertEquals("Male", outputTwelve.getGender());
    }
}
