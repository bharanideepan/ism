package com.ideas2it.ism.util;

import java.util.ArrayList;
import java.util.List;

public class CalculatePage {
	
    /**
     * Calculate the no of pages from total count of players available.
     * If the number of page is multiples of given limit the exact quotient 
     *     is returned else the pagecount is increased by one. The remaining 
     *     records are to be displayed in next page.
     *      
     *
     * @param totalCount - Count of no of rows in table. 
     * @param limit - number records to be displayed in single page.
     * @return pageNos - List consist of page numbers.
     * @throws CricWorldException - Error while getting totalcount from DB.
     */
    public static List<Integer> calculatePages(int totalCount, int limit) {
        int noOfPages = (totalCount / limit);
        if (0 != totalCount % limit) {
             noOfPages = noOfPages + 1;
        }
        List<Integer> pages = new ArrayList<Integer>();
        for (int pageNo = 1; pageNo <= noOfPages; pageNo++) {
            pages.add(pageNo);
        }
        return pages;
    }

}
