/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdao.dao;

import tfisher.dao.Keywords;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author  Theodore Chrysochoidis
 */
public class KeywordsTest {
    
    public KeywordsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setKeyword method, of class Keywords.
     */
    @Test
    public void testSetKeyword() {
        System.out.println("setKeyword");
        String keyword = "";
        Keywords instance = new Keywords();
        instance.setKeyword(keyword);
        
    }

    /**
     * Test of getKeywords method, of class Keywords.
     */
    @Test
    public void testGetKeywords() {
        System.out.println("getKeywords");
        Keywords instance = new Keywords();
        instance.setKeyword("testKeyword");
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("testKeyword");
        ArrayList<String> result = instance.getKeywords();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of changeState method, of class Keywords.
     */
    @Test
    public void testChangeState() {
        System.out.println("changeState");
        Keywords instance = new Keywords();
        instance.changeState();
       
    }

    /**
     * Test of getState method, of class Keywords.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Keywords instance = new Keywords();
        instance.setKeyword("keyword");
        String expResult = "add";
        String result = instance.getState();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getLatestKeyword method, of class Keywords.
     */
    @Test
    public void testGetLatestKeyword() {
        System.out.println("getLatestKeyword");
        Keywords instance = new Keywords();
        instance.setKeyword("Key1");
        instance.setKeyword("key2");
       
        String expResult =  "key2";
        String result = instance.getLatestKeyword();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getKeywordToDelete method, of class Keywords.
     */
    @Test
    public void testGetKeywordToDelete() {
        System.out.println("getKeywordToDelete");
        Keywords instance = new Keywords();
        String expResult = "";
        String result = instance.getKeywordToDelete();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of removeKeyword method, of class Keywords.
     */
    @Test
    public void testRemoveKeyword() {
        System.out.println("removeKeyword");
        String keyword = "";
        Keywords instance = new Keywords();
        boolean expResult = false;
        boolean result = instance.removeKeyword(keyword);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getArrayOfKeywords method, of class Keywords.
     */
    @Test
    public void testGetArrayOfKeywords() {
        System.out.println("getArrayOfKeywords");
        Keywords instance = new Keywords();
        instance.setKeyword("keyword1");        
        String[] expResult = {"keyword1"};
        String[] result = instance.getArrayOfKeywords();
        assertArrayEquals(expResult, result);
        
    }

    /**
     * Test of keywordExist method, of class Keywords.
     */
    @Test
    public void testKeywordExist() {
        System.out.println("keywordExist");
        String keyword = "";
        Keywords instance = new Keywords();
        boolean expResult = false;
        boolean result = instance.keywordExist(keyword);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkKeywordPattern method, of class Keywords.
     */
    @Test
    public void testCheckKeywordPattern() {
        System.out.println("checkKeywordPattern");
        String keyword = "ebola";
        Keywords instance = new Keywords();
        boolean expResult = true;
        boolean result = instance.checkKeywordPattern(keyword);
        assertEquals(expResult, result);      
    }

    /**
     * Test of setOccurences method, of class Keywords.
     */
    @Test
    public void testSetOccurences() {
        System.out.println("setOccurences");
        int occurences = 0;
        Keywords instance = new Keywords();
        boolean expResult = false;
        boolean result = instance.setOccurences(occurences);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setTimeInterval method, of class Keywords.
     */
    @Test
    public void testSetTimeInterval() {
        System.out.println("setTimeInterval");
        int timeInterval = 0;
        Keywords instance = new Keywords();
        boolean expResult = false;
        boolean result = instance.setTimeInterval(timeInterval);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOccurences method, of class Keywords.
     */
    @Test
    public void testGetOccurences() {
        System.out.println("getOccurences");
        Keywords instance = new Keywords();
        int expResult = 0;
        int result = instance.getOccurences();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getTimeInterval method, of class Keywords.
     */
    @Test
    public void testGetTimeInterval() {
        System.out.println("getTimeInterval");
        Keywords instance = new Keywords();
        int expResult = 0;
        int result = instance.getTimeInterval();
        assertEquals(expResult, result);
       
    }
    
}
