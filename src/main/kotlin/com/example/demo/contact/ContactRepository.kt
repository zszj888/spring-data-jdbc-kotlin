package com.example.demo.contact

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface ContactRepository : ListCrudRepository<Contact, Long>,PagingAndSortingRepository<Contact,Long> {
    @Query(
        """
           SELECT c.id,c.first_name,c.last_name,c.email,s.id status_id,s.name status_name,co.id company_id, co.name company_name
            FROM contact c
            LEFT JOIN  `STATUS` s ON s.contact = c.id 
            LEFT JOIN  company co ON co.contact = c.id 
            WHERE LOWER(c.first_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) 
            OR LOWER(c.last_name) LIKE LOWER(CONCAT('%', 'Smith3', '%'))
        """
    )
    fun search(searchTerm: String): List<Contact>

}