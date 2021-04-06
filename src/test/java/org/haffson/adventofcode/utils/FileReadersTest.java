package org.haffson.adventofcode.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileReadersTest {

    @Autowired
    FileReaders fileReaders;

    @Test
    public void testReadFileIntoArrayOfIntegersWithEmptyLine(){
        String filePath = "/utilsTestFiles/fileReadersExampleInputWithEmptyLine.txt";
        int[] expectedResult = new int[]{1721, 979, 366, 299, 675, 1456};
        int[] actualResult = fileReaders.readFileIntoArrayOfIntegers(filePath);
        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testReadFileIntoArrayOfIntegersWithoutEmptyLine(){
        String filePath = "/utilsTestFiles/fileReadersExampleInputWithoutEmptyLine.txt";
        int[] expectedResult = new int[]{1721, 979, 366, 299, 675, 1456};
        int[] actualResult = fileReaders.readFileIntoArrayOfIntegers(filePath);
        Assert.assertArrayEquals(expectedResult, actualResult);
    }
}